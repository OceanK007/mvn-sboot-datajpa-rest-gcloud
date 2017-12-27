package com.ocean.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.ocean.springboot.config.exception.ApplicationGenericException;
import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.data.entity.Role_;
import com.ocean.springboot.data.entity.User;
import com.ocean.springboot.data.entity.UserDetail_;
import com.ocean.springboot.data.entity.User_;
import com.ocean.springboot.data.entity.projection.UserSummaryProjection;
import com.ocean.springboot.data.entity.specification.UserSpecification;
import com.ocean.springboot.data.enums.RoleType;
import com.ocean.springboot.data.repository.UserRepository;
import com.ocean.springboot.service.UserService;
import com.ocean.springboot.util.helper.ModelMapperHelper;
import com.ocean.springboot.util.mapper.UserMapper;
import com.ocean.springboot.util.validator.UserValidator;

@Service
@CacheConfig(cacheNames="userServiceCache")		// To user caching at service layer
public class UserServiceImpl implements UserService
{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	/** ModelMapper: To map DTO to Entity and vice-versa | etc **/
	private static ModelMapper modelMapper = ModelMapperHelper.modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	UserValidator userValidator;
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public UserDTO createUser(UserDTO userDTO, String zoneId) 
	{
		Map<String, String> validationErrors = userValidator.validate(userDTO);
		if(!validationErrors.isEmpty())
		{
			throw new ApplicationGenericException("Validation error", validationErrors);	// Will be automatically propagated since it's RunTimeException's subclass, so no need to declare it.
		}
		
		userDTO.setZoneId(zoneId);
		//User user = modelMapper.map(userDTO, User.class);
		User user = userMapper.mapToEntity(userDTO, new User());
		userRepository.save(user);
		logger.info("User saved with ID: "+user.getId());
		userDTO = userMapper.mapToDTO(user, new UserDTO());
		return userDTO;
	}
	
	@Override
	public UserDTO updateUser(UserDTO userDTO, String zoneId) 
	{
		User user = userRepository.findById(userDTO.getId());
		
		user = userMapper.mapToEntity(userDTO, user);
		user.setId(userDTO.getId());
		user.getUserDetail().setId(userDTO.getUserDetailDTO().getId());
		
		userRepository.save(user);
		logger.info("User updated with ID: "+user.getId());
		
		userDTO = userMapper.mapToDTO(user, new UserDTO());
		return userDTO;
	}

	@Override
	public Page<UserDTO> getUserByPage(Pageable pageable) 
	{
		//Pageable pageable = new PageRequest(page, pageSize);	// Pages are zero indexed, thus providing 0 for page will return the first page.
		
		logger.info("Retrieving users with pageNumber: "+pageable.getPageNumber()+" | pageSize: "+pageable.getPageSize() +" | and sorting by: "+pageable.getSort());
		Page<User> pageUserList = userRepository.findUserByPage(pageable);
		
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		pageUserList.getContent().forEach(user -> userDTOList.add(userMapper.mapToDTO(user, new UserDTO())));
		
		Page<UserDTO> pageUserDTOList = new PageImpl<>(userDTOList, pageable, pageUserList.getTotalElements());
		
		return pageUserDTOList;
	}

	@Override
	@Cacheable
	public UserDTO getUserById(Long userId)
	{
		User user = userRepository.findOne(userId);
		if(user == null)
			throw new EntityNotFoundException();		// Will be automatically propagated since it's RunTimeException's subclass, so no need to declare it.
		return userMapper.mapToDTO(user, new UserDTO());
	}

	@Override
	public Page<UserDTO> searchUserByPageCriteria(UserDTO userDTO, Pageable pageable) 
	{
		// Search criteria applies on : firstName, lastName, roleType, username, userId, email
		//Pageable pageable = new PageRequest(page, pageSize);	// Pages are zero indexed, thus providing 0 for page will return the first page.
		//Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.DESC, "id");
		
		logger.info("Retrieving users with pageNumber: "+pageable.getPageNumber()+" | pageSize: "+pageable.getPageSize() +" | and sorting by: "+pageable.getSort());
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		Predicate predicate = criteriaBuilder.conjunction();
		
		if(userDTO.getUserDetailDTO() != null && !StringUtils.isBlank(userDTO.getUserDetailDTO().getFirstName()))
		{
			//predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("userDetail").get("firstName"), "%"+userDTO.getUserDetailDTO().getFirstName()+"%"));
			
			// Use metamodel class for type-safety
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(User_.userDetail).get(UserDetail_.firstName), "%"+userDTO.getUserDetailDTO().getFirstName()+"%"));
		}
		if(userDTO.getUserDetailDTO() != null && !StringUtils.isBlank(userDTO.getUserDetailDTO().getLastName()))
		{
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(User_.userDetail).get(UserDetail_.lastName), "%"+userDTO.getUserDetailDTO().getLastName()+"%"));
		}
		if(userDTO.getUserDetailDTO() != null && !StringUtils.isBlank(userDTO.getUserDetailDTO().getEmail()))
		{
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(User_.userDetail).get(UserDetail_.email), "%"+userDTO.getUserDetailDTO().getEmail()+"%"));
		}
		if(userDTO.getRoleDTO() != null && userDTO.getRoleDTO().getRoleType() != null && !StringUtils.isBlank(userDTO.getRoleDTO().getRoleType()))
		{
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(User_.role).get(Role_.roleType), RoleType.getByValue(userDTO.getRoleDTO().getRoleType())));
		}
		if(userDTO != null && userDTO.getUsername() != null && !StringUtils.isBlank(userDTO.getUsername()))
		{
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(User_.username), "%"+userDTO.getUsername()+"%"));
		}
		if(userDTO != null && userDTO.getId() != null)
		{
			predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(User_.id), userDTO.getId()));
		}

		criteriaQuery.where(predicate);
		
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
		List<User> userList = entityManager.createQuery(criteriaQuery).setFirstResult(pageable.getPageNumber() * pageable.getPageSize()).setMaxResults(pageable.getPageSize()).getResultList();
		
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		userList.forEach(user -> {userDTOList.add(userMapper.mapToDTO(user, new UserDTO()));});
		
		Page<UserDTO> pageUserDTOList = new PageImpl<>(userDTOList, pageable, userList.size());
		
		return pageUserDTOList;
	}

	@Override
	public Page<UserDTO> searchUserByPageSpecification(UserDTO userDTO, Pageable pageable) 
	{
		Page<User> pageUserList = userRepository.findAll(UserSpecification.searchUserBySpecification(userDTO), pageable);
		//Page<User> pageUserList = userRepository.findAll(Specifications.where(UserSpecification.firstNameLike(userDTO)).and(UserSpecification.lastNameLike(userDTO)));
		
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		pageUserList.getContent().forEach(user -> userDTOList.add(userMapper.mapToDTO(user, new UserDTO())));
		
		Page<UserDTO> pageUserDTOList = new PageImpl<>(userDTOList, pageable, pageUserList.getTotalElements());
		return pageUserDTOList;
	}

	@Override
	public Page<UserDTO> getUserBriefByPage(Pageable pageable) 
	{
		Page<UserSummaryProjection> pageUserSummaryProjectionList = userRepository.findUserBriefByPage(pageable);
		
		List<UserDTO> userDTOList = new ArrayList<UserDTO>();
		pageUserSummaryProjectionList.getContent().forEach(usp -> {userDTOList.add(userMapper.mapToDTO(usp, new UserDTO()));});
		
		Page<UserDTO> pageUserDTOList = new PageImpl<>(userDTOList, pageable, pageUserSummaryProjectionList.getTotalElements());
		return pageUserDTOList;
	}
}

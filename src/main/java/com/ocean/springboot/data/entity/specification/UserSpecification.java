package com.ocean.springboot.data.entity.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.data.entity.Role_;
import com.ocean.springboot.data.entity.User;
import com.ocean.springboot.data.entity.UserDetail_;
import com.ocean.springboot.data.entity.User_;
import com.ocean.springboot.data.enums.RoleType;

public class UserSpecification
{
	public static Specification<User> searchUserBySpecification(final UserDTO userDTO)
	{
		return new Specification<User>() 
		{
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) 
			{
				Predicate predicate = criteriaBuilder.conjunction();
				
				if(userDTO.getUserDetailDTO() != null && !StringUtils.isBlank(userDTO.getUserDetailDTO().getFirstName()))
				{
					//predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("userDetail").get("firstName"), userDTO.getUserDetailDTO().getFirstName()));
					
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
				
				return predicate;
			}
		};
	}
}

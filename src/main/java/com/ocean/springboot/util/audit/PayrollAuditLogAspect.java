package com.ocean.springboot.util.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ocean.springboot.data.dto.AbstractMasterDTO;
import com.ocean.springboot.data.dto.UserDTO;
import com.ocean.springboot.data.entity.User;
import com.ocean.springboot.data.repository.UserRepository;
import com.ocean.springboot.util.mapper.UserMapper;

import  static com.ocean.springboot.util.constant.ApplicationConstant.*;

@Aspect
@Component
public class PayrollAuditLogAspect extends AuditLogAspect 
{
	private static final Logger logger = LoggerFactory.getLogger(PayrollAuditLogAspect.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Around("getLoggableMethods() && args(userDTO, zoneId)")
    public void updatePayrollAudit(ProceedingJoinPoint joinPoint, UserDTO userDTO, String zoneId) throws Throwable 
	{
        logger.info(String.format("updatePayrollAudit : %s, namespace: %s", userDTO.getId(), zoneId));
        saveAndCreateAudit(joinPoint, userDTO);
    }
	 
	@Override
	protected AbstractMasterDTO getOldValue(Long id) 
	{
		logger.info(String.format("getOldValue for id: %d", id));
		UserDTO userDTO = new UserDTO();
		if(id!=null)
		{
		    User user = userRepository.findById(new Long(id));
		    userDTO = userMapper.mapToDTO(user, userDTO);
		}
		return userDTO;
	}

	@Override
	protected String getModuleName() 
	{
		logger.info(String.format("Audit Log for module Name: %s", MODULE_NAME));
        return MODULE_NAME;
	}
}

package com.ocean.springboot.util.audit;

import java.io.IOException;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocean.springboot.data.dto.AbstractMasterDTO;

public abstract class AuditLogAspect 
{
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	private static final Logger logger = LoggerFactory.getLogger(AuditLogAspect.class);
	
	/** Here, you need to explicitly get reference of AuditLog to retrieve value of objectType() & action() **/
	@Pointcut("@annotation(com.ocean.springboot.util.audit.AuditLog))")
	public void getLoggableMethods() 
	{
		logger.info("Inside getLoggableMethods");
	}
	
	/** Here, you can directly retrieve value of objectType() & action() from auditLog reference **/
	/*@Pointcut("@annotation(auditLog))")
	public void getLoggableMethods(AuditLog auditLog) 
	{
		logger.info("Inside getLoggableMethods");
	}*/
	
	protected abstract AbstractMasterDTO getOldValue(Long id);
	
	protected abstract String getModuleName();
	
	public void saveAndCreateAudit(final ProceedingJoinPoint proceedingJoinPoint, AbstractMasterDTO abstractMasterDTO) throws Throwable
	{
		populateAuditLogInfo(proceedingJoinPoint, abstractMasterDTO);
	}
	
	private AbstractMasterDTO populateAuditLogInfo(ProceedingJoinPoint proceedingJoinPoint, AbstractMasterDTO abstractMasterDTO) throws Throwable
	{
		final AuditLog loggable = getLoggableMethodAnnotation(proceedingJoinPoint);

		AuditLogDTO auditLogDTO = null;
		AbstractMasterDTO oldValue = getOldValue(abstractMasterDTO.getId());
		AbstractMasterDTO newValue = (AbstractMasterDTO) proceedingJoinPoint.proceed();		// newValue will be the returned value of method over which @AuditLog has been used

		auditLogDTO = differenceGeneratorHelper(oldValue, newValue);
		auditLogDTO.setBody(OBJECT_MAPPER.writeValueAsString(newValue));
		auditLogDTO.setEntity(loggable.objectType());
		logger.info(String.format("Inside saveCreateBaseAudit Object Type : %s", auditLogDTO.getEntity()));
		auditLogDTO.setAction(loggable.action());
		if(!"UPDATE".equalsIgnoreCase(loggable.action())) 
		{
			auditLogDTO.setFields_changed(null);
		}
		auditLogDTO.setObject_id(String.valueOf(newValue.getId()));
		//auditLogDTO.setIp_address(abstractMasterDTO.getIpAddress());
		//auditLogDTO.setReason(abstractMasterDTO.getReasonForChange());
		//auditLogDTO.setUser_id(abstractMasterDTO.getCreatedBy());
		//auditLogDTO.setNamespace(abstractMasterDTO.getNamespace());
		//auditLogDTO.setUser_email(abstractMasterDTO.getEmailAddress());
		String moduleName = getModuleName();
		auditLogDTO.setModule_name(moduleName);
		logger.info(String.format("Inside populateAuditLogInfo:: Module Name: %s ZoneId: %s", moduleName, abstractMasterDTO.getZoneId()));
		
		// Save auditLogDTO here
		return newValue;
	}
	
	private AuditLog getLoggableMethodAnnotation(final ProceedingJoinPoint proceedingJoinPoint) 
	{
		final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		final String methodName = methodSignature.getMethod().getName();
		logger.info(String.format("Inside getLoggableMethodAnnotation Method Name: %s ", methodName));
		final Class<?>[] parameterTypes = methodSignature.getMethod().getParameterTypes();

		AuditLog loggable = null;

		try 
		{
			loggable = proceedingJoinPoint.getTarget().getClass().getMethod(methodName, parameterTypes).getAnnotation(AuditLog.class);
		} 
		catch (final NoSuchMethodException e) 
		{
			logger.error(e.getMessage());
		}

		return loggable;
	}
	
	protected AuditLogDTO differenceGeneratorHelper(final Object oldValue, final Object newValue) throws IOException 
	{
		AuditLogDTO response = new AuditLogDTO();
		StringBuilder builder = new StringBuilder();
		if (null != oldValue) 
		{
			List<DiffDTO> diffDTOs = new DiffGenerator().generateDiffs(oldValue, newValue);
			response.setDifference(OBJECT_MAPPER.writeValueAsString(diffDTOs));
			builder.append(",");
			for(DiffDTO diffDTO :diffDTOs) 
			{
				builder.append(diffDTO.getFieldName() + ",");
			}
			response.setFields_changed(builder.toString());
		}
		response.setTimestamp(DateTime.now().toString());
		return response;
	}
}
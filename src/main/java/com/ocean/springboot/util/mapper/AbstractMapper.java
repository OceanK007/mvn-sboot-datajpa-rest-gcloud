package com.ocean.springboot.util.mapper;

public abstract class AbstractMapper<AbstractMasterEntity, AbstractMasterDTO>
{
	protected abstract AbstractMasterDTO mapToDTO(AbstractMasterEntity sourceAbstractMasterEntity, AbstractMasterDTO targetAbstractMasterDTO);
	protected abstract AbstractMasterEntity mapToEntity(AbstractMasterDTO sourceAbstractMasterEntity, AbstractMasterEntity targetAbstractMasterEntity);
}

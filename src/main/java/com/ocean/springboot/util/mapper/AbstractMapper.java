package com.ocean.springboot.util.mapper;

/** 
	Abstract classes improve the situation by preventing a developer from instantiating the base class, because a developer has marked it as having missing functionality.
**/
public abstract class AbstractMapper<AbsMasterEntity, AbsMasterDTO>
{
	public abstract AbsMasterDTO mapToDTO(AbsMasterEntity sourceEntity, AbsMasterDTO targetDTO);
	public abstract AbsMasterEntity mapToEntity(AbsMasterDTO sourceDTO, AbsMasterEntity targetEntity);
}

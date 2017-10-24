package com.ocean.springboot.util.validator;

import java.util.Map;

public abstract class AbstractMasterValidator<AbsDTO>
{
	public abstract Map<String, String> validate(AbsDTO sourceDTO); 
}

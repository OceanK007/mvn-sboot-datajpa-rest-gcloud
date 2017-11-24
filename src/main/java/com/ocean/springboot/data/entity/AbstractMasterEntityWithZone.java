package com.ocean.springboot.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Value;

/** 
	Abstract classes improve the situation by preventing a developer from instantiating the base class, because a developer has marked it as having missing functionality.
**/
@MappedSuperclass	// This class won't be persisted
public abstract class AbstractMasterEntityWithZone extends AbstractMasterEntity implements Serializable
{
	private static final long serialVersionUID = 7045051117172988133L;
	
	@Transient
	@Value("${default.zoneId}")	
	private String defaultZoneId;
	
	/********************************************************************************************************************************************** 
	 	There are some changes in hibernate4 and 5. 
	 	When GenerationType.AUTO is set then in hibernate4, it doesn't create hibernate_sequence table, but in hibernate5, it does create hibernate_sequence
	 	So we should follow either:
	 
	 		* strategy=GenerationType.AUTO, generator="native"
	 		* strategy=GenerationType.IDENTITY
	 
	 	GenerationType.AUTO is good but it depends on database default strategy. That's why we should not use GenerationType.AUTO and rely on GenerationType.IDENTITY
	**********************************************************************************************************************************************/
	/********************************************************************************************************************************************** 
		@GeneratedValue annotation to have the database generate a unique primary key for us.
		
		There can be different strategies to generate primary key: AUTO, IDENTITY, TABLE, SEQUENCE
		
		* javax.persistence.GenerationType.AUTO
			The AUTO generation strategy is the default, and this setting simply chooses the primary key generation strategy that is the default for the database in question, 
			which quite typically is IDENTITY, although it might be TABLE or SEQUENCE depending upon how the database is configured. The AUTO strategy is typically recommended, 
			as it makes your code and your applications most portable.

		* javax.persistence.GenerationType.IDENTITY
			The IDENTITY option simply allows the database to generate a unique primary key for your application. No sequence or table is used to maintain the primary key information, 
			but instead, the database will just pick an appropriate, unique number for Hibernate to assign to the primary key of the entity. With MySQL, the first lowest numbered 
			primary key available in the table in question is chosen, although this behavior may differ from database to database.

		* javax.persistence.GenerationType.SEQUENCE
			Some database vendors support the use of a database sequence object for maintaining primary keys. To use a sequence, you set the GenerationType strategy to SEQUENCE, 
			specify the name of the generator annotation, and then provide the @SequenceGenerator annotation that has attributes for defining both the name of the sequence annotation, 
			and the name of the actual sequence object in the database.
	**********************************************************************************************************************************************/
	@Column(name="zone_id")
	private String zoneId;
	
    @PrePersist
    public void saveZone()
    {
    	if(this.zoneId == null)
    	{
    		this.zoneId = defaultZoneId; 	//DateTimeZone.UTC.getID(); 	//DateTime.now(DateTimeZone.UTC).getZone().toString();
    	}
    }
    
    //@PreUpdate
    // ###
    
	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
}

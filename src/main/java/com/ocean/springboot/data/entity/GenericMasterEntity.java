package com.ocean.springboot.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@MappedSuperclass	// This class won't be persisted
public class GenericMasterEntity implements Serializable
{
	private static final long serialVersionUID = -7129389120158566946L;

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
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	//@Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")	// In Database: column data-type will be TimeStamp (hibernate 3)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")	// In Database: column data-type will be DateTime (hibernate 4 & 5)
    @Column(name = "date_created")
	private DateTime dateCreated;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")	// In Database: column data-type will be DateTime
    @Column(name = "date_modified")
	private DateTime dateModified;

	@Column(name="zone_id")
	private String zoneId;
	
    @PrePersist
    public void onSave()
    {
    	if(this.zoneId == null)
    	{
    		this.zoneId = DateTimeZone.UTC.getID(); 	//DateTime.now(DateTimeZone.UTC).getZone().toString();
    	}
    	if (this.dateCreated == null) 
    	{
            this.dateCreated = DateTime.now(DateTimeZone.UTC);	// Saving in UTC timing
        }
        this.dateModified = DateTime.now(DateTimeZone.UTC);	
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(DateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public DateTime getDateModified() {
		return dateModified;
	}

	public void setDateModified(DateTime dateModified) {
		this.dateModified = dateModified;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
}

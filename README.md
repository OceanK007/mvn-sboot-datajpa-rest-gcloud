# mvn-spring-boot
  Creating a project based on maven, spring boot and other technologies.

# Requirements:
   	> Java 8 or above
	> MySQL database
	> Eclipse IDE
	> Apache Maven

# To run the project(There are 2 approaches):
   ** Approach first **
   1. cd project-folder-name
   2. mvn clean install -DskipTests   || mvn package	(It will create the jar/war file of project)
   3. java -jar target/jar-or-war-name-with-extension
    	
   ** Approach second **
   1. cd project-folder-name
   2. mvn sprint-boot:run
   
# To run in debug mode:
   1. cd project-folder-name
   2. mvnDebug spring-boot:run
   3. goto eclipse > debug > debug configurations > remote java application > create new configuration

# To access swagger UI:
   > http://localhost:8080/swagger-ui.html

# Branch: sboot-basic-setup
   	> Adding External configuration (.properties file configuration)
   	> Adding JUnit
   	> Adding Logging functionality slf4j
   	> Adding Management services (health, audit, beans and more)
	  ** You will see a new set of RESTful end points added to the application. These are management services provided by Spring Boot.
	  2017-09-19 15:18:29.831  INFO 3692 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/]}" onto public java.lang.String com.ocean.springboot.api.endpoint.SpringBootApi.hello()
	  2017-09-19 15:18:29.834  INFO 3692 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
	  etc..

# Branch: sboot-datajpa-hibernate
   	> Adding External configuration (.properties file configuration)
   	> Adding JUnit
   	> Adding Logging functionality slf4j
	> Adding Management services (health, audit, beans and more)
	> Adding Data JPA with Hibernate as implementation
	> Adding MySQL as database
	> Adding modelMapper to map DTO to Entity and vice-versa
	> Adding JodaTime
	> Adding Exception handling for Validations fail. Plus modified some pre-defined exceptions for better explanation
	> Adding indexes to optimize the search from database.
	> Adding Criteria for dynamic query generation.
	> Adding Specification for dynamic query generation.
	> Adding QueryDSL for dynamic query generation. (Still didn't implement)
	> Adding Projection to fetch specific numbers of columns.
	> Adding AOP on class and method level annotations.


# To do
* connection pooling
* Transaction management
* Logging AOP
* Global exception handling
* session management
* Bulk Updation
* Mongo DB
* ThreadLocal variables
* Profiles using pom and java
	
# General information

** ============ Indexes =========== **

Indexes are used to find rows with specific column values quickly. Without an index, MySQL must begin with the first row and then read through the entire table to find the relevant rows. 
The larger the table, the more this costs. If the table has an index for the columns in question, MySQL can quickly determine the position to seek to in the middle of the data file without 
having to look at all the data. This is much faster than reading every row sequentially.

Updating a table with indexes takes more time than updating a table without (because the indexes also need an update). So, only create indexes on columns that will be frequently searched against.

Indexes are automatically created when PRIMARY KEY and UNIQUE constraints are defined on table columns.

Both clustered and nonclustered indexes can be unique. This means no two rows can have the same value for the index key. Otherwise, the index is not unique and multiple rows can share the same key value.

The primary key - and only the primary key - is a clustered index. If you don't explicitly define a primary key, the first suitable UNIQUE key is used. 
If you don't have either a primary key or a suitable UNIQUE key, MySQL generates a hidden clustered index.

All indexes other than the clustered index are known as secondary indexes

** Clustered Index **

* It creates a physical order of rows.
* PRIMARY KEY is basically servers as Clustering index.
* There can be only one clustered index per table, because the data rows themselves can be sorted in only one order. So when you create a table, the rows are sorted with PRIMARY KEY basis.
* When a table has a clustered index, the table is called a clustered table. If a table has no clustered index, its data rows are stored in an unordered structure called a heap.

** Non-Clustered Index **

* It creates a binary tree and doesn't create a physical order of rows.

** How to create in MySQL **

* Create non-unique index (Duplicate values are allowed): CREATE INDEX index_name ON table_name (column1, column2, ...);
* Create Unique Index (Duplicate values are not allowed): CREATE UNIQUE INDEX index_name ON table_name (column1, column2, ...);

** ============ Hibernate Template ============ **

* There can be many types of templates like for hibernate, jdbc, rest, jpa etc.

** What's the need of Hibernate Template? **

What HibernateTemplate did for you was to automatically open and close sessions and commit or rollback transactions after your code executed. However, 
all of this can be achieved in an aspect-oriented way using Spring's Declarative Transaction Management.

Using these classes was inevitable with older versions of Hibernate in order to integrate support of Spring-managed transactions.

However, since Hibernate 3.0.1 you don't need it any more - you can write a code against a plain Hibernate API while using Spring-managed transactions. 
All you need is to configure Spring transaction support, inject SessionFactory and call getCurrentSession() on it when you need to work with session.

If you use Spring, then you should use its declarative transaction management, which allows you to avoid opening, committing, closing and flushing. It's all done by Spring automatically:

E.g: 

@Autowired
private SessionFactory sessionFactory;

@Transactional
public void someMethod() {
    // get the session for the current transaction:
    Session session = sessionFactory.getCurrentSession();
    // do things with the session (queries, merges, persists, etc.)
}

In the above example, a transaction will be started (if not already started) before the method invocation; A session will be created by Spring for the transaction, 
and the session will be automatically flushed before the commit of the transaction, that will be done by Spring automatically when the method returns.

** ============ Criteria vs Specification vs QueryDSLs ============ **

** Criteria **

It's generally used to create dynamic queries based on requirements using predicates.
In criteria, you can create predicates but you can't reuse it somewhere else. Plus to use predicate, you have to use CriteriaBuilder, CriteriaQuery and Root first. 

** Specification ** 

To be able to define reusable Predicates Specification interface has introduced.
It defines a specification as a predicate over an entity.
The actually only consists of a single method:

public interface Specification<T> {
  Predicate toPredicate(Root<T> root, CriteriaQuery query, CriteriaBuilder cb);
}

Cons:
It requires quite some coding effort.

** QueryDSL **

QueryDsl is quite similar to specifications but it requires less coding.
QueryDsl approach is not only available for our JPA repositories but for MongoDB support as well.


** ============ Metadata ============ **
These metamodel classes provide static access to the metadata that describes the attributes of our domain model classes.

When you write a criteria query or create a dynamic entity graph, you need to reference the entity classes and their attributes. 
The quickest and easiest way is to provide the required names as Strings. But this has several drawbacks, 
e.g. you have to remember or look-up all the names of the entity attributes when you write the query. 
But it will also cause even greater issues at later phases of the project, if you have to refactor your entities and change the names of some attributes. 
In that case you have to use the search function of your IDE and try to find all Strings that reference the changed attributes. 
This is a tedious and error prone activity which will easily take up the most time of the refactoring.

Prefer to use the static metamodel to write criteria queries and dynamic entity graphs. 
This is a small feature defined by the JPA specification which provides a type-safe way to reference the entities and their properties.


You can create metamodel classes manually. But to avoid manual creation of metadata classe, you can define plugin entry in pom.xml 

** ============ Swagger ============ **
Swagger is used to describe and document RESTful APIs.

** ============ ThreadLocal =========== **
One possible (and common) use is when you have some object that is not thread-safe, but you want to avoid synchronizing access to that object (like SimpleDateFormat). 
Instead, give each thread its own instance of the object.

For example:

public class Foo
{
   // SimpleDateFormat is not thread-safe, so give one to each thread
   private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue()
        {
            return new SimpleDateFormat("yyyyMMdd HHmm");
        }
   };

   public String formatIt(Date date)
   {
        return formatter.get().format(date);
   }
}

** ============ @CacheConfig & @Cacheable =========== **
@CacheConfig annotation is used at class level to share common cache related settings. 
All the methods of the class annotated with @Cacheable gets a common cache related settings provided by @CacheConfig. 
The attributes of @CacheConfig are cacheNames, cacheManager, cacheResolver and keyGenerator. 
We can override the class level cache related setting for a method using attributes of @Cacheable.

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
@Service
@CacheConfig(cacheNames="mycacheone")
public class Student {
   @Cacheable
   public String getStudentName(int stdId) {
	System.out.println("execute getStudentName method...");
	if (stdId == 1) {
		return "Ramesh";
	} else {
		return "Mahesh";
	}
   }
   @Cacheable(value = "mycachetwo")
   public String getCity(int cityId) {
	System.out.println("execute getCity method...");
	if (cityId == 1) {
		return "Varanasi";
	} else {
		return "Allahabad";
	}
   }
} 

1.	The class has two methods and both are annotated with @Cacheable annotation. 
2.	With the help of @CacheConfig at class level, we have configured cache name as mycacheone, so all the methods annotated with @Cacheable will use this cache name until we do not override. 
3.	getStudentName() method will use mycacheone cache name where as getCity() will use mycachetwo cache name because this method has overridden cache name using @Cacheable(value = "mycachetwo"). 
4.	If the method is not annotated with @Cacheable, the result of that method will not be cached.

---------------------------------------------------------------------------------------

The Cacheable annotation takes three arguments: value, which is mandatory, together with key and condition. 
The first of these, value, is used to specify the name of the cache (or caches) in which the a method’s return value is stored.

 @Cacheable(value = "employee")
  public Person findEmployee(String firstName, String surname, int age) {
    return new Person(firstName, surname, age);
  }

The code above ensures that the new Person object is stored in the “employee” cache. 

Any data stored in a cache requires a key for its speedy retrieval. Spring, by default, creates caching keys using the annotated method’s 
signature as demonstrated by the code above. You can override this using @Cacheable’s second parameter: key. To define a custom key you use a SpEL expression.

@Cacheable(value = "employee", key = "#surname")
  public Person findEmployeeBySurname(String firstName, String surname, int age) {
    return new Person(firstName, surname, age);
  }
  
In the findEmployeeBySurname(...) code, the ‘#surname’ string is a SpEL expression that means ‘go and create a key using the surname argument of the findEmployeeBySurname(...) method’. 

The final @Cacheable argument is the optional condition argument. Again, this references a SpEL expression, but this time it’s 
specifies a condition that’s used to determine whether or not your method’s return value is added to the cache.

 @Cacheable(value = "employee", condition = "#age < 25")
  public Person findEmployeeByAge(String firstName, String surname, int age) {
    return new Person(firstName, surname, age);
  }
  
In the code above, I’ve applied the ludicrous business rule of only caching Person objects if the employee is less than 25 years old.

# Spring boot URLs
https://spring.io/guides/gs/spring-boot/

# JodaTime URLs
https://stackoverflow.com/questions/18504174/daylight-saving-time-in-java/23147782#23147782
https://stackoverflow.com/questions/41743455/how-to-store-joda-datetime-in-sql-server-database-with-hibernate
https://stackoverflow.com/questions/17271039/does-system-currenttimemillis-return-utc-time
https://en.wikipedia.org/wiki/List_of_tz_database_time_zones

# DataJPA URLs
https://codingexplained.com/coding/java/spring-framework/fetch-query-not-working-spring-data-jpa-pageable

# Exception Handling URLs
https://www.toptal.com/java/spring-boot-rest-api-error-handling
https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/bind/annotation/ExceptionHandler.html

# MySQL indexing URLs
https://dev.mysql.com/doc/refman/5.7/en/mysql-indexes.html
https://stackoverflow.com/questions/2955459/what-is-an-index-in-sql
https://www.w3schools.com/sql/sql_create_index.asp

# Data JPA Criteria URLs
http://www.baeldung.com/rest-api-search-language-spring-data-specifications
http://www.baeldung.com/rest-search-language-spring-jpa-criteria
https://github.com/pkainulainen/spring-data-jpa-examples/tree/master/criteria-api

# Data JPA Specifications & QueryDSL URLs
https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/
https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-five-querydsl/

# Data JPA metamodel(Used in criteria, specification, queryDsl) automatic generator URLs
https://docs.jboss.org/hibernate/orm/5.0/topical/html/metamodelgen/MetamodelGenerator.html
https://www.thoughts-on-java.org/static-metamodel/
https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-four-jpa-criteria-queries/

# Swagger URLs
https://huongdanjava.com/documenting-your-rest-api-with-swagger-in-spring-boot.html
https://dzone.com/articles/spring-boot-restful-api-documentation-with-swagger

# ThreadLocal Variables URLs
https://stackoverflow.com/questions/817856/when-and-how-should-i-use-a-threadlocal-variable

# Cache URLs
https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-cache				// Scheduler + profiling
https://memorynotfound.com/spring-boot-ehcache-2-caching-example-configuration/
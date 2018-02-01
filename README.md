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
	> Adding Caching @Service and @Repository levels.

# Branch: sboot-datajpa-mongodb
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
	> Adding Caching @Service and @Repository levels.
	> Adding mongodb crud operation.
	
# To do
* Transaction management
* Logging AOP
* Global exception handling
* session management
* Bulk Updation
* ThreadLocal variables
* Profiles using pom and java
* JUnit
* Big Query
* Cloud api usage
* Https implementation
* Run spring-boot on different port
* Design Patterns
	
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
  
In the code above, we’ve applied the ludicrous business rule of only caching Person objects if the employee is less than 25 years old.

** ============ Connection Pooling =========== **

* Spring Boot is using Tomcat pooling by default.

   org.apache.tomcat.jdbc.pool.DataSource@55c16e2c{ConnectionPool[defaultAutoCommit=null; defaultReadOnly=null; ... }

* HikariCP seems to perform better than the other connection pools like — C3P0, BoneCP, and Apache DBCP

** ============ Google Compute Engine Vs Google App Engine (Standard) vs Google App Engine (Flexible) =========== **

* Compute Engine is analogous to a virtual PC, where you'd deploy a small website + database, for instance. You manage everything, including control of installed disk drives. If you deploy a website, you're in charge of setting up DNS etc.

* Google App Engine (Standard) is like a read-only sandboxed folder where you upload code to execute from and don't worry about the rest (yes: read-only). DNS / Sub-domains etc is so much easier to map.

* Google App Engine (Flexible) is in fact like a whole file-system (not just a locked down folder), where you have more power than the Standard engine, e.g. you have read/write permissions, (but less compared to a Compute Engine). In GAE standard you have a fixed set of libraries installed for you and you cannot deploy 3rd party libraries at will. In the Flexible environment you can install whatever library your app depends on, including custom build environments (such as Python 3).

Although GAE Standard is very cumbersome to deal with (although Google makes it sound simple), it scales really well when put under pressure. It's cumbersome because you need to test and ensure compatibility with the locked-down environment and ensure any 3rd party library you use does not use any other 3rd party library you're unaware of which may not work on GAE standard. It takes longer to set it up in practice but can be more rewarding in the longer run for simple deployments.

** ============ SAAS vs PAAS vs IAAS =========== **

The cloud is a very broad concept, and it covers just about every possible sort of online service, but when businesses refer to cloud procurement, 
there are usually three models of cloud service under consideration, Software as a Service (SaaS), Platform as a Service (PaaS), 
and Infrastructure as a Service (IaaS). Each has its own intricacies and hybrid cloud models, but today we’re going to help you develop an understanding 
of the high-level differences between SaaS, PaaS, and IaaS.

* SOFTWARE AS A SERVICE
In some ways, SaaS is very similar to the old thin-client model of software provision, where clients, in this case usually web browsers, 
provide the point of access to software running on servers. SaaS is the most familiar form of cloud service for consumers. SaaS moves the
task of managing software and its deployment to third-party services. Among the most familiar SaaS applications for business are customer 
relationship management applications like Salesforce, productivity software suites like Google Apps, and storage solutions brothers like Box and Dropbox.

Use of SaaS applications tends to reduce the cost of software ownership by removing the need for technical staff to manage install, manage, 
and upgrade software, as well as reduce the cost of licensing software. SaaS applications are usually provided on a subscription model.

* PLATFORM AS A SERVICE
PaaS functions at a lower level than SaaS, typically providing a platform on which software can be developed and deployed. PaaS providers 
abstract much of the work of dealing with servers and give clients an environment in which the operating system and server software, as well 
as the underlying server hardware and network infrastructure are taken care of, leaving users free to focus on the business side of scalability, 
and the application development of their product or service.

As with most cloud services, PaaS is built on top of virtualization technology. Businesses can requisition resources as they need them, scaling 
as demand grows, rather than investing in hardware with redundant resources.

Examples of PaaS providers include Heroku, Google App Engine, and Red Hat’s OpenShift.

* INFRASTRUCTURE AS A SERVICE
Moving down the stack, we get to the fundamental building blocks for cloud services. IaaS is comprised of highly automated and scalable compute 
resources, complemented by cloud storage and network capability which can be self-provisioned, metered, and available on-demand.

IaaS providers offer these cloud servers and their associated resources via dashboard and/or API. IaaS clients have direct access to their servers 
and storage, just as they would with traditional servers but gain access to a much higher order of scalability. Users of IaaS can outsource and 
build a “virtual data center” in the cloud and have access to many of the same technologies and resource capabilities of a traditional data center 
without having to invest in capacity planning or the physical maintenance and management of it.

IaaS is the most flexible cloud computing model and allows for automated deployment of servers, processing power, storage, and networking. IaaS 
clients have true control over their infrastructure than users of PaaS or SaaS services. The main uses of IaaS include the actual development 
and deployment of PaaS, SaaS, and web-scale applications.

There are a lot of providers offering Infrastructure as a Service such as Google Cloud Engine, Navisite, exoscale, and Softlayer reach with their own unique value proposition and service portfolio to choose from.

** ============ JUnit with Mockito =========== **

** @RunWith(MockitoJUnitRunner.class)

In order for mockito annotations to be enabled, we’ll need to annotate the JUnit test with a runner – MockitoJUnitRunner

Alternatively, we can enable these annotations programmatically as well, by invoking MockitoAnnotations.initMocks() as in the following example:
@Before
public void init() 
{
    MockitoAnnotations.initMocks(this);
}


** @Mocks vs @InjectMocks

@Mock creates a mock. @InjectMocks creates an instance of the class and injects the mocks that are created with the @Mock (or @Spy) annotations into this instance. 
Note that you must use @RunWith(MockitoJUnitRunner.class) or Mockito.initMocks(this) to initialise these mocks and inject them.

Example:

 @RunWith(MockitoJUnitRunner.class)
 public class SomeManagerTest 
 {
	@InjectMocks
	private SomeManager someManager;
		
 	@Mock
	private SomeDependency someDependency; // this will be injected into someManager
		
 	//tests...
 }


** ============ Linux commands =========== **
ls 									:: To display all folders of current directory
cd /								:: To go to root 
cd ~								:: To go to home
dpkg-query -l						:: To display all installed packages
dpkg-query -L firefox				:: To display the installation folder
someCommand > someFile.txt 			:: To save the command console output in a text file

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

# Connection Pooling URLs
https://www.wix.engineering/single-post/how-does-hikaricp-compare-to-other-connection-pools
https://github.com/hendisantika/HikariCP/blob/master/pom.xml
https://www.mkyong.com/spring-boot/spring-boot-how-to-know-which-connection-pool-is-used/
http://www.mkyong.com/spring-boot/spring-boot-jdbc-mysql-hikaricp-example/
https://github.com/brettwooldridge/HikariCP/wiki/MBean-(JMX)-Monitoring-and-Management

# Google Compute Engine Vs Google App Engine (Standard) vs Google App Engine (Flexible) Urls
https://stackoverflow.com/questions/22697049/what-is-the-difference-between-google-app-engine-and-google-compute-engine

# SAAS vs PAAS vs IAAS
https://www.computenext.com/blog/when-to-use-saas-paas-and-iaas/

# Google Cloud
https://cloud.google.com/compute/docs/quickstarts
https://cloud.google.com/compute/docs/tutorials/basic-webserver-apache

# MongoDB
http://www.baeldung.com/queries-in-spring-data-mongodb
https://www.mkyong.com/spring-boot/spring-boot-spring-data-mongodb-example/
http://javasampleapproach.com/spring-framework/spring-data/mongodb-model-one-one-one-many-relationships-mongodb-embedded-documents-springboot

# JUnit with Mockito
http://www.springboottutorial.com/spring-boot-unit-testing-and-mocking-with-mockito-and-junit
http://www.baeldung.com/mockito-behavior
http://www.baeldung.com/mockito-annotations

# Design Patterns
https://www.mkyong.com/design-pattern/java-builder-design-pattern-example/
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

# General information

** --------- Indexes --------- **

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

** ---------- Hibernate Template ---------- **

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

# To do
* Adding projections in jpa
* Adding search by multiple columns using criteria
* connection pooling
* Transaction management
* Logging AOP
* Global exception handling
* session management

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
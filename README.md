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

# URLs
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
# Spring Projects
- Spring Projects are built on top of Spring Modules and provide solutions to other issues faced by Enterprise Applications
- There are a variety of Spring Projects, take a look [here](https://spring.io/projects)
- They are also open source, so you can take a look at their source code [here](https://github.com/spring-projects)
- We will look primarily at Spring Boot and Spring Data

## Spring Boot
- Takes an Opinionated view of building Spring Applications in order to get app up and running as quickly as possible
  - Follow a principle of `Convention over Configuration`
- Using Spring Boot makes it very easy to create stand-alone, production-grade Spring-based Applications that you can "just run"

### Benefits/Features
- Embedded Tomcat Server
  - We use `jar` packaging, and when we launch it, it will start a Tomcat Server and host the web application at a configured port
- Many provided `starter` dependencies
- Necessary Pre-configuration can be found in an `application.properties` file
  - No xml configuration for additional config
- Spring Boot Actuator
  - Provides endpoints for helpful metrics and project information
- Spring Boot Development Tools (DevTools)
  - Provides developer support, such as hot-reloading the application when files are changed

#### Running a Spring Boot App
- Annotate your class with the main method with `@SpringBootApplication`
  - `@Configuration`
    - Allows us to declare Spring beans with Java Config using `@Bean`
  - `@EnableAutoConfiguration`
    - Enables Spring Boot's default configurations
  - `@ComponentScan`
    - Enables Spring-based annotations
- In your main method
  - `SpringApplication.run(Driver.class, args);`

## Spring Data JPA
- Spring Data JPA is a Spring Boot Project which further abstracts away our persistence layer
- Builds on top of Hibernate
- Provides repository support for the Java Persistence API (JPA)
- Eases development of applications that need access to JPA data sources

### Important Interfaces
- CrudRepository
  - PagingAndSortingRepository
    - JpaRepository
- They use Generics to identify between classes and their Primary Key type

Spring Data will dynamically construct Repositories based on:
- The Entity
  - The class name
  - The field names
  - The methods
  - The JPA Annotations
- The type of the Primary Key
- Then will use Dependency Injection to inject the implementing repository into any field that uses Autowiring for the interface

#### Spring Data Rest
- Another Project built on top of Spring Data JPA that will construct Repositories, Services, and Controllers to provide a RESTful API based on entities declared with a particular annotation
- This RESTful API even includes Paging and Sorting functionality and HATEOAS

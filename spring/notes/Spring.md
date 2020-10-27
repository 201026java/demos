# Spring Framework
- An open source Java framework/platform supports the development of enterprise applications
- Utilizes Dependency Injection as an implementation of Inversion of Control
  - Whenever you have a dependency, instead of instantiating it directly,
    you obtain an instance from the IoC (Inversion of Control) container

Specifically, we can turn this code

```java
public class EmployeeService implements IEmployeeService {
  private IEmployeeDAO dao = new EmployeeDAO();
}
```

into this:

```java
public class EmployeeService implements IEmployeeService {
  private IEmployeeDAO dao;
}
```
- "Spring" is an umbrella term. It refers to the overall framework itself, the modules that makes up the framework, as well as many projects that built on top of the framework (and even constructs within the framework).

## IOC
- Inversion of Control
  - Inverts control of the application flow and object creation to a framework
    - Dependendency Injection (DI) is a pattern to implement Inversion of Control
      - Abstract design or pattern where behavior is injected into your classes
      - Achieves "Loose Coupling"

![Role of the IOC container](https://docs.spring.io/spring/docs/current/spring-framework-reference/images/container-magic.png "Role of the IOC container")

### Dependency Injection
- An implementation of IoC
  - Dependency: Some object to be used
  - Injection: Passing dependency to a dependent object at creation time
  - Spring bean is provided its dependencies by the framework
  - Decouples configuration from implementation

### Spring Bean
- Any object whose lifecycle is managed by Spring (The Spring Container / IoC Container)

### Spring Containers
- AKA Inversion of Control (or IoC) Container
  - Track classes registered as "Spring beans"
- Implementations
  - BeanFactory (interface)
  - ApplicationContext (interface)
    - A sub-interface of BeanFactory
    - The newer version that is used now

## Spring Modules
The Spring Framework is subdivided into different modules:

- Spring Beans
- Spring Core
- Spring Context

Spring Core + Spring Beans provide the essential DI features with the BeanFactory interface

Spring Context builds on Core & Beans to add additional functionality and access to the ApplicationContext interface

Additional Modules:
- Web Module
- AOP Module
- ORM Module
- JDBC Module
- Etc

## Spring Projects
Spring Projects are built on top of a set of modules from the framework.
- Spring Boot
- Spring Data
- Spring Security
- Etc

## BeanFactory vs ApplicationContext
- Both represent the IoC Container, manage the lifecycle of Spring Managed objects

### BeanFactory
- Older
- Lazily instantiate our Spring Beans
- Must provide a resource object configured for our `beans.xml`

Ex Implementations
- XmlBeanFactory

```java
XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
```

### ApplicationContext
- Newer
- Eagerly instantiate our Spring Beans
- Provides support for annotations
- Creates and manages its own resource objects
- Naming convention changed to use `applicationContext.xml`

- Sub-interface of BeanFactory
- Easier integration with Spring's AOP features
- Event publication
- Internationalization features
- Has support for application-layer specific contexts such as `WebApplicationContext` for example

Ex Implementations
- ClassPathXmlApplicationContext
- FileSystemXmlApplicationContext
- XmlWebApplicationContext
- Etc

```java
ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
```

### Configuration

#### XML Config

- Defined in `applicationContext.xml` (src > main > resources > `applicationContext.xml`)

```xml
<bean name="beanA" class="com.revature.models.BeanA"></bean>
```

#### Annotation Config
1. Enable "Component Scanning" in `applicationContext.xml`
2. Annotate classes with "Stereotype Annotations"
  - @Component: general Spring-Managed component
  - @Repository: DAO
  - @Controller
  - @Service

#### Java Config
There's a way of creating a class with the @Configuration annotation (or not if using a specific implementation of the ApplicationContext), with methods using @Bean to register beans in a centralized location without needing to have an `applicationContext.xml` file.

## Spring Bean LifeCycle
0. Request Bean from ApplicationContext
    - Instantiation
    - Populate Properties
1. BeanNameAware's setBeanName method
2. BeanClassLoaderAware's setBeanClassLoader method
3. beanFactoryAware's setBeanFactory method
    - The following are NOT sub-points in the LifeCycle, they are just newer, and therefore not as required to know
        - Only applicable when running in an ApplicationContext
    1. ResourceLoaderAware's setResourceLoader method
    2. ApplicationEventPublisherAware's setApplicationEventPublisher method
    3. MessageSourceAware's setMessageSource method
    4. ApplicationContextAware's setApplicationContext method
    5. ServletContextAware's setServletContext method
        - Only applicable when running in a WebApplicationContext
4. EnvironmentAware's setEnvironment method
5. BeanPostProcessor's postProcessBeforeInitialization method
6. InitializingBean's afterPropertiesSet method
7. Custom init method
    - Init method in our bean declaration (applicationContext.xml)
    - @PostConstruct annotation
8. BeanPostProcessor's postProcessAfterInitialization method

### On Shutdown
1. DestructionAwareBeanPostProcessor's postProcessBeforeDestruction method
2. DisposableBean's destroy method
3. Custom destroy method
  - Destroy method in our bean declaration (applicationContext.xml)
  - @PreDestroy annotation

### Conceptual Spring Bean LifeCycle
- Setup
    - Object is instantiated
    - handles dependencies (assigns values to instance variables)
    - Custom initialization method
    - Spring Bean is ready
- Tear Down
    - Spring's destroy method
    - Custom destroy method

## Bean Scopes

These scopes determine how many instances of a particular bean we create. This depends on the context in which they are used (hence, "scope").

### Universal Scopes
- Singleton (default): There is only 1 instance of the Bean at a time
- Prototype: There will be a new Bean instantiated each time it is called for

### Web-Aware Scopes
- Request: One Per HTTP Request
- Session: One Per HTTP Session
- New as of Spring 5
  - Application: One Per ServletContext (Per Web Container)
  - WebSocket: One Per WebSocket
- Now deprecated as of Spring 5
  - Global Session: This was used for Portlets for global sessions

## Bean Wiring
- Just as there are various ways to register beans with our IoC Container, there are a few ways that we can wire the dependencies together
- The process of providing configuration (dependencies) to our application
  - How we connect our beans via DI

### Setter Injection
- Uses setter methods to provide dependencies
- A little bit more readable than the alternative
- Does NOT ensure DI because an instance can be created without configuring a particular field
  - no issue if we don't configure
  - We can still use an @Required annotation to make sure there is configuration for that dependency
    - It will prevent NullPointerExceptions from being thrown down the line

### Constructor Injection
- Uses a Constructor, similarly to Angular
- When we provide a Constructor with the necessary dependencies as Constructor arguments
- Doesn't allow you to create objects until the dependencies are ready

### Autowiring
- Let Spring figure it out
- The dependencies are wired "automagically"

#### By Type
- Automagically determine DI based on the type of the dependencies and the setters within the class

```xml
<bean name="beanA" class="com.revature.BeanA" autowire="byType"></bean>
<bean name="beanB" class="com.revature.BeanB"></bean>
```

```java
public class BeanA {
  private BeanB beanB;

  public void setBeanB(BeanB beanB) {
    this.beanB = beanB;
  } // Spring will look to inject based on the type of the object
  // Namely, "BeanB"
}
```

#### By Name
- Automatigically determine DI based on the name of the dependencies and the setters in the class

```xml
<bean name="beanA" class="com.revature.BeanA" autowire="byName"></bean>
<bean name="beanB" class="com.revature.BeanB"></bean>
```

```java
public class BeanA {
  private BeanB beanB;

  public void setBeanB(BeanB beanB) {
    this.beanB = beanB;
  } // Spring will look to inject based on the type of the object
  // Namely, "BeanB"
}
```

#### Autowiring with Annotations
- Enable Component Scanning

```xml
<context:component-scan base-package="com.revature"></context:component-scan>
```

```java
@Component
public class BeanA {

  @Autowired // Technically uses Reflection
  // Which means it won't truly invoke your setter method
  private BeanB beanB;

  // Spring will look to perform type-based "field-injection"
}
```

We also have an @Qualifier annotation
This can be used if there is more than one bean of the same type to be identified,
however it is generally considered a bad practice if it can be avoided.

Do your best NOT to have multiple Spring Beans of the same type

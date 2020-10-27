# Spring MVC
- Deals with the MVC Design Pattern
  - Model: Data being passed, rendered, and manipulated
  - View: What will be displayed
  - Controller: Handles logic & routing
- In the context of Project 2, the "View" is effectively our Angular Application
- The data will be passed across the internet using HTTP to be rendered in the Angular application
- The Controller will handle the routing of the requests and the logic for sending the data back

- Technically speaking, we can handle the View directly through Spring MVC, using the "ViewResolver"

- SpringMVC provides a "DispatcherServlet"
  - SpringMVC will abstract away the complexity of handling requests by providing to you a Servlet that is following the FrontController Design Pattern

## Important Components
- DispatcherServlet
  - Only Servlet we have
  - Provided to us
  - Follows the Front Controller Design Pattern
- HandlerMapping (interface)
  - Similar to the RequestDispatcher class from the BusinessDelegateExample
  - Handle routing requests from url prefixes to specific Controller classes
    - Those Controller classes will use annotations to configure further
    - @Controller
    - @RequestMapping(parameters)
      - @GetMapping
      - @PostMapping
      - @PutMapping
      - @DeleteMapping
      - etc
- ViewResolver (interface)
  - Handle the routing of static resources if the server has any
  - Specific class implementations
    - InternalResourceViewResolver

```java
@Controller
public class TestController {
  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public String test() {
    return "test";
  }
}
```

```xml
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
  <property name="prefix" value="/static/" />
  <property name="suffix" value=".html" />
</bean>
```

## ContextLoadListener
- Ties the Lifecycle of the ApplicationContext with the ServletContext
- Automates the creation of the ApplicationContext so that Tomcat will start it for us
- Configured in the `web.xml`

### Additional Bean Scopes
- Additional Web Context Bean Scopes that were listed earlier
- Request
- Session
- Application
- WebSocket
- Used to have Request, Session, & GlobalSession before Spring 5, but not anymore


## Control Flow
0. Before any Request, the ContextLoadListener allows Tomcat to create the ApplicationContext (Spring Container)
  - This has our important components listed above
1. HTTP Request arrives at the `DispatcherServlet`
2. `DispatcherServlet` consults the `HandlerMapping` to determine which Controller to delegate to
3. Passes the relevant information to the Controller
  - Depend upon the method parameters indicated with annotations
  - Variety of other annotations to configure what data is passed over
4. Controller will consult the business (or service) layer to process request
5. Controller builds the response (Model and/or View)
6. The response is handed back to the `DispatcherServlet`
7. `DispatcherServlet` will consult the `ViewResolver` to process the view if needed
8. `DispatcherServlet` sends the response back to the client

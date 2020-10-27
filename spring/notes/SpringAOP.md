# AOP -> Aspect Oriented Program / Programming
- Breaking down the program logic into distinct "cross cutting concerns"
  - Logging, Security, Caching, Validation
  - These are issues that must be addressed in every single one of our classes (or at least many of them)
  - We end up repeating code, which is not very efficient
- We will break down these "aspects" into units that we will refer to as "Aspects"
  - "Aspect" will be our unit of modularity
- We'll annotate our class with @Aspect and @Component to address a particular concern
  - @Aspect is annotation that will we get from the AspectJ Library, which is a full AOP framework
  - We primarily use AspectJ annotations rather than xml config
- Overall, what we'll do, is effectively write code once, that will injected into other locations of our codebase

## Join Point
- Point in the application where code will be injected
- JP is represented by a method execution
  - We only inject at methods
- "Where you **can** inject"

## PointCut
- Predicate that specifies which methods (JoinPoints) will be affected
  - Think of Regular Expressions as an example
- A collection of JPs matching a particular criteria
- "Where you **will** inject

## Advice
- Code to be injected at a Join Point
- "**What** is to be injected"
- Several Types of Advice
  - @Before
    - Execute before the JoinPoint
  - @After
    - Execute after the JoinPoint (regardless of the JP's success/failure)
    - @AfterReturning
      - Execute after the JP completes successfully
    - @AfterThrowing
      - Execute after the JP throws an exception
  - @Around
    - Allows for advice to be injected before and after the JP execution
    - Most powerful type of advice and should not be used if Before or After could achieve what's needed
- Advice is associated with Point Cuts and run at matching Join Points
- JoinPoint interface will be able to be passed into any aspect method
  - Gives access to the `target` object
  - The object being advised (This is generally a Spring AOP Proxy object, but not always)
  - Allows us to get the JP method signature, arguments, etc
- ProceedingJoinPoint is a sub-interface of JoinPoint that can be passed into a method which handles @Around advice only
  - Allows you to control if/when the JoinPoint is actually executed relative to the surround advice
  - There is a `proceed` method that will execute the JoinPoint

## Types of PointCut Expressions
- `execution`
  - Most common, method execution
  - `"execution([type] [methodSignature](..)"`
    - `..` indicates any parameter list
  - `"execution(* doSomething()"`
    - This will be a method named `doSomething` with no parameters, and any return type
  - `"execution(* set*(..)"`
    - This will be a method name that begins with `set` and has any parameters, and any return type
- `within`
  - Limit to method executions to within certain classes
    - `"within(com.revature.models.*)"`
      - Any methods available in the classes inside the `com.revature.models` package
- `this`
  - Limit matching to JPs where the AOP proxy is being advised
- `target`
  - Limit matching to JPs where the target object is being proxied
- `args`
  - Limit matching to JPs where arguments are instances of the given types
- `@annotation`
  - Limit matching to JPs where the method is annotated by the given annotation

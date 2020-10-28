# Overview

The reason that drives a transition to Microservices is the ability to efficiently scale while having high availability of the application. This means we want to leverage horizontal scaling for individual, independent, aspects of the application. And simultaneously, allow part of the application to continue functioning even if other parts are failing.

The primary downside is the complexity. There are many challenges that must be overcome when developing and deploying a microservices application that do not apply for monolithic applications.

Some concepts that arise in regards to developing a microservices application are as follows:

## Service Discovery
As we scale further, we may have many instances of the same service, or simply just many different services. Primarily driven through the goal of horizontal scaling. This makes it very difficult to track each one individually, particularly for any load balancer you might have.
The solution is automatically discover and track them.
Service Discovery is a system that tracks all of the services. Such as where they are, whether they are healthy, etc.
Specifically, Consul or Eureka have been used as solutions to Service Discovery. Eureka was developed by Netflix and used for many years, but has since stopped service. So Industry has transitioned to Consul, which was developed by Hashicorp.

Important note, Service Discovery servers ARE services themselves. Which means they can register themselves with other Service Discovery Servers. This is one reason why scaling Eureka was difficult (not impossible), and a Service Mesh solution, such as Consul, is more effective.

## Load Balancing

Load Balancing is the idea of distributing traffic across multiple instances to effectively handle large scale loads. In particular, there are 2 categories of load balancers: Client-Side and Server-Side Load Balancing.

Server-Side Load Balancer allow you to distrust the client, but still allow for effective routing.
This means that even if the client does not know where any of the services are, the server-side load balancer can accept the request and handle the routing itself. The downside is that this creates a bottleneck at the load balancer.

Client-Side Load Balancers require that you trust the client. We must accept that the client will choose which instance to the send the request, and if they do so incorrectly, this can cause issues in terms of performance.

Netflix created a Client-Side Load Balancer called Ribbon, which is used under the hood for most routing solutions today (but it is being phased out).

This does work very well when combined with service discovery solutions. This allows us to very conveniently inform the client about where to send the request.

## API Gateway

An API Gateway is a means of organizing traffic around/within a microservices application. Generally this has the added benefit of the organization being centralized. For example, some routes might be need to be protected via authorization. Some routes should only be accessible from other routes. We often want to fine-tune exactly how this communication is taking place.

We used Spring Cloud Gateway in our example, which happens to not be a service mesh solution. Istio is an example of a service mesh solution that is often used with Kubernetes.

## Circuit-Breaking

In order to improve the application's availability, we want to ensure there are fallbacks in case our inter-service communication breaks down. For example, what happens if one service fails completely? Will any other service that talks to the first one, also fail? By using fallbacks, we can instead allow partial successes instead of completely failing.

The concept/design pattern of Circuit Breaking is in regards to the lines of inter-service communication being broken instead of failing across them.

In our case, we used Spring Cloud CircuitBreaker (which was provided through Spring Cloud Gateway and OpenFeign), to create fallback methods which returned a default response.

Specifically, in our case, if the flashcard-service failed, we could continue to have partial functionality in terms of our quiz-service.

Under the Hood, Spring Cloud CircuitBreaker will use any implementation, such as Hystrix or Resilience4J. In our case, Hystrix which was created by Netflix is still being used under the hood (Provided through OpenFeign).

This did to lead to some issues, since Netflix has dropped support for Hystrix. We had to manually include an `rxjava-reactive-streams` dependency in our gateway-service. But this is steadily being improved as industry transitions off of Hystrix.

## Centralized Configuration

As we expanded and added more services to our architecture, it became more and more difficult to maintain the configuration. It became evident that we wanted to maintain a full version history and authorship tracking of any changes made to any service's configuration. In particular, having it in one centralized location is very valuable.

We leveraged Spring Cloud Config to store our configuration in a Git Repository, which provided the features we wanted. Then we had a config-service that was responsible for injecting the configuration into each other microservice.

Not all configuration solutions are centralized. Kubernetes supports ConfigMaps which allow for some configuration injection into services, but it does not take the exact same form as Spring Cloud Config. Similarly, Consul supports a Key/Value Store that can inject some key-value configurations, but is also not centralized.

Spring Cloud Config was created by Netflix.

## Distributed Databases and Eventual Consistency

Similarly with the application, we would like our databases to be horizontally scaled. However, this is more difficult to accomplish than with the application. There are several techniques that we can leverage to split our database into several smaller databases, but they aren't necessarily dynamically scaled.

In our case, we started by separating our databases across domain boundaries. Specifically, our one database that had both quizzes and flashcards was split into 2 databases. Additionally, while uncommon, we can leverage a few more advanced techniques.

Data/Database Sharding: You can split records from a single database across multiple instaces according to some predefined strategy. For example, the database used might depend on the entity's ID modulo 5. This can be done in the application layer (such as within your Spring Boot application). Or could be accomplished externally in a separate layer. We did not do this in our example.

Data Replication: We can produce multiple copies of a database to support more simultaneous READ operations, without needing to purchase a more expensive database with more resources. This does not improve the bandwidth for WRITE operations, since all the data that is replicated will need to be eventually consistent. So you'll have to also replicate each WRITE operation per database.

This is usually quite helpful, since READ operations are much more common than WRITE operations (although not always, depends on your application).

We did accomplish Data Replication in our example by having each instance of a service have its own database. Specifically, we were using H2 databases.

It is important that when using Data Replication as well as dependent data across domain boundaries, that we achieve eventual consistency. The data will unfortunately never be perfectly in sync (unless the application is not being used), but it is perfectly acceptable in many scenarios, to _eventually_ have consistent data.

We accomplish with some sort of Message Queue. In our case, we used Apache Kafka.

Kafka distributes messages into different "topics". Each _line of communication_ would be a single topic. And by default, each message will be consumed by only 1 consumer _per group-id_.

This allows Kafka to support many different use-cases. Some of which lie in the realm of Big Data, and data pipelines. However in our use-case, we wanted every replicated database to process every operation, so each consumer had a different group-id.

Additionally, Kafka uses Zookeeper to persist it's message data.

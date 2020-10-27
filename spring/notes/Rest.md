# Rest (Continued)
- REpresentational State Transfer
- Represent everything as a "resource"
- Characteristics/Traits/Properties
  1. Stateless
  2. Cacheable
  3. Uniform Interface
  4. Layered Architecture
  5. Client/Server Relationship
  6. (Optional) Code on Demand
- We previously talked about URI structure in a RESTful Web Application

## Richardson Maturity Model (RMM)
- Leonard Richardson broke down the steps of creating a RESTful API into three steps/levels
0. Remote Procedure Calls (RPC)
  - Absolute Chaos
  - No RESTful traits
  - Any HTTP Methods are used
  - No attention is paid to Response Status Codes
  - Often execute functionality instead of giving resources
1. Resources
  - API is now structured as a collection of resources
  - Includes URI naming conventions
  - But, still little attention is paid to HTTP Methods and Status Codes
2. HTTP Verb
  - Now correctly uses context of HTTP Verbs/Methods
    - GET: Requesting a Resource (Generally doesn't have a Request body)
    - POST: Send a resource/information to server (Generally does use the body)
    - PUT: Update a resource (Replacement of the resource, aside from ID)
    - PATCH: Update a resource (Partial Update)
    - DELETE: Deletes a resource (Generally doesn't use ID, but sometimes might)
    - TRACE: Returns the path the request takes through the internet
    - HEAD: Returns the headers of the Request
    - OPTIONS: Used for CORS Pre-Flight Requests
  - There is a concept of Safe Requests & Idempotent Requests
  - They are not mutually exclusive
    - Safe: The request does not alter the state of the server
      - GET, TRACE, HEAD, OPTIONS
    - Idempotent: Repeating the operation in sequence will always provide the same result
      - GET, PUT, PATCH, DELETE, HEAD
  - Proper Status Code Responses are provided
3. Hypermedia Controls - HATEOAS (Hypermedia As The Engine Of Application State)
  - Information on how to use API is included in the response
    - Ex: Think of the Poke API, with the example that we created
    - Often people will create a `links` attribute
    - With `href` or `src` attributes
  - Helps clients of the API to continue to work across changes

The RMM is a good way to get started with creating a RESTful API / RESTful Web Service, however it is not the end.

So there are many more aspects that we can improve.

For example, HTTP is not necessarily the only protocol that we can support.
We could expand the range of HTTP Verbs that we support. We could support ALL HTTP Verbs.
You could add support for "Vary" Headers, which allows you to customize responses based on other headers such as "Content-Type". You could send back JSON, XML, etc.

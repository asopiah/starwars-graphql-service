# starwars-graphql-service
**starwars-graphql-service** is a backend service that provides a GraphQL API for querying and retrieving data related to the Star Wars universe. It utilizes the GraphQL query language to provide a flexible and efficient way for clients to request only the data they need, while also allowing for multiple resources to be queried with a single request. The service is typically used as part of a larger application or system that requires access to Star Wars-related data.
## Getting Started
### Prerequisites
1. Java 17+
2. Maven
3. Node
4. Angular CLI
5. Docker

### How to  Run
1. Clone the parent repository with its submodules using the command below:
      ````
      git clone git@github.com:asopiah/starwars-graphql-service.git
      ````

2. ```cd starwars-graphql-service```
3. Run ````mvn install````
4. Run ````mvn spring-boot:run````
5. The backend server is running on [http://localhost:8080]()
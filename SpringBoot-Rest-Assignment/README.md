# REST API Demo
## INTRODUCTION
### Main application
* Technologies used - Spring Boot, Spring Data JPA, Swagger
* Embedded Server - H2
* Start the application by running [ProductRestStarter class](src/main/java/com/springboot/ProductRestStarter.java)
* Configuration file - [application.properties](src/main/resources/application.properties)
* data.sql and schema.sql will be automatically populated during startup.
* Swagger generated API doc can be accessed from [localhost:port/swagger-ui.html](localhost:8009/swagger-ui.html)

### Product property
* Integer id
* String name
* Integer quantity
* Double totalprice

### Exceptions
* ProductNotFoundException
* ProductNotAvailableException

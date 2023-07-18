# API example for "Practice 2023 @PS"

## Student API

[![CI Status](https://github.com/concosminx/student-api/actions/workflows/gradle.yml/badge.svg)](https://github.com/concosminx/student-api/actions/workflows/gradle.yml)

## General requirements
- The source code will be available on GitHub, in a separate, public repository. Check slides and [how-to-use-git](https://phoenixnap.com/kb/how-to-use-git)
- The repository will contain everything necessary and sufficient for compilation and running (configuration files, popular scripts, database - if applicable, etc.). See [ignore files in git](https://www.freecodecamp.org/news/gitignore-file-how-to-ignore-files-and-folders-in-git/)
- The application will be compiled using gradle (preferred) or maven (accepted)
- In addition to the source code, the repository will also contain a README.md type file in which the project is briefly described. See [markdowns basics](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax) and [some examples](https://bulldogjob.com/readme/how-to-write-a-good-readme-for-your-github-project).
- The APIs will be built using Spring Boot. See [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/), [What is REST](https://restfulapi.net/) and [spring initializr project](https://start.spring.io/).

## Specific requirements

### 1. JSON and XML

Your API must communicate via JSON and/or XML. Make sure you design your API to be intuitive and easy to use by naming your resources in a logical manner.

See:
- The course slides
- Guide: [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
- Article: [Consuming XML in Spring Boot REST](https://www.appsdeveloperblog.com/consuming-xml-in-spring-boot-rest/)

Hints:
- Check the `build.gradle` file to see if extra dependencies are added

### 2. Persistence

For resource storage, you must use persistent storage (H2 or another PostgreSQL/MySQL variant can be opted for).
You can opt for JDBC or JPA.

If the project does not involve the storage of resources, the way in which you used APIs or external resources will be scored, as the case may be.

See:
- Guide: [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- Guide: [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
- Article: [Spring Boot With H2 Database](https://www.baeldung.com/spring-boot-h2-database)
- Guide: [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest)

Hints:
- N/A
### 3. Load some data at startup

Prepopulate the database with registrations at startup (manage the situation depending on the chosen database).

See:
- [Spring Boot Runners](https://www.tutorialspoint.com/spring_boot/spring_boot_runners.htm)
- [Examples](https://www.javaguides.net/2020/02/spring-boot-commandlinerunner-example.html)

### 4. HTTP Response status codes

Make sure your API responds with the appropriate status codes for each situation it reaches in processing a request (eg 200 - Ok, 404 - Not Found, 500 - Internal Server Error, etc.)

See: 
- [Specific HTTP Response Status](https://www.amitph.com/spring-return-specific-http-status/)
- [Error Handling](https://www.toptal.com/java/spring-boot-rest-api-error-handling)

### 5. Postman | or similar tool

Test the API with Postman (or a similar tool).
If you use Postman create a POSTMAN collection ( https://www.getpostman.com/ ) of requests for each call that can be made in your API, export it and save it in the REPO along with the code source.

See:
- [Alternatives](https://katalon.com/resources-center/blog/postman-alternatives-api-testing)
- [Basics](https://www.geeksforgeeks.org/basics-of-api-testing-using-postman/)

### 6. Java Client for API

Create a client for your API no matter what technology you choose:
- it can be a small application (HTML / CSS / JavaScript)
- it can be a simple Java application that uses an HTTP client ( [client examples](https://reflectoring.io/comparison-of-java-http-clients) )
- an application in a dedicated JavaScript framework (React, Angular, etc.)

_Note: if the client is not integrated in the initial application (static folder) it will be stored in a separate repository or you will organize the main repository from the beginning in 2 folders: api and client._

See:
- [Java HTTP Client Comparison](https://www.wiremock.io/post/java-http-client-comparison)
- [Java 8 Date/Time issue](https://howtodoinjava.com/jackson/java-8-date-time-type-not-supported-by-default/)
- [JavaScript](https://medium.com/javarevisited/3-best-ways-to-learn-javascript-from-scratch-books-courses-and-projects-be995d4cd964)
- [Client side frameworks](https://developer.mozilla.org/en-US/docs/Learn/Tools_and_testing/Client-side_JavaScript_frameworks/Introduction)


### 7. Automated testing [BONUS]

Unit and Integration Testing: Implementing unit tests and/or integration tests to ensure the correct functionality of the developed REST services. You can use frameworks like JUnit and Mockito to perform these tests.

See:
- [Testing the Web Layer](https://spring.io/guides/gs/testing-web/)
- [Testing in Spring Boot](https://www.baeldung.com/spring-boot-testing)

### 8. API Documentation [BONUS]

API Documentation: Using tools like Swagger or Springfox to generate interactive REST API documentation and make it easier to test and understand services.

See:
- [SpringDOC](https://springdoc.org/)
- [Spring Boot and Swagger 3](https://www.bezkoder.com/spring-boot-swagger-3/)
- [Setting up Swagger 2](https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api)
- [Documenting a Spring Rest API](https://www.baeldung.com/spring-rest-openapi-documentation)

Hint:
- You may encounter problems in the configuration for Spring Boot 3. Check that the analyzed resources refer to the version of Spring Boot chosen for the project.

### 9. Docker [BONUS]

Create a Docker image based on your application. Start a container based on the created image and check if your API is functional.

See:
- [Spring Boot Docker](https://spring.io/guides/topicals/spring-boot-docker/)
- [Dockerizing Spring Boot App](https://www.baeldung.com/dockerizing-spring-boot-application)

### 10. CI/CD with GithubActions

Create a workflow (workflow) in GitHub Actions that automates the compilation and testing (if there are tests) of the application after each commit.

See:
- [Learn GitHub Actions](https://docs.github.com/en/actions)
- [Executing gradle builds](https://docs.gradle.org/current/userguide/github-actions.html)

Hints:
- Error if you are using windows ([Gradlew: Permission denied](https://github.com/actions/starter-workflows/issues/171))

### 11. Spring Security [BONUS]

Secure access to the developed API using Spring Security with in-memory users.

See:
- [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
- [In Memory Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html)
- [Authorize Basic Auth requests in Spring Boot Swagger](https://keepgrowing.in/java/springboot/how-to-secure-spring-boot-swagger-ui-with-basic-authentication/)
- [Configure JWT Authentication for OpenAPI](https://www.baeldung.com/openapi-jwt-authentication)


Hints:
- Adding Spring Security as a dependency may have a negative impact on existing functionality for the H2 console, Swagger UI, existing automated tests.
  You will have to take measures so that they can continue to be used ([example](https://github.com/spring-projects/spring-security/issues/12310#issuecomment-1328990026))

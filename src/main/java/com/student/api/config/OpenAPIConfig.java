package com.student.api.config;

import java.util.List;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

  private static final String SCHEME_NAME = "basicAuth";
  private static final String SCHEME = "basic";

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl("http://localhost:8080");
    devServer.setDescription("Server URL in Development environment");

    Server prodServer = new Server();
    prodServer.setUrl("http://prd-server:8080");
    prodServer.setDescription("Server URL in Production environment");

    Contact contact = new Contact();
    contact.setEmail("somemail@gmail.com");
    contact.setName("Student UPIT");
    contact.setUrl("https://www.someurl.com");

    License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

    Info info = new Info()
        .title("Student Management API")
        .version("1.0")
        .contact(contact)
        .description("This API exposes endpoints to manage students.")
        .license(mitLicense);

    return new OpenAPI()
        .info(info)
        //.components(new Components()
        //    .addSecuritySchemes(SCHEME_NAME, createSecurityScheme()))
        //.addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
        .servers(List.of(devServer, prodServer));
  }

//  private SecurityScheme createSecurityScheme() {
//    return new SecurityScheme()
//        .name(SCHEME_NAME)
//        .type(SecurityScheme.Type.HTTP)
//        .scheme(SCHEME);
//  }
}

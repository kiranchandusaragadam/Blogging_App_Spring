package com.springboot.blogsmanagementsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
		info = @Info(
				title = "Blogging_App_Spring",
				version = "1.0.0",
				description = "This is Blogs Management System backend project, that features Rest APIs to perform CRUD operations on different entities like User, Post, Category and Comment.",
				contact = @Contact(
						name = "Kiranchandu Saragadam",
						email = "kiransaragadam247@gmail.com",
						url = "https://github.com/kiranchandusaragadam/Blogging_App_Spring"
				)
		)
)
@SpringBootApplication
public class BlogsManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogsManagementSystemApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}

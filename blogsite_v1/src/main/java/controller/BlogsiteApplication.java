package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"DAO","controller"})
@SpringBootApplication
public class BlogsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogsiteApplication.class, args);
	}

}

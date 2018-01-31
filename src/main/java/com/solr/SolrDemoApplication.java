package com.solr;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class SolrDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SolrDemoApplication.class, args);
		}
	
	
}

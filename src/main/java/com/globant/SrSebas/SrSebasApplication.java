package com.globant.SrSebas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"controller", "business", "db.domain.People.service.implementation", "utils"})
@EnableMongoRepositories("db.domain.People.repo")
public class SrSebasApplication {
	public static void main(String[] args) {SpringApplication.run(SrSebasApplication.class, args);}
}

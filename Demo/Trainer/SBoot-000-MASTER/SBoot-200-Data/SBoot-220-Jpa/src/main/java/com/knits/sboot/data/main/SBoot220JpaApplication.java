package com.knits.sboot.data.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.knits.sboot.common.data.entities.User;
import com.knits.sboot.data.dao.UserRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EntityScan( basePackages = {"com.knits.sboot.common.data.entities"} )
@EnableJpaRepositories({"com.knits.sboot.data"})
@EnableTransactionManagement
public class SBoot220JpaApplication {

	
	public static void main(String[] args) {		
		SpringApplication.run(SBoot220JpaApplication.class,args);
	}

	
	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			
			System.out.println("In CommandLineRunner ...");
			
			// save a few users
			repository.save(new User("Jack", "Bauer"));
			repository.save(new User("Chloe", "O'Brian"));
			repository.save(new User("Kim", "Bauer"));
			repository.save(new User("David", "Palmer"));
			repository.save(new User("Michelle", "Dessler"));

			// fetch all users
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (User customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual users by ID
			User customer = repository.findById(1L).orElse(new User("Not Found", "Not found"));
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch users by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
		
			log.info("");
		};
	}
}

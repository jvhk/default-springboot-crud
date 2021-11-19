package com.api;

import java.util.stream.LongStream;

import com.api.model.Contact;
import com.api.repository.ContactRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCloudMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudMysqlApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ContactRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 11)
					.mapToObj(i -> {
						Contact c = new Contact();
						c.setName("Joao " + i);
						c.setEmail("Joao" + i + "@email.com");
						c.setPhone("(111) 111-1111");
						return c;
					})
					.map(v -> repository.save(v))
					.forEach(System.out::println);
		};
	}

}

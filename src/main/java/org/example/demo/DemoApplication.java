package org.example.demo;


import org.example.demo.entities.Customer;
import org.example.demo.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
		return args -> {
			customerRepository.save(new Customer(null, "12345", "Jasper Diaz", 15000.00, 5, "Savings-Deluxe"));
			customerRepository.save(new Customer(null, "67890", "Zanip Mendez", 5000.00, 2, "Savings-Deluxe"));
			customerRepository.save(new Customer(null, "11223", "Geronima Esper", 6000.00, 5, "Savings-Regular"));
			customerRepository.findAll().forEach(p -> {
				System.out.println(p.getCustomerName());
			});
		};
	}
}

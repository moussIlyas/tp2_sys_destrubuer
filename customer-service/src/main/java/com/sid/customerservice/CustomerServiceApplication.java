package com.sid.customerservice;

import com.sid.customerservice.Entities.Customer;
import com.sid.customerservice.config.CustomerConfigParams;
import com.sid.customerservice.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner seedMockCustomers(CustomerRepository customerRepository) {
        return args -> {
            if (customerRepository.count() > 0) {
                log.info("Mock data already present, skipping");
                return;
            }
            List<Customer> customers = List.of(
                    Customer.builder().name("Alice Martin").email("alice.martin@example.com").build(),
                    Customer.builder().name("Bob Dupont").email("bob.dupont@example.com").build(),
                    Customer.builder().name("Carla Rossi").email("carla.rossi@example.com").build(),
                    Customer.builder().name("Diego Alvarez").email("diego.alvarez@example.com").build(),
                    Customer.builder().name("Emma Wilson").email("emma.wilson@example.com").build()
            );
            customerRepository.saveAll(customers);
            log.info("Seeded {} mock customers", customers.size());
        };
    }
}

package com.sid.invantoryservice;

import com.sid.invantoryservice.Entities.Product;
import com.sid.invantoryservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InvantoryServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(InvantoryServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(InvantoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner seedMockProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() > 0) {
                log.info("Mock data already present, skipping");
                return;
            }
            List<Product> products = List.of(
                    Product.builder().name("Wireless Mouse").price(29.99).quantity(120).build(),
                    Product.builder().name("Mechanical Keyboard").price(89.50).quantity(45).build(),
                    Product.builder().name("27\" Monitor").price(249.00).quantity(30).build(),
                    Product.builder().name("USB-C Hub").price(39.90).quantity(80).build(),
                    Product.builder().name("Noise Cancelling Headphones").price(199.99).quantity(25).build()
            );
            productRepository.saveAll(products);
            log.info("Seeded {} mock products", products.size());
        };
    }
}

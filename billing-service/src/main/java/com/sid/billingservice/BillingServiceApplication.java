package com.sid.billingservice;

import com.sid.billingservice.entities.Bill;
import com.sid.billingservice.entities.ProductItem;
import com.sid.billingservice.repository.BillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BillingServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(BillingServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner seedMockBills(BillRepository billRepository) {
        return args -> {
            if (billRepository.count() > 0) {
                log.info("Mock data already present, skipping");
                return;
            }
            List<Bill> bills = List.of(
                    bill(1L, new Date(),
                            ProductItem.builder().productId(1L).quantity(2).price(29.99).build(),
                            ProductItem.builder().productId(2L).quantity(1).price(89.50).build()),
                    bill(2L, new Date(),
                            ProductItem.builder().productId(3L).quantity(1).price(249.00).build()),
                    bill(3L, new Date(),
                            ProductItem.builder().productId(4L).quantity(3).price(39.90).build(),
                            ProductItem.builder().productId(5L).quantity(1).price(199.99).build()),
                    bill(4L, new Date(),
                            ProductItem.builder().productId(2L).quantity(2).price(89.50).build()),
                    bill(5L, new Date(),
                            ProductItem.builder().productId(1L).quantity(5).price(29.99).build(),
                            ProductItem.builder().productId(5L).quantity(2).price(199.99).build())
            );
            billRepository.saveAll(bills);
            log.info("Seeded {} mock bills", bills.size());
        };
    }

    private static Bill bill(long customerId, Date date, ProductItem... items) {
        Bill bill = Bill.builder()
                .customerId(customerId)
                .date(date)
                .items(new ArrayList<>(List.of(items)))
                .build();
        for (ProductItem item : items) {
            item.setBill(bill);
        }
        return bill;
    }

}

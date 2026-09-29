package com.sid.billingservice.feign;

import com.sid.billingservice.models.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer-service")
public interface CustomerServiceRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "customer-service" ,fallbackMethod = "getDefaultCustomer")
    Customer findByCustomerId(@PathVariable("id") Long id);

    default Customer getDefaultCustomer(Long id ,Exception e) {
Customer customer = new Customer();
customer.setId(id);
customer.setEmail("defualt@gmail.com");
customer.setName("defualt");

return customer;
    }


}

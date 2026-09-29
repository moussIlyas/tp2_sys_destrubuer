package com.sid.billingservice.feign;

import com.sid.billingservice.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer-service")
public interface CustomerServiceRestClient {
    @GetMapping("customer/{id}")
    Customer findByCustomerId(@PathVariable Long id);



}

package com.sid.billingservice.feign;


import com.sid.billingservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "invantory-service")
public interface InentoryServiceRrestClient {

    @GetMapping("/products/{id}")
    Product findByProductId(@PathVariable("id") Long id);


}

package com.sid.billingservice.feign;


import com.sid.billingservice.entities.ProductItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "invantory-service")
public interface InentoryServiceRrestClient {

    @GetMapping("/products/{id}")
    ProductItem findByProductId(@PathVariable Long id);


}

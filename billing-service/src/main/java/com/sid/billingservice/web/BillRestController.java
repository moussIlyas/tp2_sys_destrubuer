package com.sid.billingservice.web;


import com.sid.billingservice.entities.Bill;
import com.sid.billingservice.entities.ProductItem;
import com.sid.billingservice.feign.CustomerServiceRestClient;
import com.sid.billingservice.feign.InentoryServiceRrestClient;
import com.sid.billingservice.models.Product;
import com.sid.billingservice.repository.BillRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BillRestController {

    private final BillRepository billRepository;
    private final InentoryServiceRrestClient inentoryServiceRrestClient;
    private final CustomerServiceRestClient customerServiceRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable("id") Long billId) {

        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bill not found: " + billId));

        bill.setCustomer(customerServiceRestClient.findByCustomerId(bill.getCustomerId()));

        Map<Long, Product> products = bill.getItems().stream()
                .map(ProductItem::getProductId)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), inentoryServiceRrestClient::findByProductId));

        bill.getItems().forEach(item -> item.setProduct(products.get(item.getProductId())));

        return bill;
    }

}

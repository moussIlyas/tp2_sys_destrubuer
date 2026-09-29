package com.sid.billingservice.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Long id ;
    private String name;
    private double price ;
    private int quantity;
}

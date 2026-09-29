package com.sid.billingservice.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
private Long Id;
private String name;
private String email;
}

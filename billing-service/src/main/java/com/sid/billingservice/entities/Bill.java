package com.sid.billingservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private long customerId;
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<ProductItem> items;


}

package com.sid.customerservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "customer.params")
public class CustomerConfigParams {

    private int x;
    private int y;
}

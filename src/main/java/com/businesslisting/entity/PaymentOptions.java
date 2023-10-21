package com.businesslisting.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("payment_options")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOptions {
    @Id
    private Integer id;
    private String paymentOption;
}

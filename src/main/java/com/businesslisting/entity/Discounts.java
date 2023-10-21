package com.businesslisting.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("discounts")
@AllArgsConstructor
@NoArgsConstructor
public class Discounts {
    @Id
    private Integer id;
    private Integer discount;
}

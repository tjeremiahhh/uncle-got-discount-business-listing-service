package com.businesslisting.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("days")
@AllArgsConstructor
@NoArgsConstructor
public class Days {
    @Id
    private Integer id;
    private String day;
}

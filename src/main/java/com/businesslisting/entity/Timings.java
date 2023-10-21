package com.businesslisting.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("timings")
@AllArgsConstructor
@NoArgsConstructor
public class Timings {
    @Id
    private Integer id;
    private String time;
}

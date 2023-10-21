package com.businesslisting.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.businesslisting.dto.BusinessListingSpecialConditionsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("business_listing_special_conditions")
@AllArgsConstructor
@NoArgsConstructor
public class BusinessListingSpecialConditions {
    @Id
    private Integer id;
    private Integer businessListingId;
    private String condition;

    public BusinessListingSpecialConditions(BusinessListingSpecialConditionsDTO businessListingSpecialConditionsDTO) {
        this.id = businessListingSpecialConditionsDTO.getId();
        this.businessListingId = businessListingSpecialConditionsDTO.getBusinessListingId();
        this.condition = businessListingSpecialConditionsDTO.getCondition();
    }
}

package com.businesslisting.dto;

import com.businesslisting.entity.BusinessListingSpecialConditions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessListingSpecialConditionsDTO {
    private Integer id;
    private Integer businessListingId;
    private String condition;

    public BusinessListingSpecialConditionsDTO(BusinessListingSpecialConditions businessListingSpecialConditions) {
        this.id = businessListingSpecialConditions.getId();
        this.businessListingId = businessListingSpecialConditions.getBusinessListingId();
        this.condition = businessListingSpecialConditions.getCondition();
    }
}

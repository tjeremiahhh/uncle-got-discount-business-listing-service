package com.businesslisting.dto;

import com.businesslisting.entity.BusinessListingDiscounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessListingDiscountsDTO {
    private Integer id;
    private Integer businessListingId;
    private Integer dayId;
    private Integer timingsId;
    private Integer discountsId;
    private Integer maxHeadCount;
    
    public BusinessListingDiscountsDTO(BusinessListingDiscounts businessListingDiscounts) {
        this.id = businessListingDiscounts.getId();
        this.businessListingId = businessListingDiscounts.getBusinessListingId();
        this.dayId = businessListingDiscounts.getDayId();
        this.timingsId = businessListingDiscounts.getTimingsId();
        this.discountsId = businessListingDiscounts.getDiscountsId();
        this.maxHeadCount = businessListingDiscounts.getMaxHeadCount();
    }
}

package com.businesslisting.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.businesslisting.dto.BusinessListingDiscountsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("business_listing_discounts")
@AllArgsConstructor
@NoArgsConstructor
public class BusinessListingDiscounts {
    @Id
    private Integer id;
    private Integer businessListingId;
    private Integer dayId;
    private Integer timingsId;
    private Integer discountsId;
    private Integer maxHeadCount;

    public BusinessListingDiscounts(BusinessListingDiscountsDTO businessListingDiscountsDTO) {
        this.id = businessListingDiscountsDTO.getId();
        this.businessListingId = businessListingDiscountsDTO.getBusinessListingId();
        this.dayId = businessListingDiscountsDTO.getDayId();
        this.timingsId = businessListingDiscountsDTO.getTimingsId();
        this.discountsId = businessListingDiscountsDTO.getDiscountsId();
        this.maxHeadCount = businessListingDiscountsDTO.getMaxHeadCount();
    }
}

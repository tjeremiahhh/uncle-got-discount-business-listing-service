package com.businesslisting.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessListingAndDiscountDTO {
    private BusinessListingDTO businessListing;
    private BusinessListingDiscountsDTO businessListingDiscounts;
}

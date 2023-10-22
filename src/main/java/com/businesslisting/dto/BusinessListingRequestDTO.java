package com.businesslisting.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessListingRequestDTO {
    private BusinessListingDTO businessListing;
    private BusinessListingDescriptionDTO businessListingDescription;
    private BusinessListingSpecialConditionsDTO businessListingSpecialConditions;
    private List<BusinessListingDiscountsDTO> businessListingDiscounts;
}

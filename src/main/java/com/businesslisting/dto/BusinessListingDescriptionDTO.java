package com.businesslisting.dto;

import com.businesslisting.entity.BusinessListingDescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessListingDescriptionDTO {
    private Integer id;
    private Integer businessListingId;
    private String about;
    private String cuisines;
    private String atmospheres;
    private String languages;
    private String paymentOptions;

    public BusinessListingDescriptionDTO(BusinessListingDescription businessListingDescription) {
        this.id = businessListingDescription.getId();
        this.businessListingId = businessListingDescription.getBusinessListingId();
        this.about = businessListingDescription.getAbout();
        this.cuisines = businessListingDescription.getCuisines();
        this.atmospheres = businessListingDescription.getAtmospheres();
        this.languages = businessListingDescription.getLanguages();
        this.paymentOptions = businessListingDescription.getPaymentOptions();
    }
}

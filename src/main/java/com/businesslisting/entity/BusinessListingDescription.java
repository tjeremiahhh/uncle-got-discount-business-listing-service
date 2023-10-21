package com.businesslisting.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.businesslisting.dto.BusinessListingDescriptionDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("business_listing_description")
@AllArgsConstructor
@NoArgsConstructor
public class BusinessListingDescription {
    @Id
    private Integer id;
    private Integer businessListingId;
    private String about;
    private String cuisines;
    private String atmospheres;
    private String languages;
    private String paymentOptions;

    public BusinessListingDescription(BusinessListingDescriptionDTO businessListingDescriptionDTO) {
        this.id = businessListingDescriptionDTO.getId();
        this.businessListingId = businessListingDescriptionDTO.getBusinessListingId();
        this.about = businessListingDescriptionDTO.getAbout();
        this.cuisines = businessListingDescriptionDTO.getCuisines();
        this.atmospheres = businessListingDescriptionDTO.getAtmospheres();
        this.languages = businessListingDescriptionDTO.getLanguages();
        this.paymentOptions = businessListingDescriptionDTO.getPaymentOptions();
    }
}

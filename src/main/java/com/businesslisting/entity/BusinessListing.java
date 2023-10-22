package com.businesslisting.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.businesslisting.dto.BusinessListingDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table("business_listings")
@AllArgsConstructor
@NoArgsConstructor
public class BusinessListing {
    @Id
    private Integer id;
    private Integer userId;
    private String outletName;
    private String emailAddress;
    private Integer phoneNumber;
    private String address;
    private byte[] imageFile;
    private String websiteUrl;
    private Boolean allowPublicHoliday;
    private String menuUrl;
    private Boolean halalCertified;

    public BusinessListing(BusinessListingDTO businessListingDTO) {
        this.id = businessListingDTO.getId();
        this.userId = businessListingDTO.getUserId();
        this.outletName = businessListingDTO.getOutletName();
        this.emailAddress = businessListingDTO.getEmailAddress();
        this.phoneNumber = businessListingDTO.getPhoneNumber();
        this.address = businessListingDTO.getAddress();
        this.imageFile = businessListingDTO.getImageFile();
        this.websiteUrl = businessListingDTO.getWebsiteUrl();
        this.allowPublicHoliday = businessListingDTO.getAllowPublicHoliday();
        this.menuUrl = businessListingDTO.getMenuUrl();
        this.halalCertified = businessListingDTO.getHalalCertified();
    }
}

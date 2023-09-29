package com.businesslisting.dto;

import com.businesslisting.entity.BusinessListing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessListingDTO {
    private Integer id;
    private Integer userId;
    private String outletName;
    private String emailAddress;
    private Integer phoneNumber;
    private String address;
    private byte[] imageFile;
    private String websiteUrl;
    private boolean allowPublicHoliday;
    private String menuUrl;
    private boolean halalCertified;

    public BusinessListingDTO(BusinessListing businessListing) {
        this.id = businessListing.getId();
        this.userId = businessListing.getUserId();
        this.outletName = businessListing.getOutletName();
        this.emailAddress = businessListing.getEmailAddress();
        this.phoneNumber = businessListing.getPhoneNumber();
        this.address = businessListing.getAddress();
        this.imageFile = businessListing.getImageFile();
        this.websiteUrl = businessListing.getWebsiteUrl();
        this.allowPublicHoliday = businessListing.isAllowPublicHoliday();
        this.menuUrl = businessListing.getMenuUrl();
        this.halalCertified = businessListing.isHalalCertified();
    }
}

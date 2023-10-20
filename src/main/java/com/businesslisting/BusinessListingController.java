package com.businesslisting;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.businesslisting.dto.BusinessListingDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/business-listing/")
@RequiredArgsConstructor
public class BusinessListingController {

    private final BusinessListingService businessListingService;

    @PostMapping("create-business-listing")
    public BusinessListingDTO createBusinessListing(@RequestBody BusinessListingDTO businessListingDTO) {
        return businessListingService.createOrUpdateBusinessListing(businessListingDTO);
    }

    @GetMapping("get-business-listing")
    public BusinessListingDTO getBusinessListing(@RequestParam Integer id) {
        return businessListingService.getBusinessListingById(id);
    }

    @GetMapping(value = "get-all-business-listings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BusinessListingDTO> getAllBusinessListings() {
        return businessListingService.getAllBusinessListings();
    }

    @PostMapping("update-business-listing")
    public BusinessListingDTO updateBusinessListing(@RequestBody BusinessListingDTO businessListingDTO) {
        return businessListingService.createOrUpdateBusinessListing(businessListingDTO);
    }

    @DeleteMapping("delete-business-listing")
    public void deleteBusinessListing(@RequestParam Integer id) {
        businessListingService.deleteBusinessListing(id);
    }
}

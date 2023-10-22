package com.businesslisting;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.businesslisting.dto.BusinessListingAndDiscountDTO;
import com.businesslisting.dto.BusinessListingDTO;
import com.businesslisting.dto.BusinessListingDescriptionDTO;
import com.businesslisting.dto.BusinessListingDescriptionDetailsDTO;
import com.businesslisting.dto.BusinessListingDiscountsDTO;
import com.businesslisting.dto.BusinessListingRequestDTO;
import com.businesslisting.dto.BusinessListingSpecialConditionsDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/business-listing/")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = { "http://localhost:4200",
        "http://ugd-frontend-app-lb-1592138430.ap-southeast-1.elb.amazonaws.com" })
public class BusinessListingController {

    private final BusinessListingService businessListingService;

    @PostMapping(value = "create-business-listing", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createBusinessListing(@RequestBody BusinessListingRequestDTO businessListingRequestDTO) throws IOException {

        // ObjectMapper mapper = new ObjectMapper();

        // BusinessListingDTO businessListingDTO = mapper.readValue(businessListing, BusinessListingDTO.class);
        // BusinessListingDescriptionDTO businessListingDescriptionDTO = mapper.readValue(businessListingDescription,
        //         BusinessListingDescriptionDTO.class);
        
        // BusinessListingSpecialConditionsDTO businessListingSpecialConditionsDTO = new BusinessListingSpecialConditionsDTO();
        // if (businessListingSpecialConditions != null) {
        //     businessListingSpecialConditionsDTO = mapper
        //             .readValue(businessListingSpecialConditions, BusinessListingSpecialConditionsDTO.class);
            
        // } else {
        //     businessListingSpecialConditionsDTO = null;
        // }

        // List<BusinessListingDiscountsDTO> businessListingDiscountsDTO = mapper.readValue(businessListingDiscounts,
        //             new TypeReference<List<BusinessListingDiscountsDTO>>() {
        //             });

        businessListingService.createOrUpdateBusinessListing(businessListingRequestDTO);
    }

    @GetMapping("get-business-listing")
    public BusinessListingRequestDTO getBusinessListing(@RequestParam Integer id) {
        return businessListingService.getBusinessListingById(id);
    }

    @GetMapping(value = "get-all-business-listings", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BusinessListingDTO> getAllBusinessListings() {
        return businessListingService.getAllBusinessListings();
    }

    // @PostMapping(value = "update-business-listing", consumes =
    // MediaType.MULTIPART_FORM_DATA_VALUE, produces =
    // MediaType.APPLICATION_JSON_VALUE)
    // public void updateBusinessListing(@RequestParam("businessListing") String
    // businessListing,
    // @RequestParam("businessListingDescription") String
    // businessListingDescription,
    // @RequestParam("businessListingSpecialConditions") String
    // businessListingSpecialConditions,
    // @RequestParam("businessListingDiscounts") String businessListingDiscounts,
    // @RequestParam(value = "logoFile", required = false) MultipartFile logoFile)
    // throws IOException {

    // ObjectMapper mapper = new ObjectMapper();

    // BusinessListingDTO businessListingDTO = mapper.readValue(businessListing,
    // BusinessListingDTO.class);
    // BusinessListingDescriptionDTO businessListingDescriptionDTO =
    // mapper.readValue(businessListingDescription,
    // BusinessListingDescriptionDTO.class);
    // BusinessListingSpecialConditionsDTO businessListingSpecialConditionsDTO =
    // mapper
    // .readValue(businessListingSpecialConditions,
    // BusinessListingSpecialConditionsDTO.class);
    // List<BusinessListingDiscountsDTO> businessListingDiscountsDTO =
    // mapper.readValue(businessListingDiscounts,
    // new TypeReference<List<BusinessListingDiscountsDTO>>(){});

    // businessListingService.createOrUpdateBusinessListing(businessListingDTO,
    // businessListingDescriptionDTO,
    // businessListingSpecialConditionsDTO, businessListingDiscountsDTO, logoFile);
    // }

    @DeleteMapping("delete-business-listing")
    public void deleteBusinessListing(@RequestParam Integer id) {
        businessListingService.deleteBusinessListing(id);
    }

    @GetMapping(value = "get-business-listing-description-details", produces = MediaType.APPLICATION_JSON_VALUE)
    public BusinessListingDescriptionDetailsDTO getBusinessListingDescriptionDetails() {
        return businessListingService.getBusinessListingDescriptionDetails();
    }

    // @PostMapping(value = "create-business-listing-description", consumes =
    // MediaType.APPLICATION_JSON_VALUE, produces =
    // MediaType.APPLICATION_JSON_VALUE)
    // public BusinessListingDescriptionDTO createBusinessListingDescription(
    // @RequestBody BusinessListingDescriptionDTO businessListingDescriptionDTO) {
    // return
    // businessListingService.createOrUpdateBusinessListingDescription(businessListingDescriptionDTO);
    // }

    // @PostMapping(value = "create-business-listing-special-conditions", consumes =
    // MediaType.APPLICATION_JSON_VALUE, produces =
    // MediaType.APPLICATION_JSON_VALUE)
    // public BusinessListingSpecialConditionsDTO
    // createBusinessListingSpecialConditions(
    // @RequestBody BusinessListingSpecialConditionsDTO
    // businessListingSpecialConditionsDTO) {
    // return
    // businessListingService.createBusinessListingSpecialConditions(businessListingSpecialConditionsDTO);
    // }

    // @PostMapping(value = "create-business-listing-discounts", consumes =
    // MediaType.APPLICATION_JSON_VALUE, produces =
    // MediaType.APPLICATION_JSON_VALUE)
    // public void createBusinessListingDiscounts(
    // @RequestBody List<BusinessListingDiscountsDTO> businessListingDiscountsDTO) {
    // businessListingService.createBusinessListingDiscounts(businessListingDiscountsDTO);
    // }

    @GetMapping(value = "get-business-listing-id-by-userid", produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getBusinessListingIdByUserid(Integer userId) {
        return businessListingService.getBusinessListingIdByUserid(userId);
    }

    @GetMapping(value = "get-business-listing-and-discount", produces = MediaType.APPLICATION_JSON_VALUE)
    public BusinessListingAndDiscountDTO getBusinessListingAndDiscount(Integer discountId) {
        return businessListingService.getBusinessListingAndDiscount(discountId);
    }
}

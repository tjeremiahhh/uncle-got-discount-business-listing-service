package com.businesslisting;

import java.io.IOException;
import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.businesslisting.dto.BusinessListingDTO;
import com.businesslisting.dto.BusinessListingDescriptionDTO;
import com.businesslisting.dto.BusinessListingDescriptionDetailsDTO;
import com.businesslisting.dto.BusinessListingDiscountsDTO;
import com.businesslisting.dto.BusinessListingRequestDTO;
import com.businesslisting.dto.BusinessListingSpecialConditionsDTO;
import com.businesslisting.entity.Atmospheres;
import com.businesslisting.entity.BusinessListing;
import com.businesslisting.entity.BusinessListingDescription;
import com.businesslisting.entity.BusinessListingDiscounts;
import com.businesslisting.entity.BusinessListingSpecialConditions;
import com.businesslisting.entity.Cuisines;
import com.businesslisting.entity.Days;
import com.businesslisting.entity.Discounts;
import com.businesslisting.entity.PaymentOptions;
import com.businesslisting.entity.Timings;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessListingService {

    private final BusinessListingRepository businessListingRepository;

    @Transactional
    public void createOrUpdateBusinessListing(BusinessListingDTO businessListingDTO,
            BusinessListingDescriptionDTO businessListingDescriptionDTO,
            BusinessListingSpecialConditionsDTO businessListingSpecialConditionsDTO,
            List<BusinessListingDiscountsDTO> businessListingDiscountsDTO,
            MultipartFile logoFile) throws IOException {

        BusinessListing businessListing = new BusinessListing(businessListingDTO);
        businessListing.setImageFile(logoFile.getBytes());
        businessListingRepository.save(businessListing);
        
        BusinessListingDescription businessListingDescription = new BusinessListingDescription(businessListingDescriptionDTO);
        businessListingDescription.setBusinessListingId(businessListing.getId());
        businessListingRepository.save(businessListingDescription);

        BusinessListingSpecialConditions businessListingSpecialConditions = new BusinessListingSpecialConditions(businessListingSpecialConditionsDTO);
        businessListingSpecialConditions.setBusinessListingId(businessListing.getId());
        businessListingRepository.save(businessListingSpecialConditions);

        for (BusinessListingDiscountsDTO discount : businessListingDiscountsDTO) {
            BusinessListingDiscounts businessListingDiscounts = new BusinessListingDiscounts(discount);
            businessListingRepository.save(businessListingDiscounts);
        }
    }

    public BusinessListingRequestDTO getBusinessListingById(Integer id) {
        BusinessListingRequestDTO businessListingRequest = new BusinessListingRequestDTO();
        businessListingRequest.setBusinessListing(businessListingRepository.findById(id));
        businessListingRequest.setBusinessListingDescription(businessListingRepository.getBusinessListingDescription(id));
        businessListingRequest.setBusinessListingSpecialConditions(businessListingRepository.getBusinessListingSpecialConditions(id));
        businessListingRequest.setBusinessListingDiscounts(businessListingRepository.getBusinessListingDiscounts(id));
        
        return businessListingRequest;
    }

    public List<BusinessListingDTO> getAllBusinessListings() {
        return businessListingRepository.findAll();
    }

    @Transactional
    public void deleteBusinessListing(Integer id) {
        businessListingRepository.deleteById(id, BusinessListing.class);
    }

    public BusinessListingDescriptionDetailsDTO getBusinessListingDescriptionDetails() {
        BusinessListingDescriptionDetailsDTO businessListingDescriptionDetailsDTO = new BusinessListingDescriptionDetailsDTO();

        businessListingDescriptionDetailsDTO.setCuisines(businessListingRepository.findAll(Cuisines.class));
        businessListingDescriptionDetailsDTO.setDays(businessListingRepository.findAll(Days.class));
        businessListingDescriptionDetailsDTO.setDiscounts(businessListingRepository.findAll(Discounts.class));
        businessListingDescriptionDetailsDTO.setPaymentOptions(businessListingRepository.findAll(PaymentOptions.class));
        businessListingDescriptionDetailsDTO.setTimings(businessListingRepository.findAll(Timings.class));
        businessListingDescriptionDetailsDTO.setAtmospheres(businessListingRepository.findAll(Atmospheres.class));

        return businessListingDescriptionDetailsDTO;
    }

    // public BusinessListingDescriptionDTO createOrUpdateBusinessListingDescription(
    //         BusinessListingDescriptionDTO businessListingDescriptionDTO) {
    //     BusinessListingDescription businessListingDescription = new BusinessListingDescription(
    //             businessListingDescriptionDTO);

    //     return new BusinessListingDescriptionDTO(businessListingRepository.save(businessListingDescription));
    // }

    // public BusinessListingSpecialConditionsDTO createBusinessListingSpecialConditions(
    //         BusinessListingSpecialConditionsDTO businessListingSpecialConditionsDTO) {
    //     BusinessListingSpecialConditions businessListingSpecialConditions = new BusinessListingSpecialConditions(
    //             businessListingSpecialConditionsDTO);

    //     return new BusinessListingSpecialConditionsDTO(
    //             businessListingRepository.save(businessListingSpecialConditions));
    // }

    // public void createBusinessListingDiscounts(List<BusinessListingDiscountsDTO> businessListingDiscountsDTO) {
    //     for (BusinessListingDiscountsDTO discount : businessListingDiscountsDTO) {
    //         BusinessListingDiscounts businessListingDiscounts = new BusinessListingDiscounts(discount);
    //         businessListingRepository.save(businessListingDiscounts);
    //     }
    // }

    public Integer getBusinessListingIdByUserid(Integer userId) {
        return businessListingRepository.getBusinessListingIdByUserid(userId);
    }
}

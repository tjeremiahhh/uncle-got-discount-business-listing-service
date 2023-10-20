package com.businesslisting;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.businesslisting.dto.BusinessListingDTO;
import com.businesslisting.entity.BusinessListing;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BusinessListingService {
    
    private final BusinessListingRepository businessListingRepository;

    @Transactional
    public BusinessListingDTO createOrUpdateBusinessListing(BusinessListingDTO businessListingDTO) {
        BusinessListing businessListing = new BusinessListing(businessListingDTO);

        return new BusinessListingDTO(businessListingRepository.save(businessListing));
    }

    public BusinessListingDTO getBusinessListingById(Integer id) {
        return businessListingRepository.findById(id);
    }

    public List<BusinessListingDTO> getAllBusinessListings() {
        return businessListingRepository.findAll();
    }

    @Transactional
    public void deleteBusinessListing(Integer id) {
        businessListingRepository.deleteById(id, BusinessListing.class);
    }
}

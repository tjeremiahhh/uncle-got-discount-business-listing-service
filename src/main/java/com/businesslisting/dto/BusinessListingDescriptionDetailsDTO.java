package com.businesslisting.dto;

import java.util.List;

import com.businesslisting.entity.Atmospheres;
import com.businesslisting.entity.Cuisines;
import com.businesslisting.entity.Days;
import com.businesslisting.entity.Discounts;
import com.businesslisting.entity.PaymentOptions;
import com.businesslisting.entity.Timings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessListingDescriptionDetailsDTO {
    private List<Cuisines> cuisines;
    private List<Days> days;
    private List<Discounts> discounts;
    private List<PaymentOptions> paymentOptions;
    private List<Timings> timings;
    private List<Atmospheres> atmospheres;
}

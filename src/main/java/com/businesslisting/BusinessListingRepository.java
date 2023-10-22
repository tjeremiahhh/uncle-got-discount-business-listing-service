package com.businesslisting;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.businesslisting.common.repository.SimpleJdbcRepositoryImpl;
import com.businesslisting.dto.BusinessListingDTO;
import com.businesslisting.dto.BusinessListingDescriptionDTO;
import com.businesslisting.dto.BusinessListingDiscountsDTO;
import com.businesslisting.dto.BusinessListingSpecialConditionsDTO;

@Repository
public class BusinessListingRepository extends SimpleJdbcRepositoryImpl {

    public BusinessListingDTO findById(Integer id) {
        String sqlQuery = "select id, user_id, outlet_name, email_address, phone_number, address, image_file, website_url, "
        + "case when allow_public_holiday = 1 then true else false end as allow_public_holiday, menu_url, "
        + "case when halal_certified = 1 then true else false end as halal_certified "
        + "from business_listings "
        + "where id = :id";

        MapSqlParameterSource sqlParams = new MapSqlParameterSource("id", id);

        return querySingleObject(sqlQuery, sqlParams, BusinessListingDTO.class);
    }
    
    public List<BusinessListingDTO> findAll() {
        String sqlQuery = "select id, user_id, outlet_name, email_address, phone_number, address, image_file, website_url, "
        + "case when allow_public_holiday = 1 then true else false end as allow_public_holiday, menu_url, "
        + "case when halal_certified = 1 then true else false end as halal_certified "
        + "from business_listings";

        return queryList(sqlQuery, BusinessListingDTO.class);
    }

    public Integer getBusinessListingIdByUserid(Integer userId) {
        StringBuilder sqlQuery = new StringBuilder(
            "SELECT "
            +"    id "
            +"FROM "
            +"    business_listings "
            +"WHERE "
            +"    user_id = :userId "
        );

        MapSqlParameterSource sqlParameters = new MapSqlParameterSource("userId", userId);

        return querySingleObject(sqlQuery.toString(), sqlParameters, Integer.class);
    }
    
    public BusinessListingDescriptionDTO getBusinessListingDescription(Integer id) {
        StringBuilder sqlQuery = new StringBuilder(
            "SELECT "
            +"    * "
            +"FROM "
            +"    business_listing_description "
            +"WHERE "
            +"    business_listing_id = :id "
        );

        MapSqlParameterSource sqlParameters = new MapSqlParameterSource("id", id);

        return querySingleObject(sqlQuery.toString(), sqlParameters, BusinessListingDescriptionDTO.class);
    }

    public BusinessListingSpecialConditionsDTO getBusinessListingSpecialConditions(Integer id) {
        StringBuilder sqlQuery = new StringBuilder(
            "SELECT "
            +"    * "
            +"FROM "
            +"    business_listing_special_conditions "
            +"WHERE "
            +"    business_listing_id = :id "
        );

        MapSqlParameterSource sqlParameters = new MapSqlParameterSource("id", id);

        return querySingleObject(sqlQuery.toString(), sqlParameters, BusinessListingSpecialConditionsDTO.class);
    }

    public List<BusinessListingDiscountsDTO> getBusinessListingDiscounts(Integer id) {
        StringBuilder sqlQuery = new StringBuilder(
            "SELECT "
            +"    * "
            +"FROM "
            +"    business_listing_discounts "
            +"WHERE "
            +"    business_listing_id = :id "
        );

        MapSqlParameterSource sqlParameters = new MapSqlParameterSource("id", id);

        return queryList(sqlQuery.toString(), sqlParameters, BusinessListingDiscountsDTO.class);
    }

    public BusinessListingDTO getBusinessListing(Integer id) {
        StringBuilder sqlQuery = new StringBuilder(
            "select "
            +"    * "
            +"from "
            +"    business_listings "
            +"where "
            +"    id = :id "
        );

        MapSqlParameterSource sqlParameters = new MapSqlParameterSource("id", id);

        return querySingleObject(sqlQuery.toString(), sqlParameters, BusinessListingDTO.class);
    }
}

package com.alkemy.ong.service;


import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.dto.OrganizationResponse;
import com.alkemy.ong.model.Organization;

public interface IOrganizationService {

    public OrganizationResponse updatePublicData(OrganizationRequest organization);

}

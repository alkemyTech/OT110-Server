package com.alkemy.ong.integration.organization;

import com.alkemy.ong.common.BaseGeneralTest;
import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

public class BaseOngTest extends BaseGeneralTest {

    @MockBean
    protected OrganizationRepository organizationRepository;

    protected Organization generateOrganization(){
        Organization organization = new Organization();

        organization.setId(1L);
        organization.setName("Somos Mas");
        organization.setImage("https://somosmasImageNoticia.jpg");
        organization.setEmail("somosMas@somosmas.com");
        organization.setWelcomeText("Bienvenidos");
        organization.setEnabled(Boolean.TRUE);

        return organization;

    }

    protected OrganizationRequest generateOrganizationRequest(){
        OrganizationRequest organizationRequest = new OrganizationRequest();

        organizationRequest.setName("Somos Mas");
        organizationRequest.setImage("https://somosmasImageNoticia.jpg");
        organizationRequest.setEmail("somosMas@somosmas.com");
        organizationRequest.setWelcomeText("Bienvenidos");

        return organizationRequest;
    }

    protected List<Organization> generateListOrganization(int count){
            List<Organization> organizations = new ArrayList<>();
            for (int i=0; i<= count; i++){
                organizations.add(generateOrganization());
            }
            return organizations;
    }

}

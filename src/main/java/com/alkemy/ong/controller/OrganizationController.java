package com.alkemy.ong.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.dto.OrganizationPublicDto;
import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.dto.OrganizationResponse;
import com.alkemy.ong.service.IOrganizationService;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

   
    @Autowired
    private IOrganizationService organizationService;

    @GetMapping("/public")
    public ResponseEntity<List<OrganizationPublicDto>> getAll() {
        try {
            List<OrganizationPublicDto> organizations = organizationService.getAllOrganizations();
            return ResponseEntity.ok().body(organizations);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/public")
    @PreAuthorize("hasAnyRole(T(com.alkemy.ong.security.RoleEnum).ADMIN)")
    public ResponseEntity<OrganizationResponse> updatePublicData(@Valid @RequestBody OrganizationRequest organization){
        return new ResponseEntity<OrganizationResponse>(organizationService.updatePublicData(organization), HttpStatus.OK);
    }

}

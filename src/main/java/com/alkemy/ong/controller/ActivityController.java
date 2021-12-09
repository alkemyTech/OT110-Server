package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.dto.ActivityResponse;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/activity")
@AllArgsConstructor
public class ActivityController {

    private final IActivityService activityService;

    @PostMapping
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<ActivityResponse> createActivity(@Valid @RequestBody ActivityRequest activity){
        return new ResponseEntity<ActivityResponse>(activityService.createActivity(activity), HttpStatus.CREATED);
    };

}

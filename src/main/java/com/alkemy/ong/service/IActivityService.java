package com.alkemy.ong.service;

import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.dto.ActivityResponse;

import javax.validation.Valid;

public interface IActivityService {

    public ActivityResponse createActivity(@Valid ActivityRequest request);
}

package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsRequest;
import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.dto.SlideRequest;
import com.alkemy.ong.dto.SlideResponse;
import com.alkemy.ong.model.Slide;

import java.io.IOException;

public interface ISlidesService {

    Slide addSlide(SlideRequest slide) throws Exception;

    SlideResponse updateSlidesById(Long id, SlideRequest news);
}

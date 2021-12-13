package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideRequest;
import com.alkemy.ong.dto.SlideResponse;
import com.alkemy.ong.model.Slide;
import org.springframework.http.ResponseEntity;


public interface ISlidesService {

    Slide addSlide(SlideRequest slide) throws Exception;
    public ResponseEntity<?> delete(Long id);
    public SlideResponse detail(Long id);

}

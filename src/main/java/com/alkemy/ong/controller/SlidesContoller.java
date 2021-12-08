package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideRequest;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.service.ISlidesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/Slides")
@AllArgsConstructor
public class SlidesContoller {

    private final ISlidesService slidesService;

    @PostMapping
    //@PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<Slide> addSlide(@Valid @RequestBody SlideRequest slide) throws Exception {
            return new ResponseEntity<>(slidesService.addSlide(slide), HttpStatus.OK);
    }

}

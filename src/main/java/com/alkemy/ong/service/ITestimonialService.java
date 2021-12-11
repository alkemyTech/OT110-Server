package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.model.Testimonial;
import org.springframework.http.ResponseEntity;

public interface ITestimonialService {

    ResponseEntity<?> delete(Long id);

    Testimonial addTestimonial(TestimonialRequest testimonialRequest);

}

package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.dto.TestimonialResponse;
import com.alkemy.ong.model.Testimonial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITestimonialService {

    ResponseEntity<?> delete(Long id);

    Testimonial addTestimonial(TestimonialRequest testimonialRequest);

    public List<TestimonialResponse> findAllByName();

    Page<Testimonial> readAllTestimonials(Pageable pageable, int page);

    TestimonialResponse findById(Long id);

}

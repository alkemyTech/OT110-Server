package com.alkemy.ong.controller;

import com.alkemy.ong.assembler.TestimonialAssembler;
import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.dto.TestimonialResponse;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.ITestimonialService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/testimonials")
@AllArgsConstructor
public class TestimonialController {

    private final ITestimonialService testimonialService;
    private final TestimonialAssembler testimonialAssembler;
    private final PagedResourcesAssembler<Testimonial> pagedResourcesAssembler;

    @DeleteMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> deleteTestimonial(@Valid @PathVariable("id") Long id) {
        return testimonialService.delete(id);
    }

    @PostMapping
    public ResponseEntity<Testimonial> addTestimonial(@Valid @RequestBody TestimonialRequest testimonialRequest){
        return new ResponseEntity<>(testimonialService.addTestimonial(testimonialRequest), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    @PreAuthorize(SecurityConstant.USER)
    public ResponseEntity<?> findById(@Valid @PathVariable("id") Long id){
        return new ResponseEntity<>(testimonialService.findById(id), HttpStatus.OK);
    }

    @GetMapping(params = "page")
    @PreAuthorize(SecurityConstant.USER)
    public ResponseEntity<PagedModel<TestimonialResponse>> findAllByName(Pageable pageable, @RequestParam("page") int page){
        Page<Testimonial> testimonialEntities = testimonialService.readAllTestimonials(pageable, page);
        PagedModel<TestimonialResponse> testimonialDto = pagedResourcesAssembler
        .toModel(testimonialEntities, testimonialAssembler);

return new ResponseEntity<>(testimonialDto, HttpStatus.OK);

    }


}
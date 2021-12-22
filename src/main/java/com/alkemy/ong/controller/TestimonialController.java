package com.alkemy.ong.controller;


import com.alkemy.ong.assembler.TestimonialAssembler;
import com.alkemy.ong.controller.docs.TestimonialConstantDocs;
import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.dto.TestimonialResponse;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.ITestimonialService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

import org.springframework.boot.context.properties.bind.DefaultValue;
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
@Api(value = TestimonialConstantDocs.TESTIMONIAL)
public class TestimonialController {

    private final ITestimonialService testimonialService;
    private final TestimonialAssembler testimonialAssembler;
    private final PagedResourcesAssembler<Testimonial> pagedResourcesAssembler;

    @DeleteMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    @ApiOperation(value = TestimonialConstantDocs.TESTIMONIAL_DELETETESTIMONIAL, response = String.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = TestimonialConstantDocs.TESTIMONIAL_DELETE_200_OK),
    		@ApiResponse(code = 403, message = TestimonialConstantDocs.TESTIMONIAL_DELETE_403_FORBIDDEN),
    		@ApiResponse(code = 404, message = TestimonialConstantDocs.TESTIMONIAL_404_NOT_FOUND)
    })    
    public ResponseEntity<?> deleteTestimonial(
    		@ApiParam(value = TestimonialConstantDocs.TESTIMONIAL_DELETE_PARAM_ID, required = true, example = "1") @Valid @PathVariable("id") Long id
    		) {
        return testimonialService.delete(id);
    }

    @PostMapping
    @PreAuthorize(SecurityConstant.ADMIN)
    @ApiOperation(value = TestimonialConstantDocs.TESTIMONIAL_ADDTESTIMONIAL, response = Testimonial.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 201, message = TestimonialConstantDocs.TESTIMONIAL_POST_201_CREATED)
    })
    public ResponseEntity<Testimonial> addTestimonial(
    		@ApiParam(value = TestimonialConstantDocs.TESTIMONIAL_POST_PARAM_TESTIMONIALREQUEST, required = true) @Valid @RequestBody TestimonialRequest testimonialRequest
    		){
        return new ResponseEntity<>(testimonialService.addTestimonial(testimonialRequest), HttpStatus.CREATED);

    }
    
    @PutMapping("/{id}")
 	@PreAuthorize(SecurityConstant.ADMIN)
    @ApiOperation(value = TestimonialConstantDocs.TESTIMONIAL_UPDATE, response = TestimonialResponse.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = TestimonialConstantDocs.TESTIMONIAL_PUT_200_OK),
    		@ApiResponse(code = 403, message = TestimonialConstantDocs.TESTIMONIAL_PUT_403_FORBIDDEN),
    		@ApiResponse(code = 404, message = TestimonialConstantDocs.TESTIMONIAL_404_NOT_FOUND)
    })
    public ResponseEntity<TestimonialResponse> update(
    		@ApiParam(value = TestimonialConstantDocs.TESTIMONIAL_PUT_PARAM_ID, required = true, example = "1") @PathVariable(value = "id") Long id, 
    		@ApiParam(value = TestimonialConstantDocs.TESTIMONIAL_PUT_PARAM_TESTIMONIALREQUEST, required = true) @Valid @RequestBody TestimonialRequest testimonial
    		){
        return new ResponseEntity<TestimonialResponse>(testimonialService.updateTestimonial(testimonial, id), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @PreAuthorize(SecurityConstant.USER_ADMIN)
    @ApiOperation(value = TestimonialConstantDocs.TESTIMONIAL_FINDBYID, response = TestimonialResponse.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = TestimonialConstantDocs.TESTIMONIAL_GET_200_OK),
    		@ApiResponse(code = 404, message = TestimonialConstantDocs.TESTIMONIAL_404_NOT_FOUND)
    })
    public ResponseEntity<?> findById(
    		@ApiParam(value = TestimonialConstantDocs.TESTIMONIAL_GET_PARAM_ID, required = true, example = "1") @Valid @PathVariable("id") Long id
    		){
        return new ResponseEntity<>(testimonialService.findById(id), HttpStatus.OK);
    }

    @GetMapping(params = "page")
    @PreAuthorize(SecurityConstant.USER_ADMIN)
    @ApiOperation(value = TestimonialConstantDocs.TESTIMONIAL_FINDALLBYNAME, response = PagedModel.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = TestimonialConstantDocs.TESTIMONIAL_GET_200_OK),
    		@ApiResponse(code = 404, message = TestimonialConstantDocs.TESTIMONIAL_GET_404_NOT_FOUND)
    })    
    public ResponseEntity<PagedModel<TestimonialResponse>> findAllByName(
    		@ApiParam(required = false) Pageable pageable, 
    		@ApiParam(value = TestimonialConstantDocs.TESTIMONIAL_GET_PARAM_PAGE_NUMBER, required = true, example = "0") @RequestParam("page") int page
    		){
        Page<Testimonial> testimonialEntities = testimonialService.readAllTestimonials(pageable, page);
        PagedModel<TestimonialResponse> testimonialDto = pagedResourcesAssembler
        .toModel(testimonialEntities, testimonialAssembler);

        return new ResponseEntity<>(testimonialDto, HttpStatus.OK);

    }


}
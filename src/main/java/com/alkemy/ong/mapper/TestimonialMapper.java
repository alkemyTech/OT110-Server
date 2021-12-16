package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.TestimonialResponse;
import com.alkemy.ong.model.Testimonial;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {

    public TestimonialResponse testimonialModel2DTO(Testimonial model){
        TestimonialResponse dto = new TestimonialResponse();
        dto.setName(model.getName());
        dto.setContent(model.getContent());
        dto.setImage(model.getImage());

        return dto;
    }

}

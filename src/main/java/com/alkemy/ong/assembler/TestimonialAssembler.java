package com.alkemy.ong.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import com.alkemy.ong.controller.TestimonialController;
import com.alkemy.ong.dto.TestimonialResponse;
import com.alkemy.ong.model.Testimonial;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TestimonialAssembler extends RepresentationModelAssemblerSupport<Testimonial, TestimonialResponse> {

    public TestimonialAssembler(){
        super(TestimonialController.class, TestimonialResponse.class);
    }

    @Override
    public TestimonialResponse toModel(Testimonial entity) {

        TestimonialResponse testimonialDto = instantiateModel(entity);

        testimonialDto.add(linkTo(
            methodOn(TestimonialController.class)
                    .findById(entity.getId()))
            .withSelfRel());

        testimonialDto.setName(entity.getName());
        testimonialDto.setImage(entity.getImage());
        testimonialDto.setContent(entity.getContent());

        return testimonialDto;
    }

}

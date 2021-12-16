package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.dto.TestimonialResponse;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.TestimonialMapper;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.ITestimonialService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestimonialServiceImpl implements ITestimonialService {

    private final TestimonialRepository testimonialRepository;
    private final TestimonialMapper testimonialMapper;
    private final MessageSource messageSource;
    private static final int SIZE_DEFAULT = 10;

    @Override
    public ResponseEntity<?> delete(Long id) {
        String notFoundTestimonialMessage = messageSource.getMessage("testimonial.notFound", null, Locale.US);
        String deletedTestimonialMessage = messageSource.getMessage("testimonial.isDeleted", null, Locale.US);

        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(notFoundTestimonialMessage));
        testimonialRepository.delete(testimonial);

        return new ResponseEntity<>(deletedTestimonialMessage, HttpStatus.OK);
    }

    @Override
    public Testimonial addTestimonial (TestimonialRequest testimonialRequest){

          Testimonial testimonial = new Testimonial();
          testimonial.setName(testimonialRequest.getName());
          testimonial.setContent(testimonialRequest.getContent());

          if (testimonialRequest.getImage() != null) {
               testimonial.setImage(testimonialRequest.getImage());
          }

          return testimonialRepository.save(testimonial);
    }

    @Override
    public TestimonialResponse findById(Long id) throws NotFoundException{
        String notFoundTestimonialsMessage = messageSource.getMessage("testimonial.notFound", null, Locale.US);
        Testimonial testimonial = testimonialRepository.findById(id).orElseThrow(()-> new NotFoundException(notFoundTestimonialsMessage));
        return testimonialMapper.testimonialModel2DTO(testimonial);
    }

    @Override
    public List<TestimonialResponse> findAllByName() {
        String testimonialListIsEmpty = messageSource.getMessage("testimonial.listEmpty", null, Locale.US);
        List<TestimonialResponse> testimonialDto = testimonialRepository.findAll()
                .stream()
                .map(testimonial -> testimonialMapper.testimonialModel2DTO(testimonial))
                .collect(Collectors.toList());
        if(testimonialDto.isEmpty()){
            throw new NotFoundException(testimonialListIsEmpty);
        }
        return testimonialDto;
    }
    @Override
    public Page<Testimonial> readAllTestimonials(Pageable pageable , int page) {
        String testimonialListPageNotFound = messageSource.getMessage("testimonial.pageNotFound", null, Locale.US);
        pageable = (Pageable) PageRequest.of(page, SIZE_DEFAULT);
        if (page > testimonialRepository.findAll(pageable).getTotalPages()) {
            throw new NotFoundException(testimonialListPageNotFound);
        }
        return testimonialRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }

}
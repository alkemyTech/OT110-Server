package com.alkemy.ong.service.impl;

import com.alkemy.ong.aws.IAWSS3Service;
import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.dto.SlideRequest;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.IOrganizationService;
import com.alkemy.ong.service.ISlidesService;
import com.alkemy.ong.util.ContentTypeEnum;
import io.jsonwebtoken.lang.Strings;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Comparator;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SlidesServiceImpl implements ISlidesService{

    private final IAWSS3Service awss3Service;
    private final IOrganizationService organizationService;
    private final SlideRepository slideRepository;
    private final MessageSource messageSource;

    @Override
    public Slide addSlide(SlideRequest slide) throws Exception {

        String contentTypeErrorMessage = messageSource.getMessage("awss3.errorMessage.contentType", null, Locale.US);
        String invalidNumberErrorMessage = messageSource.getMessage("slides.invalidNumber", null, Locale.US);
        final String FILE_NAME = "slides";
        final String PREFIX_PNG = "iVBORw0KGgo";
        final String PREFIX_JPG_JPEG = "/9j/4";

        File outputFile = contentTypeValidation(slide, contentTypeErrorMessage, FILE_NAME, PREFIX_PNG, PREFIX_JPG_JPEG);

        decodeBase64ToFile(slide.getBase64Image(), outputFile);

        String imageURL = awss3Service.uploadImage(outputFile);

        if(slide.getOrderSlide() == null){
            automaticOrder(slide);
        }else{
            Optional<Slide> repeatOrderSlide = slideRepository.findByOrderSlide(slide.getOrderSlide());
            if (repeatOrderSlide.isPresent()){
                throw new IllegalArgumentException(invalidNumberErrorMessage);
            }
        }

        Slide slideToAdd = new Slide();
        slideToAdd.setImageUrl(imageURL);
        slideToAdd.setText(slide.getText());
        slideToAdd.setOrderSlide(slide.getOrderSlide());
        slideToAdd.setOrganizationId(organizationService.getOrganization().getId());

        return slideRepository.save(slideToAdd);

    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        String notFoundSlideMessage = messageSource.getMessage("slide.notFound", null, Locale.US);
        String isDeletedSlideMessage = messageSource.getMessage("slide.isDeleted", null, Locale.US);

        Slide slide = slideRepository.findById(id)
                .orElseThrow(()->new NotFoundException(notFoundSlideMessage));
        slideRepository.delete(slide);
        return new ResponseEntity<>(isDeletedSlideMessage, HttpStatus.OK);
    }

    private void automaticOrder(SlideRequest slide) {
        Integer lastNumber = slideRepository.findAll()
                                            .stream()
                                            .sorted(Comparator.comparing(Slide::getOrderSlide, Comparator.reverseOrder()))
                                            .map(Slide::getOrderSlide)
                                            .findFirst()
                                            .orElse(0);
        lastNumber++;
        slide.setOrderSlide(lastNumber);
    }

    private void decodeBase64ToFile(String base64Image, File outputFile) throws IOException {
        byte[] decodedImage = Base64.getDecoder().decode(base64Image);
        FileUtils.writeByteArrayToFile(outputFile, decodedImage);
    }

    private File contentTypeValidation(SlideRequest slide, String contentTypeErrorMessage, String FILE_NAME, String PREFIX_PNG, String PREFIX_JPG_JPEG) {
        File outputFile;
        if (Strings.startsWithIgnoreCase(slide.getBase64Image(), PREFIX_PNG)){
            outputFile = new File(FILE_NAME + ContentTypeEnum.PNG.getFileExtension());
        }else if (Strings.startsWithIgnoreCase(slide.getBase64Image(), PREFIX_JPG_JPEG)){
            outputFile = new File(FILE_NAME + ContentTypeEnum.JPG.getFileExtension());
        }else {
            throw new IllegalArgumentException(contentTypeErrorMessage);
        }
        return outputFile;
    }


}

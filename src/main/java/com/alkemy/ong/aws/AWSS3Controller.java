package com.alkemy.ong.aws;

import com.alkemy.ong.security.SecurityConstant;
import com.amazonaws.SdkClientException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/image")
public class AWSS3Controller{

        private final AWSS3ServiceImpl amazonClient;

        @PostMapping("/upload")
        @PreAuthorize(SecurityConstant.ADMIN)
        public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile file) throws IOException, SdkClientException {
            return new ResponseEntity<>(amazonClient.uploadImage(file), HttpStatus.CREATED);
        }

}

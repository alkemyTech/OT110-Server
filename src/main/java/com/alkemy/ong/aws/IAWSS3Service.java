package com.alkemy.ong.aws;

import org.springframework.web.multipart.MultipartFile;

public interface IAWSS3Service {
    String uploadImage(MultipartFile multipartFile) throws Exception;
}

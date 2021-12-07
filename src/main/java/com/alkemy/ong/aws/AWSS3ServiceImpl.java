package com.alkemy.ong.aws;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;


@RequiredArgsConstructor
@Service
public class AWSS3ServiceImpl implements IAWSS3Service {

    private final AmazonS3 s3client;
    private final MessageSource messageSource;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;

    @Override
    public String uploadImage(MultipartFile multipartFile) throws IOException, SdkClientException{
        String emptyFileErrorMessage = messageSource.getMessage("awss3.errorMessage.emptyFile", null, Locale.US);
        String contentTypeErrorMessage = messageSource.getMessage("awss3.errorMessage.contentType", null, Locale.US);
        String fileUrl = "";

        if(multipartFile.isEmpty()){
            throw new IllegalArgumentException(emptyFileErrorMessage);
        }

        String contentType = multipartFile.getContentType();
        if (!isSupportedContentType(contentType)) {
            throw new IllegalArgumentException(contentTypeErrorMessage);
        }

        File file = convertMultiPartToFile(multipartFile);
        String fileName = generateFileName(multipartFile);
        fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
        uploadFileTos3bucket(fileName, file);
        file.delete();

        return fileUrl;
    }

    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg");
    }




}

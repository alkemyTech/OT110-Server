package com.alkemy.ong.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonConfig {

	@Value("${amazonProperties.accessKey}")
	private String accessKeyId;

	@Value("${amazonProperties.secretKey}")
	private String accessSecretKey;

	@Value("${amazonProperties.region}")
	private String region;

	@Bean
	public AmazonS3 getS3Client() {
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, accessSecretKey);
		return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
	}

}

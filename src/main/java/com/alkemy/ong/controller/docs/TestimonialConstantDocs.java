package com.alkemy.ong.controller.docs;

public interface TestimonialConstantDocs {

	public static final String TESTIMONIAL = "Testimonial Docs";
	
	public static final String TESTIMONIAL_FINDALLBYNAME = "Returns a paginated list of testimonials";
	public static final String TESTIMONIAL_FINDBYID = "Returns a testimonial through id parameter";
	public static final String TESTIMONIAL_UPDATE = "Update an existing testimonial";
	public static final String TESTIMONIAL_ADDTESTIMONIAL = "Create new testimonial";
	public static final String TESTIMONIAL_DELETETESTIMONIAL = "Delete a testimonial";
	
	public static final String TESTIMONIAL_GET_200_OK = "Testimony found";
	public static final String TESTIMONIAL_GET_404_NOT_FOUND = "Page number not found";
	
	public static final String TESTIMONIAL_POST_201_CREATED = "The testimony has been created";
	
	public static final String TESTIMONIAL_PUT_200_OK = "The testimony has been updated successfully";	
	public static final String TESTIMONIAL_PUT_403_FORBIDDEN = "Access denied to update testimonials";	
		
	public static final String TESTIMONIAL_DELETE_200_OK = "The testimonial has been removed";
	public static final String TESTIMONIAL_DELETE_403_FORBIDDEN = "Access denied to delete testimonials";
	
	public static final String TESTIMONIAL_404_NOT_FOUND = "Cannot find any testimonial";	
	
	public static final String TESTIMONIAL_DELETE_PARAM_ID = "Enter an existing id to remove testimonial";
	
	public static final String TESTIMONIAL_POST_PARAM_TESTIMONIALREQUEST = "Fill in the required fields to save";
	
	public static final String TESTIMONIAL_PUT_PARAM_ID = "Enter an existing id to update testimonial";
	public static final String TESTIMONIAL_PUT_PARAM_TESTIMONIALREQUEST = "Fill in the required fields to update";
	
	public static final String TESTIMONIAL_GET_PARAM_ID = "Enter an existing id to search testimonial";
	
	public static final String TESTIMONIAL_GET_PARAM_PAGE_NUMBER = "Enter an existing number page to search testimonial";
	
	public static final String TESTIMONIAL_DTO_MODEL = "Model to create a testimonial";
	public static final String TESTIMONIAL_DTO_MODEL_FIELD_NAME = "Choose a name or title for the testimonial";
	public static final String TESTIMONIAL_DTO_MODEL_FIELD_IMAGE = "Enter the url of an image";
	public static final String TESTIMONIAL_DTO_MODEL_FIELD_CONTENT = "Enter the content of the testimonial";

}

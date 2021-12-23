package com.alkemy.ong.controller.docs;

public interface CategoryConstantDocs {

    public static final String CATEGORY = "Category documentation";

    public static final String CATEGORY_FIND_BY_ID = "Returns a category through id parameter";
    public static final String CATEGORY_FIND_ALL_CATEGORIES_BY_NAME = "Returns a paginated list of categories";
    public static final String CATEGORY_CREATE = "Create new category";
    public static final String CATEGORY_UPDATE = "Update an existing category";
    public static final String CATEGORY_DELETE = "Delete a category";

    public static final String CATEGORY_GET_200_OK = "Category found";
    public static final String CATEGORY_GET_404_NOT_FOUND = "Page number not found";

    public static final String CATEGORY_POST_201_CREATED = "The category has been created";

    public static final String CATEGORY_PUT_200_OK = "The category has been updated successfully";
    public static final String CATEGORY_PUT_403_FORBIDDEN = "Access denied to update categories";

    public static final String CATEGORY_DELETE_200_OK = "The category has been removed";
    public static final String CATEGORY_DELETE_403_FORBIDDEN = "Access denied to delete categories";

    public static final String CATEGORY_404_NOT_FOUND = "Cannot find any categories";

    public static final String CATEGORY_DELETE_PARAM_ID = "Enter an existing id to remove category";

    public static final String CATEGORY_POST_PARAM_CATEGORY_REQUEST = "Fill in the required fields to save a category";

    public static final String CATEGORY_PUT_PARAM_ID = "Enter an existing id to update category";
    public static final String CATEGORY_PUT_PARAM_CATEGORY_REQUEST = "Fill in the required fields to update a category";

    public static final String CATEGORY_GET_PARAM_ID = "Enter an existing id to search category";

    public static final String CATEGORY_GET_PARAM_PAGE_NUMBER = "Enter an existing number page to search category";

    public static final String CATEGORY_DTO_MODEL = "Model to create a category";
    public static final String CATEGORY_DTO_MODEL_FIELD_NAME = "Choose a name or title for the category";
    public static final String CATEGORY_DTO_MODEL_FIELD_DESCRIPTION = "Enter the description of the category";
    public static final String CATEGORY_DTO_MODEL_FIELD_IMAGE = "Enter a valid url of an image";

}


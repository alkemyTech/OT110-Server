package com.alkemy.ong.controller.docs;

public interface CategoryConstantDocs {

    String CATEGORY = "Category documentation";

    String CATEGORY_DTO_ID = "Category id";
    String CATEGORY_DTO_NAME = "Category's name";
    String CATEGORY_DTO_DESCRIPTION = "Category´s description";
    String CATEGORY_DTO_IMAGE = "Category´s image";
    String CATEGORY_DTO_DATE_CREATION = "Category's date creation";
    String CATEGORY_DTO_DATE_UPDATE = "Category´s date update";

    String CATEGORY_CATEGORYREQUESTUPDATE_NAME = "Enter a title for the category";
    String CATEGORY_CATEGORYREQUESTUPDATE_DESCRIPTION = "Enter the description of the category";
    String CATEGORY_CATEGORYREQUESTUPDATE_IMAGE = "Enter a valid url of an image";

    String CATEGORY_FIND_BY_ID = "Returns a category through id parameter";
    String CATEGORY_GET_200_OK = "Category found";
    String CATEGORY_GET_404_NOT_FOUND = "Page number not found";

    String CATEGORY_FIND_ALL_CATEGORIES_BY_NAME = "Returns a paginated list of categories";
    String CATEGORY_GET_PARAM_ID = "Enter an existing id to search category";
    String CATEGORY_GET_PARAM_PAGE_NUMBER = "Enter an existing number page to search category";

    String CATEGORY_CREATE = "Create new category";
    String CATEGORY_CREATED = "The category has been created";
    String CATEGORY_CREATED_PARAM_CATEGORY_REQUEST = "Fill in the required fields to save a category";

    String CATEGORY_UPDATE = "Update an existing category";
    String CATEGORY_UPDATE_OK = "The category has been updated successfully";
    String CATEGORY_UPDATE_PARAM_ID = "Enter an existing id to update category";
    String CATEGORY_UPDATE_PARAM_CATEGORY_REQUEST = "Fill in the required fields to update a category";

    String CATEGORY_DELETE = "Delete a category";
    String CATEGORY_DELETE_OK = "The category has been removed";
    String CATEGORY_DELETE_PARAM_ID = "Enter an existing id to remove category";

    String CATEGORY_404_NOT_FOUND = "Cannot find any categories";


}


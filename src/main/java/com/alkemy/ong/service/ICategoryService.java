/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryByNameDto;
import com.alkemy.ong.dto.CategoryDto;

import java.util.List;


public interface ICategoryService {

    public List<CategoryByNameDto> findByName();

    CategoryDto findById(Long id);
}

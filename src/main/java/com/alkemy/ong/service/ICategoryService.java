/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;


/**
 *
 * @author mateo
 */

public interface ICategoryService {

    public CategoryDto findById(Long id);
}

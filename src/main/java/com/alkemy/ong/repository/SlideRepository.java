/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.repository;

import com.alkemy.ong.model.Slide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mateo
 */
@Repository
public interface SlideRepository  extends JpaRepository<Slide, Long>{
    
}

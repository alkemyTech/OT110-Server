/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.model;



import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;




/**
 *
 * @author mateo
 */
@Data
@Entity
@Table(name="slide")
public class Slide {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "El url no puede estar vacio")
    private String imageUrl;
    
    @NotNull(message="El text no puede estar vacio")
    private String text;
    
    @NotNull
    private Integer order;
    
    @NotNull(message="organizationId no puede estar vacio")
    private Long organizationId;
    
}

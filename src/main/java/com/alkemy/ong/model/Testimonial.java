package com.alkemy.ong.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "testimonials")
@SQLDelete(sql = "UPDATE testimonials SET enabled = false WHERE id = ?")
@EntityListeners(AuditingEntityListener.class)
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name field has to be present")
    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String image;

    @Column(nullable = true)
    private String content;

    @CreatedDate
    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime dateUpdate;

    @NotNull
    @Column(nullable = false)
    private boolean enabled = true;

}
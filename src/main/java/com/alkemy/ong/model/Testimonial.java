package com.alkemy.ong.model;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "testimonials")
@SQLDelete(sql = "UPDATE Testimonial SET enabled = false WHERE id = ?")
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Nullable
    @Column(nullable = true)
    private String image;

    @Nullable
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
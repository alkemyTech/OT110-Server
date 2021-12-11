package com.alkemy.ong.model;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "organizations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE organizations SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@EntityListeners(AuditingEntityListener.class)
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Image cannot be null")
    private String image;

    @Nullable
    private String address;

    @Nullable
    private Long phone;

    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Welcome Text cannot be null")
    private String welcomeText;

    @Nullable
    private String aboutUsText;

    @Nullable
    private String facebookUrl;

    @Nullable
    private String instagramUrl;

    @Nullable
    private String linkedinUrl;

    @CreatedDate
    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @LastModifiedDate
    private LocalDateTime dateUpdate;

    @NotNull
    private boolean deleted = Boolean.FALSE;
}



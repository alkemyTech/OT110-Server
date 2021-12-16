package com.alkemy.ong.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE news SET enabled = false WHERE id = ?")
@Where(clause = "enabled=true")
@Table(name = "news")
@EntityListeners(AuditingEntityListener.class)
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name cannot be null")
    private @NonNull String name;

    @NotNull(message = "content cannot be null")
    private @NonNull String content;

    @NotNull(message = "image cannot be null")
    private @NonNull String image;

    private boolean enabled = true;

    @CreatedDate
    @NotNull
    @Column(updatable = false)
    private LocalDateTime dateCreation;

    @LastModifiedDate
    private LocalDateTime dateUpdate;

    private String type = "news";

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private @NonNull Category category;

    @OneToMany(mappedBy = "news")
    private List<Comment> comments;

}

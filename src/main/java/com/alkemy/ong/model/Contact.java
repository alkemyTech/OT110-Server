package com.alkemy.ong.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contacts")
@SQLDelete(sql = "UPDATE contacts SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "The name field is required")
	@Column(nullable = false)
	private String name;
	
	@Nullable
	@Column(nullable = false)
	private String phone;
	
	@NotNull(message = "Email cannot be null")
	@Column(nullable = false)
	private String email;
	
	@NotNull(message = "The message field is required")
	@Column(nullable = false)
	private String message;
	
	@NotNull
	private boolean deleted = Boolean.FALSE;
	
}

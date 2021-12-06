package com.alkemy.ong.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.NonNull;
import org.springframework.lang.Nullable;

import com.alkemy.ong.security.RoleEnum;




@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {
	
	 @Id
	 @GeneratedValue(strategy =  GenerationType.IDENTITY)
	 private Long idRole;
	 
	 @NotNull(message = "role name cannot be null")
	 @NotEmpty(message = "role name cannot be empty")
	 private String name;
	 
	 @NotNull
	 @Enumerated(EnumType.STRING)
	 private RoleEnum roleEnum;
	 
	 @Nullable
	 private String description;
	 
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date timestamp;

	
}

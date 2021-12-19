package com.alkemy.ong.model;

import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import com.alkemy.ong.security.RoleEnum;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
@EntityListeners(AuditingEntityListener.class)
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

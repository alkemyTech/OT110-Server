package com.alkemy.ong.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	
    @Modifying
    @Query("UPDATE User SET role = :role WHERE email = :email")
	void updateUserRole(@Param("email") String email, @Param("role") Role role);
}

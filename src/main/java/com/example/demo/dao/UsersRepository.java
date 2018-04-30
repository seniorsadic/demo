package com.example.demo.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Users;



public interface UsersRepository extends JpaRepository<Users, String> {
	
	@Query("select c from Users c where username like :x")
	public Page<Users> findByUsername(@Param("x")String numeroCompte, Pageable page);
	
}

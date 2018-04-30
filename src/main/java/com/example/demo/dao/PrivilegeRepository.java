package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Module;
import com.example.demo.entities.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
	
	@Query("select p from Privilege p where module = :x")
	public List<Privilege> findByModule(@Param("x")Module module);

}

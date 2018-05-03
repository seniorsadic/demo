package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.AncienPCB;

public interface AncienPCBRepository extends JpaRepository<AncienPCB, String> {

	@Query("select a from AncienPCB a where parent = :x")
	public List<AncienPCB> findByParent(@Param("x")AncienPCB ancienpcb);
	
	@Query("select a from AncienPCB a where parent = null")
	public List<AncienPCB> listeParent();
}

package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {

}

package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Citizen;

public interface CitizensRepo extends CrudRepository<Citizen, Integer>{
	Citizen findIdByEmail(String email);
}

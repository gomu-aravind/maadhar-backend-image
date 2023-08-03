package com.examples.demo.api.exception;

public class CitizenNotFoundException extends RuntimeException{
	
	public CitizenNotFoundException(int id) {
		super("Citizen with id " + id + " is not Found.Please check the given id");
	}

}
package com.examples.demo.api.exception;

public class AdminNotFoundException extends RuntimeException{

	public AdminNotFoundException(int id) {
		super("Admin with id " + id + " is not Found.Please check the Admin id");
	}
}
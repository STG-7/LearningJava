package com.goel.studentmanagement;

public class PersonNotFoundException extends RuntimeException {

	PersonNotFoundException(Integer id) {
	    super("Could not find person " + id);
	  }
	}
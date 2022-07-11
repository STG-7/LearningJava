package com.goel.studentmanagement.controller;

public class PersonNotFoundException extends RuntimeException {

	PersonNotFoundException(Integer id) {
	    super("Could not find person " + id);
	  }
	}
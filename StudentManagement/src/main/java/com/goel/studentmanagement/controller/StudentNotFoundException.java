package com.goel.studentmanagement.controller;

public class StudentNotFoundException extends PersonNotFoundException {

	StudentNotFoundException(Integer id) {
		super(id);
	}

}

package com.goel.studentmanagement.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.goel.studentmanagement.entity.Student;

@Component
class StudentModeAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>> {

	@Override
	public EntityModel<Student> toModel(Student student) {

		return EntityModel.of(student, //
				linkTo(methodOn(MainController.class).getStudent(student.getId())).withSelfRel(),
				linkTo(methodOn(MainController.class).getAllStudents()).withRel("students"));
	}
}
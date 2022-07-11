package com.goel.studentmanagement.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.goel.studentmanagement.entity.Student;
import com.goel.studentmanagement.repository.StudentRepository;

@RestController
@RequestMapping(path = "/demo")
public class MainController {
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentModeAssembler studentModeAssembler;

	@GetMapping("")
	public String index() {
		return "Use /student post request to add student and /students to get a list of all students";
	}

	@GetMapping(path = "/students")
	public @ResponseBody CollectionModel<EntityModel<Student>> getAllStudents() {
		List<EntityModel<Student>> students = StreamSupport.stream(studentRepository.findAll().spliterator(), false)
				.map(studentModeAssembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(students, linkTo(methodOn(MainController.class).getAllStudents()).withSelfRel());
	}

	@GetMapping(path = "/students/{id}")
	public @ResponseBody EntityModel<Student> getStudent(@PathVariable Integer id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		return studentModeAssembler.toModel(student);
	}

	@PostMapping(path = "/students")
	public @ResponseBody ResponseEntity<?> addNewStudent(@RequestBody Student student) {
		EntityModel<Student> entityModel = studentModeAssembler.toModel(studentRepository.save(student));
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@PutMapping("/students/{id}")
	public @ResponseBody ResponseEntity<?> replaceEmployee(@RequestBody Student student, @PathVariable Integer id) {

		Student updatedStudent = studentRepository.findById(id).map(aStudent -> {
			aStudent.setName(student.getName());
			aStudent.setEmail(student.getEmail());
			aStudent.setSex(student.getSex());
			aStudent.setDob(student.getDob());
			aStudent.setAddress(student.getAddress());
			aStudent.setParentName(student.getParentName());
			aStudent.setParentPhone(student.getParentPhone());
			aStudent.setPhone(student.getPhone());
			return studentRepository.save(aStudent);
		}).orElseGet(() -> {
			student.setId(id);
			return studentRepository.save(student);
		});
		
		EntityModel<Student> entityModel = studentModeAssembler.toModel(updatedStudent);
		
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}

	@DeleteMapping("/students/{id}")
	public @ResponseBody ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {

		studentRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
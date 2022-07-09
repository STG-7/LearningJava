package com.goel.studentmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.goel.studentmanagement.repository.StudentRepository;

@RestController
@RequestMapping(path = "/demo")
public class MainController {
	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("")
	public String index() {
		return "Use /student post request to add student and /students to get a list of all students";
	}

	@PostMapping(path = "/students")
	public @ResponseBody Student addNewStudent(@RequestBody Student student) {
		return studentRepository.save(student);
	}

	@GetMapping(path = "/students")
	public @ResponseBody Iterable<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@GetMapping(path = "/students/{id}")
	public @ResponseBody Student getStudent(@PathVariable Integer id) {
		return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
	}

	@PutMapping("/students/{id}")
	public @ResponseBody Student replaceEmployee(@RequestBody Student student, @PathVariable Integer id) {

		return studentRepository.findById(id).map(aStudent -> {
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
	}

	@DeleteMapping("/students/{id}")
	public @ResponseBody String deleteEmployee(@PathVariable Integer id) {
		
		studentRepository.deleteById(id);
		return "Student with ID: '" +id + "' deleted successfully";
	}
}
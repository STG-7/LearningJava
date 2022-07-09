package com.goel.studentmanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.goel.studentmanagement.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {

}
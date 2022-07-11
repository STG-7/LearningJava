package com.goel.studentmanagement.entity;

import javax.persistence.Entity;

@Entity
public class Student  extends Person{
	private String parentName;
	private String parentPhone;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentPhone() {
		return parentPhone;
	}

	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}

}

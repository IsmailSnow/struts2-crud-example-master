package org.apache.struts.example.crud.model;

import java.io.Serializable;

/*
 *
 *Class definning entity Departement
 *
 *
 */

public class Department implements Serializable {

	private static final long serialVersionUID = 7857286940835207293L;

	private Integer departmentId;
	private String name;

	public Department() {
	}

	public Department(Integer departmentId, String name) {
		this.departmentId = departmentId;
		this.name = name;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

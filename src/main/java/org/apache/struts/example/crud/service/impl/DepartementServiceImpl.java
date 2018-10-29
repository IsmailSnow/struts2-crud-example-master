package org.apache.struts.example.crud.service.impl;

import java.util.List;

import org.apache.struts.example.crud.dao.DepartmentDao;
import org.apache.struts.example.crud.dao.InMemoryDepartmentDao;
import org.apache.struts.example.crud.model.Department;
import org.apache.struts.example.crud.service.DepartmentService;

public class DepartementServiceImpl implements DepartmentService {
	private DepartmentDao dao;

	public DepartementServiceImpl() {
		this.dao = new InMemoryDepartmentDao();
	}

	public List<Department> getAllDepartments() {
		return dao.getAllDepartments();
	}

}

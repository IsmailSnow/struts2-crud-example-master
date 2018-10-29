package org.apache.struts.example.crud.dao;

import org.apache.struts.example.crud.model.Department;
import org.apache.struts.example.crud.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class InMemoryEmployeeDao implements EmployeeDao {

	/*** Creating some data for Employee ****/

	private static Map<Integer, Department> departmentsMap;
	private static ArrayList<Employee> employees;

	static {
		employees = new ArrayList<Employee>();
		employees.add(new Employee(1, "Ismail", "Salmi", 25, new Department(100, "Production")));
		employees.add(new Employee(2, "Tata", "Tata", 26, new Department(200, "R & D")));
		employees.add(new Employee(3, "Houda", "Salmi", 27, new Department(300, "Sales")));
		employees.add(new Employee(4, "Toto", "Toto", 28, new Department(400, "Test")));
		DepartmentDao deptDao = new InMemoryDepartmentDao();
		departmentsMap = deptDao.getDepartmentsMap();
	}

	@Override
	public List getAllEmployees() {
		return employees;
	}

	@Override
	public Employee getEmployee(Integer id) {

		Optional<Employee> emp = employees.stream().filter(a -> a.getEmployeeId().equals(id)).findFirst();
		if (emp.isPresent()) {
			return emp.get();
		} else {
			throw new IllegalArgumentException("The given id dont fetch with any employee, please chekc your data");
		}
	}

	@Override
	public void update(Employee emp) {
		if (!employees.contains(emp)) {
			throw new IllegalArgumentException("Employee not found");
		}
		employees.stream().filter(a -> a.getEmployeeId().equals(emp.getEmployeeId())).findFirst().ifPresent(a -> {
			a.setDepartment(departmentsMap.get(emp.getDepartment().getDepartmentId()));
		});
	}

	@Override
	public void insert(Employee emp) {
		OptionalInt lastId = employees.stream().map(Employee::getEmployeeId).collect(Collectors.toList()).stream()
				.mapToInt(Integer::intValue).max();
		if (!lastId.isPresent()) {
			lastId = OptionalInt.of(0);
		}
		emp.setDepartment(departmentsMap.get(emp.getDepartment().getDepartmentId()));
		emp.setEmployeeId(lastId.getAsInt() + 1);
		employees.add(emp);
	}

	@Override
	public void delete(Integer id) {
		employees.stream().filter(temp -> temp.getEmployeeId().equals(id)).findFirst()
				.ifPresent(temp -> employees.remove(temp));
	}

}

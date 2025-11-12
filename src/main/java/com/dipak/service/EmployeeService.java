package com.dipak.service;

import java.util.List;

import com.dipak.entity.Employee;

public interface EmployeeService {
	
	Employee createEmployee(Employee emp,Long deptId);
	List<Employee>getAllEmployee();
	Employee getEmployeeById(Long id);
	Employee updateEmployee(Long id,Employee emp);
	void deleteEmployee(Long id);
	List<Employee>getEmployeesByDepartment(Long deptId);

}

package com.dipak.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dipak.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee>findByDepartmentId(Long id);
}

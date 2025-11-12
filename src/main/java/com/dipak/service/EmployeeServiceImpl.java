package com.dipak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dipak.entity.Department;
import com.dipak.entity.Employee;
import com.dipak.exception.ResourceAlreadyExistsException;
import com.dipak.repository.DepartmentRepository;
import com.dipak.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeRepository empRepo;
	private DepartmentRepository deptRepo;
	public EmployeeServiceImpl(EmployeeRepository empRepo, DepartmentRepository deptRepo) {
		super();
		this.empRepo = empRepo;
		this.deptRepo = deptRepo;
	}
	
	@Override
	public Employee createEmployee(Employee emp, Long deptId) {
		if(empRepo.existsById(emp.getId())) {
			throw new ResourceAlreadyExistsException("Employee already exists with id: "+emp.getId());
			
		}
		Department department=deptRepo.findById(deptId).orElseThrow(()->
				new ResourceAlreadyExistsException("Department not found")
				);
		emp.setDepartment(department);
		return empRepo.save(emp);
	}
	
	@Override
	public List<Employee> getAllEmployee() {
		return empRepo.findAll();
	}
	
	@Override
	public Employee getEmployeeById(Long id) {
		return empRepo.findById(id).orElseThrow(()->new ResourceAlreadyExistsException("Employee not found"));
	}
	
	@Override
	public Employee updateEmployee(Long id, Employee emp) {
		Employee existsEmp=getEmployeeById(id);
		existsEmp.setFirstName(emp.getFirstName());
		existsEmp.setLastName(emp.getLastName());
		existsEmp.setEmail(emp.getEmail());
		existsEmp.setSalary(emp.getSalary());
		return empRepo.save(existsEmp);
	}
	
	@Override
	public void deleteEmployee(Long id) {
		empRepo.deleteById(id);
	}
	
	@Override
	public List<Employee> getEmployeesByDepartment(Long deptId) {	
		return empRepo.findByDepartmentId(deptId);
	}

	
}

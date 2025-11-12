package com.dipak.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dipak.entity.Employee;
import com.dipak.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	private final EmployeeService empService;

	public EmployeeController(EmployeeService empService) {
		super();
		this.empService = empService;
	}
	
	@PostMapping("/addEmp/{deptId}")
	public ResponseEntity<Employee>createEmployee(@RequestBody Employee employee,@PathVariable Long deptId){
		return new ResponseEntity<>(empService.createEmployee(employee, deptId),HttpStatus.CREATED);
	}
	
	@GetMapping("/allEmp")
	public ResponseEntity<List<Employee>>getAllEmployee(){
		return ResponseEntity.ok(empService.getAllEmployee());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee>getEmployeeById(@PathVariable Long id){
		return ResponseEntity.ok(empService.getEmployeeById(id));
	}
	
	@PutMapping("/updateEmp/{id}")
	public ResponseEntity<Employee>updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
		return ResponseEntity.ok(empService.updateEmployee(id, employee));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteEmployee(@PathVariable Long id){
		empService.deleteEmployee(id);
		return ResponseEntity.ok("Employee deleted successfully");
	}
	
	@GetMapping("empByDept/{deptId}")
	public ResponseEntity<List<Employee>>getEmployeesByDepartment(@PathVariable Long deptId){
		return ResponseEntity.ok(empService.getEmployeesByDepartment(deptId));
	}
}

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

import com.dipak.entity.Department;
import com.dipak.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	private final DepartmentService deptService;

	public DepartmentController(DepartmentService deptService) {
		super();
		this.deptService = deptService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Department>createDepartment(@RequestBody Department department){
		return new ResponseEntity<Department>(deptService.createDepartment(department),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Department>>getAllDepartment(){
		return ResponseEntity.ok(deptService.getAllDepartments());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Department>getDepartmentById(@PathVariable Long id){
		return ResponseEntity.ok(deptService.getDepartmentById(id));
	}
	
	@PutMapping("/updateDept/{id}")
	public ResponseEntity<Department>updateDepartment(@PathVariable Long id,@RequestBody Department department){
		return ResponseEntity.ok(deptService.updateDepartment(id, department));
	}
	
	@DeleteMapping("/deleteDept/{id}")
	public ResponseEntity<String>deleteDepartment(@PathVariable Long id){
		deptService.deleteDepartment(id);
		return ResponseEntity.ok("Department deleted successfully");
	}
	
}

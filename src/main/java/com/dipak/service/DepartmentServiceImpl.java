package com.dipak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dipak.entity.Department;
import com.dipak.exception.ResourceAlreadyExistsException;
import com.dipak.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	private DepartmentRepository departmentRepo;
	
	

	public DepartmentServiceImpl(DepartmentRepository departmentRepo) {
		super();
		this.departmentRepo = departmentRepo;
	}

	@Override
	public Department createDepartment(Department department) {
		//check department already exists or not
		if(departmentRepo.existsByName(department.getName())) {
			throw new ResourceAlreadyExistsException("department name already exists: "+department.getName());
		}
		return departmentRepo.save(department);
	}

	@Override
	public Department getDepartmentById(Long id) {
		
		return departmentRepo.findById(id).orElseThrow(()->new ResourceAlreadyExistsException("Department not found"));
	}

	@Override
	public List<Department> getAllDepartments() {

		return departmentRepo.findAll();
	}

	@Override
	public Department updateDepartment(Long id, Department department) {
		Department existsDept=getDepartmentById(id);
		existsDept.setName(department.getName());
		return departmentRepo.save(existsDept);
	}

	@Override
	public void deleteDepartment(Long id) {
		departmentRepo.deleteById(id);
		
	}

}

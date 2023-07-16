package com.dz.app.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.dz.app.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Long>,PagingAndSortingRepository<Employee, Long>{

	//derived query 
	public List<Employee> findByFirstName(String firstName);
	public List<Employee> findByLastName(String name);
	
}

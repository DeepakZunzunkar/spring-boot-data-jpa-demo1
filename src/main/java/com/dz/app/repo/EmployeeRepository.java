package com.dz.app.repo;

import org.springframework.data.repository.CrudRepository;

import com.dz.app.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Long>{

}

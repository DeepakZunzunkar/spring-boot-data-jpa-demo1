package com.dz.app.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.dz.app.entity.Employee;
import java.util.Date;

public interface EmployeeRepository extends CrudRepository<Employee, Long>, PagingAndSortingRepository<Employee, Long> {

	// derived query
	public List<Employee> findByFirstName(String firstName);

	public List<Employee> findByLastName(String name);
//	public List<Employee>

	public List<Employee> findByFirstNameStartingWith(String prefix);

	public List<Employee> findByFirstNameLike(String word);

	public List<Employee> findByBirthDate(Date birthDate);

	public List<Employee> findByBirthDateBetween(Date startdate, Date enddate);

	public List<Employee> findByBirthDateBetweenOrderByBirthDateAsc(Date startdate, Date enddate);
	
	public List<Employee> findByBasePropertiesCreatedOnLessThanEqual(Date createdDate);
	public List<Employee> findByBasePropertiesCreatedOnGreaterThanEqual(Date createdDate);

//	@Query
//	    - JPQL / HQL query 
//		- native SQL query

	
	@Query(value="Select year(CURDATE())-year(birthDate) as age from hibernatedemo3.adpemployee where firstname=:fname",nativeQuery = true)
	public Integer getAgeByName(@Param("fname") String firstName);
	
	
	@Query("Select adp from Employee adp where salary >=:sal")
	public List<Employee> getSalaryGreaterThan(@Param("sal") Double salary);
	
}

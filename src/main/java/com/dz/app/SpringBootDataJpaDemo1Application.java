package com.dz.app;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dz.app.entity.Employee;
import com.dz.app.repo.EmployeeRepository;
import com.dz.app.utility.AppUtility;
import com.dz.app.utility.DateUtils;

@SpringBootApplication
public class SpringBootDataJpaDemo1Application {


	private static final DecimalFormat df = new DecimalFormat("0.00");
	private static final SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
	
	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SpringBootDataJpaDemo1Application.class, args);
		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);

		/*
		 * long count = employeeRepository.count();
		 * employeeRepository.findAll().forEach(emp->{
		 * System.out.println(emp.getFirstName()); });
		 */

		Scanner sc = new Scanner(System.in);
		Employee empTrn = null;
		List<Employee> employeeList = null;
		Optional<Employee> optEmp = null;
		String status;
		long eid = 0;

		do {
//    		AppUtility.initializeLandingPage();
			System.out.println("select your choice \n");

			System.out.println("1]Add  ");
			System.out.println("2]update");
			System.out.println("3]delete");
			System.out.println("4]Search");
			System.out.println("5]View All Record");
			System.out.println("6]Exit from App ");

			int num = sc.nextInt();

			switch (num) {
			case 1:
				empTrn = AppUtility.setEmployeeForm(sc, "ADD", null);
				AppUtility.loader();
				empTrn = employeeRepository.save(empTrn);
				if (empTrn.getEid() == null) {
					System.err.println("not added ,employee already exist");
				} else {
					System.out.println("Employee Added..");
				}
				break;
			case 2:
				System.out.println("****** UPDATE BY ID ***** \n");
				System.out.println("Enter Employee EID : ");
				eid = sc.nextLong();
				AppUtility.loader();
				optEmp = employeeRepository.findById(eid);
				if (optEmp.isPresent()) {
					employeeList = new ArrayList<Employee>();
					employeeList.add(optEmp.get());
					AppUtility.displayRecords(employeeList);
					empTrn = AppUtility.updateEmployeeForm(sc, optEmp.get());
					employeeRepository.save(empTrn);
				} else {
					System.err.println("employee not found by EID ");
				}
				break;
			case 3:
				System.out.println("****** DELETE BY ID ***** \n");
				System.out.println("Enter Employee EID : ");
				eid = sc.nextLong();
				AppUtility.loader();
				optEmp = employeeRepository.findById(eid);
				if (optEmp.isPresent()) {
					employeeList = new ArrayList<Employee>();
					employeeList.add(optEmp.get());
					AppUtility.displayRecords(employeeList);
					AppUtility.loader();
					employeeRepository.delete(optEmp.get());
					System.out.println("deleted successfully !");
				}
				break;
			case 4:
				System.out.println("****** SEARCH BY ID ***** \n");
				System.out.println("Enter Employee EID : ");
				eid = sc.nextLong();
				AppUtility.loader();
				optEmp = employeeRepository.findById(eid);
				if (optEmp.isPresent()) {
					employeeList = new ArrayList<Employee>();
					employeeList.add(optEmp.get());
					AppUtility.displayRecords(employeeList);
				} else {
					System.err.println("employee not found by EID ");
				}
				break;
			case 5:
				
				System.out.println("\n-------------------------------------------------------------------------------------------------------------");
				System.out.println("ID	|	NAME		|	STATUS 	|	AGE	| 	SALARY 		|	CREATED ON	 ");
				System.out.println("---------------------------------------------------------------------------------------------------------------");   
				
				employeeRepository.findAll().forEach(emp->{
					
					System.out.println(emp.getEid()+"\t|"+emp.getFirstName()+" "+emp.getLastName()+"\t\t|\t"+emp.getStatus()+"\t|\t"+DateUtils.getAge(DateUtils.convertJUtilDateTimeToString(emp.getBirthDate()))+"\t|\t"+df.format(emp.getSalary())+"\t|\t"+sdf.format(emp.getBaseProperties().getCreatedOn()));
					
				});
				System.out.println("-----------------------------------------------------------------------------------------------------------------\n");
				
				
				break;

			case 6:
				System.exit(0);
				break;
			default:
				System.err.println("Invalid Choice,try again");
				break;
			}

			System.out.println("\n\n\tDo u want to continue other operation (yes[y] / no[n] ) ? : ");
			status = sc.next();

		} while (status.equalsIgnoreCase("yes") || status.equalsIgnoreCase("y"));
	}

}

package com.dz.app;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.dz.app.entity.Employee;
import com.dz.app.repo.EmployeeRepository;
import com.dz.app.utility.AppUtility;
import com.dz.app.utility.DateUtils;

@SpringBootApplication
public class SpringBootDataJpaDemo1Application {

	private static final DecimalFormat df = new DecimalFormat("0.00");
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");

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
				System.out.println("****** SEARCH OP ***** \n");
				/*
				 * System.out.println("Enter Employee EID : "); eid = sc.nextLong();
				 * AppUtility.loader(); optEmp = employeeRepository.findById(eid);
				 * 
				 * if (optEmp.isPresent()) { employeeList = new ArrayList<Employee>();
				 * employeeList.add(optEmp.get()); AppUtility.displayRecords(employeeList); }
				 * else { System.err.println("employee not found by EID "); }
				 */

				System.out.println("select your choise \n");

				System.out.println("1] search by EID  ");
				System.out.println("2] search by first name  ");
				System.out.println("3] search by last name  ");
				System.out.println("4] search by birth date ");
				System.out.println("5] birtdate Between [yyyy-MM-dd]");
				System.out.println("6] First Name like ");
				System.out.println("7] First Name Start With  ");
				System.out.println("8] created date less than equal :");
				System.out.println("9] created date greater than equal ");
				System.out.println("10] Age By Name  ");
				int searchBy = sc.nextInt();
				employeeList = new ArrayList<>();
				switch (searchBy) {
				case 1:
					System.out.println("Please enter EId");
					optEmp = employeeRepository.findById(sc.nextLong());
					if (optEmp.isPresent()) {
						employeeList = new ArrayList<Employee>();
						employeeList.add(optEmp.get());
					}
					break;
				case 2:
					System.out.println("Please enter first name");
					employeeList = employeeRepository.findByFirstName(sc.next());
					break;
				case 3:
					System.out.println("Please enter last name");
					employeeList = employeeRepository.findByLastName(sc.next());
					break;

				case 4:
					System.out.println("Please enter birtdate [yyyy-MM-dd]");
					String dateStr = sc.next();
					Date datedob = DateUtils.convertStringToJUtilDateTime(dateStr);
					employeeList = employeeRepository.findByBirthDate(datedob);
					break;
				case 5:
					System.out.println("Please enter start date  : ");
					String dateStr1 = sc.next();
					System.out.println("Please enter end date  :");
					String dateStr2 = sc.next();
					employeeList = employeeRepository.findByBirthDateBetweenOrderByBirthDateAsc(
							DateUtils.convertStringToJUtilDateTime(dateStr1),
							DateUtils.convertStringToJUtilDateTime(dateStr2));
					break;

				case 6:
					System.out.println("enter first name :");
					employeeList = employeeRepository.findByFirstNameLike(sc.next());
					break;

				case 7:
					System.out.println("enter first name prefix :");
					employeeList = employeeRepository.findByFirstNameStartingWith(sc.next());
					break;

				case 8:
					System.out.println("enter date :");
					employeeList = employeeRepository.findByBasePropertiesCreatedOnLessThanEqual(DateUtils.convertStringToJUtilDateTime(sc.next()));
					break;
					
				case 9:
					System.out.println("enter date :");
					employeeList = employeeRepository.findByBasePropertiesCreatedOnGreaterThanEqual(DateUtils.convertStringToJUtilDateTime(sc.next()));
					break;
				
				case 10:
					System.out.println("enter first name :");
					int age  = employeeRepository.getAgeByName(sc.next());
					System.err.println("\n Age "+age);
					break;
				
				case 11:
					System.out.println("enter salary :");
					employeeList = employeeRepository.getSalaryGreaterThan(sc.nextDouble());
					break;
					
					
					
				default:
					System.err.println("Invalid Choice,try again\n");
					break;
				}
				if (employeeList != null && !employeeList.isEmpty()) {
					AppUtility.displayRecords(employeeList);
				} else {
					System.err.println("employees not found ");
				}
				break;
			case 5:

				/*
				 * System.out.println(
				 * "\n-------------------------------------------------------------------------------------------------------------"
				 * ); System.out.
				 * println("ID	|	NAME		|	STATUS 	|	AGE	| 	SALARY 		|	CREATED ON	 "
				 * ); System.out.println(
				 * "---------------------------------------------------------------------------------------------------------------"
				 * );
				 * 
				 * employeeRepository.findAll().forEach(emp->{
				 * 
				 * System.out.println(emp.getEid()+"\t|"+emp.getFirstName()+" "+emp.getLastName(
				 * )+"\t\t|\t"+emp.getStatus()+"\t|\t"+DateUtils.getAge(DateUtils.
				 * convertJUtilDateTimeToString(emp.getBirthDate()))+"\t|\t"+df.format(emp.
				 * getSalary())+"\t|\t"+sdf.format(emp.getBaseProperties().getCreatedOn()));
				 * 
				 * }); System.out.println(
				 * "-----------------------------------------------------------------------------------------------------------------\n"
				 * );
				 * 
				 * 
				 * 
				 */

				/*
				 * Page<Employee> findaEmployees = employeeRepository.findAll(PageRequest.of(1,
				 * 20)); System.out.println(findaEmployees.getContent().size());
				 */

				AppUtility.loader();
				System.out.println("\nEnter each page  size : ");
				Integer pageSize = sc.nextInt();
				System.err.println("note : now on all pages record size will be " + pageSize + "\n\n");
				Page<Employee> emmPages = employeeRepository.findAll(PageRequest.of(1, pageSize));
				System.out.println("Total Pages : " + emmPages.getTotalPages());
				System.out.println("pp : " + emmPages.getNumber());
				System.out.println("bbmppp : " + emmPages.getNumberOfElements());
				AppUtility.displayRecords(emmPages.getContent());

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

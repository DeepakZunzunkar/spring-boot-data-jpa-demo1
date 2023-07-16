package com.dz.app.utility;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.dz.app.entity.BaseProperties;
import com.dz.app.entity.Employee;
//import com.dz.app.service.EmployeeService;
/*import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.dz.app.model.entity.BaseProperties;
import com.dz.app.model.entity.Employee;
import com.dz.app.service.EmployeeService;
import com.dz.app.serviceImpl.EmployeeServiceImpl;
import com.dz.app.serviceImpl.ProjectionImpl;*/
import com.dz.app.utility.Constant.EmployeeStatus;
import com.dz.app.utility.Constant.Gender;


public class AppUtility {
	
	private static final DecimalFormat df = new DecimalFormat("0.00");
	private static final SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
//	static EmployeeService eService =null;
	static List<Employee> employees=null;
	static Double salary=0.00;
	static Double sum=0.00;
	static Double maxSalary=0.00;
	static Double minSalary=0.00;
	static Double avSalary=0.00;
	static Long totalEmp=0L;
	static Long activeEmp;
	static Long onLeaveEmp;
	static Long terminatedEmp;
	
	public static void loader(){
		System.out.print("Loading ");
		for(int i=0;i<=20;i++) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("#");
		}
		System.out.println("\n");
	}
	
	public  static void displayRecords(List<Employee> employeeList) {
		
		System.out.println("\n-------------------------------------------------------------------------------------------------------------");
		System.out.println("ID	|	NAME		|	STATUS 	|	AGE	| 	SALARY 		|	CREATED ON	 ");
		System.out.println("---------------------------------------------------------------------------------------------------------------");   
		
		if(employeeList!=null && !employeeList.isEmpty()) {
			
			for(Employee emp:employeeList) {
				System.out.println(emp.getEid()+"\t|"+emp.getFirstName()+" "+emp.getLastName()+"\t\t|\t"+emp.getStatus()+"\t|\t"+DateUtils.getAge(DateUtils.convertJUtilDateTimeToString(emp.getBirthDate()))+"\t|\t"+df.format(emp.getSalary())+"\t|\t"+sdf.format(emp.getBaseProperties().getCreatedOn()));
			}
//			employeeList.clear();
		}else {
			System.err.println("Not Found ...");
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------\n");
	}
	
	public  static void displayRecord(Employee emp) {
		
		System.out.println("\n-------------------------------------------------------------------------------------------------------------");
		System.out.println("ID	|	NAME		|	STATUS 	|	AGE	| 	SALARY 		|	CREATED ON	 ");
		System.out.println("---------------------------------------------------------------------------------------------------------------");   
		
		if(emp!=null) {
			System.out.println(emp.getEid()+"\t|"+emp.getFirstName()+" "+emp.getLastName()+"\t\t|\t"+emp.getStatus()+"\t|\t"+DateUtils.getAge(DateUtils.convertJUtilDateTimeToString(emp.getBirthDate()))+"\t|\t"+df.format(emp.getSalary())+"\t|\t"+sdf.format(emp.getBaseProperties().getCreatedOn()));
		}else {
			System.err.println("Not Found ...");
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------\n");
	}

/*	@SuppressWarnings("unchecked")
	public static void dumpEmployeeData(){

		//added employee is from HibernateDemo1 application 
		
		List<Employee> employeesList=null;
//		try(Session session=Factory.getSessionFactory("secondConfig.xml").openSession()) {
		try{
			SessionFactory sf1 = HibernateUtility.getSecondConfigHibernateConnection();
			Session session= sf1.openSession();
			Criteria criteria=session.createCriteria(Employee.class);
			employeesList=criteria.list();
			displayRecords(employeesList);
			
			session.close();
			sf1.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(Factory.sf.isClosed());
			Factory.sf.close();
			System.out.println(Factory.sf.isClosed());
			
		}
		
		if(employeesList!=null && !employeesList.isEmpty()) {
			
			eService=new EmployeeServiceImpl();
			Transaction tx=null;
			try(Session session=Factory.getSessionFactory().openSession()) {
				
				tx=session.beginTransaction();
				eService.deleteAll();
				
				employeesList.stream().forEach(emp ->{
					eService.saveEmployee(emp);
				});
				
				tx.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
*/	
	public static String colunmChoise(Scanner sc){
		System.out.println("1.firstName \t2.lastName \t3.gender \t4.status \t5.baseProperties.active");
		
		String column="";
		int choise = sc.nextInt();
	
		switch(choise){
			
			case 1:
					column ="firstName";
					break;
					
			case 2: 
					column ="lastName";
					break;
			
			case 3: 
					column ="gender";
					break;
			case 4: 
					column ="status";
					break;
			case 5: 
					column ="baseProperties.active";
					break;		
			default:	
					System.err.println("invalid choise");
					break;
		}
		return column;
	}
	
	public static String likeManuChoise(Scanner sc) {
		
		System.out.println("\n1.check Employee Name Start with letter Enter By User");
		System.out.println("2.check Employee Name Ends with letter Enter By User");

		System.out.println("\nSelect your choice ");
		String letter ="";
		
		int option = sc.nextInt();

		/*if (option == 1) {
			System.out.println("Enter Letter :");
			letter = sc.next();
			letter = letter + "%";
			
		}else if (option == 2) {
			System.out.println("Enter Letter :");
			letter = sc.next();
			letter = "%" + letter;
		}*/
		
		switch(option){
		
			case 1:
					System.out.println("Enter Letter :");
					letter = sc.next();
					letter = letter + "%";
					break;
					
			case 2: 
					System.out.println("Enter Letter :");
					letter = sc.next();
					letter = "%" + letter;
					break;
					
			default:	
					System.err.println("invalid choise");
					break;
		}
		
		return letter;
	}

	public static Map<Integer, List<String>> betweenManuChoise(Scanner sc) {
		System.out.println("\n");
		System.out.println("\t1.check Employee salary Between user Enter salary :");
		System.out.println("\t2.check Employee birthDate[yyyy-MM-dd] Between user Enter dates :");
		
		List<String> list= new ArrayList<>();
		Map<Integer, List<String>> selectedChoice=new HashMap<>();
		
		Integer option = sc.nextInt();
		switch(option){
		
		case 1:
				System.out.println("Enter lowest salary :");
				list.add(sc.next());
	
				System.out.println("Enter Highest salary :");
				list.add(sc.next());
				
				selectedChoice.put(option, list);
				
				break;
				
		case 2: 
				System.out.println("Enter start date :");
				 list.add(sc.next());
	
				System.out.println("Enter end date  :");
				list.add(sc.next());
				
				selectedChoice.put(option, list);
				
				break;
				
		default:	
				System.err.println("invalid choise");
				break;
		}
		return selectedChoice;
	}

	public static void initializeLandingPage() {
		
		/*totalEmp=ProjectionImpl.totalEmployeeCount();
		activeEmp=ProjectionImpl.employeeCountByStatus("A");
		onLeaveEmp=ProjectionImpl.employeeCountByStatus("L");
		terminatedEmp=ProjectionImpl.employeeCountByStatus("T");
		
		sum=ProjectionImpl.sum("salary");
		maxSalary=ProjectionImpl.max("salary");
		minSalary=ProjectionImpl.min("salary");
		avSalary=ProjectionImpl.avg("salary");
		
		
		eService=new EmployeeServiceImpl();*/
//		employees=eService.getAllEmployees();
//		displayRecords(employees);
//		employees.clear();
		
		System.out.println("Active Employee			: 	 "+activeEmp);
		System.out.println("On Leave Employee		: 	 "+onLeaveEmp);
		System.out.println("Terminated Employee		:	 "+terminatedEmp);
		System.out.println("--------------------------------------------------");
		System.out.println("Total Employee			: 	 "+totalEmp+"\n");
		System.out.println("sum of salary of All Employee :"+ df.format(sum) +"\n");
		System.out.println("Maximum salary among all Employee :"+ df.format(maxSalary) +"\n");
		System.out.println("Minimum salary among all Employee :"+df.format(minSalary) +"\n");
		System.out.println("Average of Employees salary :"+ df.format(avSalary) +"\n");
	}

	/*public static void OrderByDesc(String coulmnName) {
		List<Employee> employees=null;
		try(Session session=Factory.getSessionFactory().openSession()){
			
			Criteria cr = session.createCriteria(Employee.class);
			cr.addOrder(Order.desc(coulmnName));
			employees =cr.list();
			displayRecords(employees);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void OrderByAsc(String coulmnName) {
		List<Employee> employees=null;
		try(Session session=Factory.getSessionFactory().openSession()){
			
			Criteria cr = session.createCriteria(Employee.class);
			cr.addOrder(Order.asc(coulmnName));
			employees =cr.list();
			displayRecords(employees);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static List<Employee> getEmployeeOrderByEid() {
		List<Employee> employees=null;
		try(Session session=Factory.getSessionFactory().openSession()){
			
			Criteria cr = session.createCriteria(Employee.class);
			cr.addOrder(Order.desc("eid"));
			employees =cr.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	
	public static List<Employee> getEmployeeOrderByEidandPagination(Integer currentPage, Integer pageSize) {
		List<Employee> employees=null;
		try(Session session=Factory.getSessionFactory().openSession()){
			
			Criteria cr = session.createCriteria(Employee.class);
			cr.addOrder(Order.desc("eid"));
//			int page=4;
			int pageVal=currentPage-1;
			pageVal=pageVal*pageSize;
			
			cr.setFirstResult(pageVal);
			cr.setMaxResults(pageSize);
			
			// total pages 6
			// 0-4  , 5-9 ,  10-14 , 15-19 , 20-25 , 26-
//				1	   2	   3      4		  5			6
			
			employees =cr.list();
			displayRecords(employees);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	//find total pages , current page , per page 
	public static void pagination(Integer perPage,Integer totalPages,Integer currentPage) {
		
//		Double perPage=5.0;
		
//		totalEmp=ProjectionImpl.totalEmployeeCount();
//		System.out.println("total "+totalEmp);
		
//		The java.lang.Math.round() method in Java is used to round off the decimal numbers to their nearest integer value. 
//		This integer value can be higher or lower than the decimal value being rounded off.
//		This method takes both float and double variables as arguments.
		
		Double totalPages =(double) Math.round(27/perPage);
		//The result of the argument being rounded to an integer is calculated by adding 1/2 i.e. 0.5 
		//if result is less than 0.5 then it is round off to 0
		//if result is greater than 0.5 then it is round off to 1
		
		//The java.lang.Math.ceil() method is used to find the nearest integer value that is greater than or equal to the number given as an argument.
//		Double totalPages =(double) Math.ceil(totalEmp/perPage);
		
//		System.out.println(totalPages);
		//per page 5 records 
		//  28/5 =5.6 = 6
		
		
		getEmployeeOrderByEidandPagination(currentPage,perPage);
		
		System.out.println("Total Pages : "+totalPages +"\t\t|\t current page : "+currentPage);

		Scanner sc=new Scanner(System.in);
		
		System.out.println("\nEnter page number : ");
		Integer pageNumber= sc.nextInt();
		if(pageNumber <= totalPages) {
		}else {
			System.err.println("page not available ...");
		}

	}
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);

		System.out.println("\nEnter page each size : ");
		int pageSize =sc.nextInt();
		System.err.println("note : now on all pages record size will be "+pageSize +"\n\n");
		String choice="yes";
		Integer currentPage=1;
		
		totalEmp=ProjectionImpl.totalEmployeeCount();
		
		//here both deviser and divident should be of  double type. 
		Double totalPages =(double) Math.ceil(28.0/3.0);
		System.out.println("Total Pages : "+totalPages +"\t current page : "+currentPage);
		
//			getEmployeeOrderByEidandPagination(currentPage,pageSize);
		
		
		
		do{
			
//			System.out.println("Enter page number : ");
//			pagination(pageSize,totalPages.intValue(),1);
			
			pagination(pageSize,totalPages.intValue(),currentPage);
			System.out.println("\n\n exit from pagination menu ?  (yes[y] / no[n] ):");
			choice=sc.next();
			
		}while(choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"));
	}*/
	
	public static Employee setEmployeeForm(Scanner sc,String label,Employee trn) {
		
		
		if(trn!=null) {
			trn.getBaseProperties().setUpdatedBy("spring-boot-demo1");
			trn.getBaseProperties().setUpdatedOn(new Date());
		}else {
			trn = new Employee(new BaseProperties("A",new Date(),"spring-boot-demo1",null, null));
		}
		
		if(label.equalsIgnoreCase("ADD") || label.equalsIgnoreCase("ALL")|| label.equalsIgnoreCase("NAMES")) {
			
			System.out.println("First Name________:\n");
			trn.setFirstName(sc.next());
			
			System.out.println("Last Name_________:\n");
			trn.setLastName(sc.next());
			
		}
		if(label.equalsIgnoreCase("ADD") || label.equalsIgnoreCase("ALL") || label.equalsIgnoreCase("GENDER")){
			
			System.out.println("Gender \n 1.Male\n 2.Female : ");
			int gChoice =sc.nextInt();
			switch (gChoice)
			{
				case 1:	
						trn.setGender(Gender.MALE.getGenderValue());
						break;
				case 2: 
						trn.setGender(Gender.FEMALE.getGenderValue());
						break;
				default:
						trn.setGender("NA");
						break;
			}	
			
		}
		if(label.equalsIgnoreCase("ADD") || label.equalsIgnoreCase("ALL") || label.equalsIgnoreCase("DOB")){
			
			System.out.println("Date Of Birth [ yyyy-mm-dd ] _________:\n");
			String dateStr = sc.next();
			trn.setBirthDate(DateUtils.convertStringToJUtilDateTime(dateStr));
			
		}
		if(label.equalsIgnoreCase("ADD") || label.equalsIgnoreCase("STATUS")) {
			trn.setStatus(label.equalsIgnoreCase("STATUS")? trn.getStatus():EmployeeStatus.ACTIVE.getEmployeeStatusCode());
		}
		if(label.equalsIgnoreCase("ADD")){
			System.out.println("Salary per month ________:\n");
			trn.setSalary(Double.parseDouble(sc.next()));
		}
				
		return trn;
	}
	
	public static Employee updateEmployeeForm(Scanner sc, Employee empTrn) {
		
		System.out.println("select your choise \n");
		
		System.out.println("1]update first and last name  ");
		System.out.println("2]update gender ");
		System.out.println("3]update dob ");
		System.out.println("4]update all data\n");
		
		int choice =sc.nextInt();
		
		switch (choice)
		{
			case 1:	
					empTrn= setEmployeeForm(sc,"NAMES",empTrn);
					break;
			case 2: 
					empTrn= setEmployeeForm(sc,"GENDER",empTrn);
					break;
			case 3:	
					empTrn= setEmployeeForm(sc,"DOB",empTrn);
					break;
			case 4: 
					empTrn= setEmployeeForm(sc,"ALL",empTrn);
					break;
			default:
					System.err.println("Invalid Choice,try again\n");
					break;
		}	
		
		return empTrn;
	}
}	

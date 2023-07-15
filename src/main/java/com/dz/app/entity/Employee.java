package com.dz.app.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


@Entity
@Table(name="Adpemployee",schema="EMPRDEV")
public class Employee {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long eid;
    
    @Column(name="FIRSTNAME")
    private String firstName;
    
    @Column(name="MIDDLENAME")
    private String middleName;
    
    @Column(name="LASTNAME")
    private String lastName;
    
//    @Enumerated(EnumType.STRING)
    @Column(name="GENDER")
    private String gender;

//    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private String status;

    @Embedded
    	@AttributeOverrides({
    		@AttributeOverride(name="createdOn",column=@Column(name="CREATEDON",updatable= false))
    	})
    private BaseProperties baseProperties;
    
    @Column(name="birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
//    
//    @Transient
//    private Integer age;

    @Column(name="SALARY")
    private Double salary;
    
	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BaseProperties getBaseProperties() {
		return baseProperties;
	}

	public void setBaseProperties(BaseProperties baseProperties) {
		this.baseProperties = baseProperties;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	
//	public Integer getAge() {
//		return age;
//	}
//
//	public void setAge(Integer age) {
//		this.age = age;
//	}
//


	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}


	public Employee() {
		super();
	}

	public Employee(String firstName, String gender) {
		super();
		this.firstName = firstName;
		this.gender = gender;
	}

	public Employee(BaseProperties baseProperties) {
		super();
		this.baseProperties = baseProperties;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", gender=" + gender + ", status=" + status + ", baseProperties=" + baseProperties
				+ ", birthDate=" + birthDate + ", salary=" + salary + "]";
	}
	
}

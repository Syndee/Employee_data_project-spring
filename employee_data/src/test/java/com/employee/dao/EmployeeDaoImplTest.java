/**
 * 
 */
package com.employee.dao;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import com.employee.entity.Employee;

/**
 * @author User
 *
 */

@Sql(scripts= {"classpath:/db/create-table.sql","classpath:/db/insert-employees.sql"})
@ContextConfiguration("classpath:data-context.xml")
@RunWith(SpringRunner.class)
public class EmployeeDaoImplTest {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private EmployeeDao employeeDaoImpl;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void saveEmployeeToDbTest() {
		
		Date employeeDate = Date.valueOf("2000-07-24");
		
		Employee newEmployee = new Employee();
		newEmployee.setFirstname("Mary");
		newEmployee.setLastname("Black");
		newEmployee.setEmail("mary@mail.com");
		newEmployee.setPhoneNumber("07086453214");
		newEmployee.setDateOfBirth(employeeDate);
		
		assertThat(employeeDaoImpl).isNotNull();
		employeeDaoImpl.saveEmployee(newEmployee);
		
		int id = newEmployee.getEmployeeId();
		
		System.out.println("New Employee Id -->" + id);
		
		Employee existingEmployee= employeeDaoImpl.getById(id);
		assertThat(existingEmployee).isNotNull();
		
	}
	

	@Test
	public void getEmployeeByEmail() {
		assertThat(employeeDaoImpl).isNotNull();
		Employee savedEmployee= employeeDaoImpl.getByEmail("Sola@mail.com");
		
		assertThat(savedEmployee).isNotNull();
		assertThat(savedEmployee.getEmployeeId()).isEqualTo(2);
		System.out.println(savedEmployee);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

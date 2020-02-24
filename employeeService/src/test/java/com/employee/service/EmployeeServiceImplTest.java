package com.employee.service;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.dao.EmployeeDao;
import com.employee.entity.Employee;

@ContextConfiguration("classpath:service-context.xml")
@RunWith(SpringRunner.class)
public class EmployeeServiceImplTest {
	
	@Mock
	private EmployeeDao employeeDaoImpl;

	@Before
	public void setUp() throws Exception {
		employeeDaoImpl = mock(EmployeeDao.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void saveEmployeeTest() {
		
		//create an object 
		Employee tempEmployee = new Employee();
		
		//define mock method call
		doNothing().when(employeeDaoImpl).saveEmployee(isA(Employee.class));
		
		// make a mock call
		employeeDaoImpl.saveEmployee(tempEmployee);
		
		//verify the method was called
		verify(employeeDaoImpl, times(1)).saveEmployee(tempEmployee);
	}

}

/**
 * 
 */
package com.ark.excel.generator.test;

import java.util.ArrayList;
import java.util.List;

import com.ark.excel.generator.ExcelUtils;
import com.ark.excel.generator.model.Department;
import com.ark.excel.generator.model.Employee;
import com.ark.excel.generator.model.EmployeeDetails;
import com.ark.excel.generator.model.EmployeeModel;
import com.ark.excel.generator.model.Salary;

/**
 * @author Joby Wilson Mathews
 *
 */
public class ExcelGeneratorTest {

	/**
	 * @param args
	 */
	public static void display(int a) {
		System.out.println("int");
	}
	public static void display(Integer a) {
		System.out.println("Integer");
	}
	public static void main(String[] args) {
		EmployeeModel employeeDetails=new EmployeeModel();
		employeeDetails.setFirstname("Joby");
		employeeDetails.setLastname("Wilson");
		employeeDetails.setDepartmentName("Computer");
		employeeDetails.setAmount("100000");
	
		List<EmployeeModel> aList =new ArrayList<EmployeeModel>(); 
		aList.add(employeeDetails);
		aList.add(employeeDetails);
		aList.add(employeeDetails);
		aList.add(employeeDetails);
		aList.add(employeeDetails);
		aList.add(employeeDetails);
		
		ExcelUtils excelUtils=new ExcelUtils();
		excelUtils.writeToExcel(aList);

	}

}

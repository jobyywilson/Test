/**
 * 
 */
package com.ark.excel.generator.model;

import com.ark.excel.annotations.Data;
import com.ark.excel.annotations.Font;
import com.ark.excel.annotations.Style;

/**
 * @author Joby Wilson Mathews
 *
 */
@Style(fillPattern=1,fillForegroundColor = 11 ,font=@Font(bold=true))
public class EmployeeDetails {
	@Data(fieldName="Employee Details")
	private Employee employee;
	@Data(fieldName="Department Details")
	private Department department;
	@Data(fieldName="Salary Details")
	private Salary salary;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	
}

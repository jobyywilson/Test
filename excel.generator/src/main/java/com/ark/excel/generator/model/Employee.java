/**
 * 
 */
package com.ark.excel.generator.model;

import com.ark.excel.annotations.Data;
import com.ark.excel.annotations.Style;

/**
 * @author Joby Wilson Mathews
 *
 */
@Style(fillPattern=1,fillForegroundColor = 20)
public class Employee {
	
	@Data(referenceFieldName="firstname" ,fieldName="First Name" ,style=@Style(fillPattern=1,fillForegroundColor=30))
	private String firstname;
	@Data(referenceFieldName="lastname" ,fieldName="Last Name")
	private String lastname;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}

package com.ark.excel.generator.model;

import com.ark.excel.annotations.Data;
import com.ark.excel.annotations.Style;

@Style(fillPattern=1,fillForegroundColor = 20)
public class Department {
	@Data(referenceFieldName="departmentName",fieldName="Department Name")
	private String departmentName;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}

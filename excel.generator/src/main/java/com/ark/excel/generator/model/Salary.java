package com.ark.excel.generator.model;

import com.ark.excel.annotations.Data;
import com.ark.excel.annotations.Style;

@Style(fillPattern=1,fillForegroundColor = 20)
public class Salary {
	@Data(referenceFieldName="amount")
	private String amount;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}

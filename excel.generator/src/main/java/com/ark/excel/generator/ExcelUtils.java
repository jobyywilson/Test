package com.ark.excel.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ark.excel.annotations.Data;
import com.ark.excel.annotations.Font;
import com.ark.excel.annotations.Style;
import com.ark.excel.generator.model.EmployeeDetails;

/**
 * @author Joby Wilson Mathews
 * @param <T>
 *
 */
public class ExcelUtils<T> {
	
	XSSFWorkbook workbook = null;
	List<T> data;
	Sheet sheet;
	public final static String DEFAULT_VALUE = "-1";
	public T getStyle(Class<?> clazzOfMethod,Annotation[] styles,Object cellStyle) {
		if(styles.length>0) {
			Method[] styleMethod = styles[0].annotationType().getDeclaredMethods();
			for (Method method : styleMethod) {
				try {
					Object value =  method.invoke(styles[0]);
					if(method.getName().equals("font")) {
						Font fontValue=((Style)styles[0]).font();
						org.apache.poi.ss.usermodel.Font font = workbook.createFont();
						((CellStyle)cellStyle).setFont((org.apache.poi.ss.usermodel.Font) getStyle(XSSFFont.class,new Font[] {fontValue},font));
					}else if(!value.toString().equals(DEFAULT_VALUE)) {
						
						Method xSSFMethod = clazzOfMethod.getDeclaredMethod("set" + capitalize(method.getName()),getType(value));
						xSSFMethod.invoke(cellStyle, value);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}				   
		}
		return (T) cellStyle;
	}
	
	public CellStyle getStyleForField(Field field,Class<?> parentClass ) {
		CellStyle cellStyle =workbook.createCellStyle();
		cellStyle=(CellStyle) getStyle(XSSFCellStyle.class,field.getAnnotationsByType(Style.class),cellStyle);
		cellStyle=(CellStyle) getStyle(XSSFCellStyle.class,parentClass.getAnnotationsByType(Style.class),cellStyle);		
		return cellStyle;	

	}
	public Class<?> getType(Object value) {
		if(value.getClass() == Short.class) {
			return short.class;
		}
		else if(value.getClass() == Integer.class ) {
			return int.class;
		}
		else if(value.getClass() == Boolean.class ) {
			return boolean.class;
		}
		return value.getClass();
    }

    public Class<?> getType(Integer i) {
        return int.class;
    }

    public void fillData(Integer rowCount , Integer columnCount,Field fieldz) {
    	Row row ;
    	try {
    	Data[] datas =fieldz.getAnnotationsByType(Data.class);
    	if(datas.length>0)
    	{
    		Method method=datas[0].annotationType().getDeclaredMethod("referenceFieldName");
        	String referenceFieldName=(String) method.invoke(datas[0]);
        	
        	method=datas[0].annotationType().getDeclaredMethod("style");
        	Style style=((Data)datas[0]).style();
        	CellStyle cellStyle =workbook.createCellStyle();
    		cellStyle=(CellStyle) getStyle(XSSFCellStyle.class,new Style[] {style},cellStyle);
    		
    			Field field = data.get(0).getClass().getDeclaredField(referenceFieldName);    
    			field.setAccessible(true);
    			for(T t:data) {
    				Object value = field.get(t);
    				rowCount=rowCount+1;
    				if((row=sheet.getRow(rowCount))==null) {
    					row = sheet.createRow(rowCount);
    				}
    				Cell cell = row.createCell(columnCount);
    				cell.setCellValue(value.toString());
    				cell.setCellStyle(cellStyle);
    			}
    	}
    	
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public String getFieldName(Field field) {
    	try {
			Data[] datas =field.getAnnotationsByType(Data.class);
			if(datas.length>0)
			{
				Method method=datas[0].annotationType().getDeclaredMethod("fieldName");
				String fieldName;
				if(!((fieldName=(String) method.invoke(datas[0])).equals(DEFAULT_VALUE)))
				return fieldName;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return field.getName();
    }
	public  Integer generateExcel(Integer rowCount , Integer columnCount,Class<?> clazz ) {
		
		rowCount=rowCount+1;
		Row row ;
		if((row=sheet.getRow(rowCount))==null) {
			row = sheet.createRow(rowCount);
		} 
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Cell cell = row.createCell(columnCount);
			cell.setCellValue(getFieldName(field));
			cell.setCellStyle(getStyleForField(field,clazz ));
			
			if(field.getType() == String.class || field.getType() == Integer.class) {
				fillData(rowCount,columnCount,field);
			}
			else {
				Integer childrenColumnCount=generateExcel(rowCount,columnCount,field.getType());
				sheet.addMergedRegion(new CellRangeAddress(rowCount,rowCount,columnCount,childrenColumnCount));				
				columnCount=childrenColumnCount;
			}
			columnCount=columnCount+1;
			
		}	
		return columnCount-1;
		
	}
	
	public  void writeToExcel( List<T> data) {
	OutputStream fos = null;
	this.data=data;
	try {
		File file = new File("D:\\excel.xls");
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet();
		int rowCount = 0;
		int columnCount = 0;
		generateExcel(rowCount,columnCount,EmployeeDetails.class);
		fos = new FileOutputStream(file);
		workbook.write(fos);
		fos.flush();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (fos != null) {
				fos.close();
			}
		} catch (IOException e) {
		}
		try {
			if (workbook != null) {
				workbook.close();
			}
		} catch (IOException e) {
		}
	}
}

private static String capitalize(String s) {
	if (s.length() == 0)
		return s;
	return s.substring(0, 1).toUpperCase() + s.substring(1);
}
}

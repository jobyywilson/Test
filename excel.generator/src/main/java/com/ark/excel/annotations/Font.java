package com.ark.excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.FIELD, ElementType.TYPE})  
public @interface Font {
	
	String fontName() default "-1";
	
	short fontHeight() default -1; 
	
	short fontHeightInPoints() default -1; 
	
	boolean italic() default false; 
	
	boolean strikeout() default false; 
	
	short color() default -1; 
	
	short typeOffset() default -1; 
	
	byte underline() default -1; 
	
	byte charSet() default -1;
	
	short boldweight() default -1;
	
	boolean bold() default false;
	
}

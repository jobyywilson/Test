package com.ark.excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Joby Wilson Mathews
 *
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.FIELD, ElementType.TYPE})  
public @interface Style {
	
	Font font() default @Font();

	short dataFormat() default -1; 
	
	boolean hidden() default false; 
	
	boolean locked() default false; 
	
	short alignment() default -1; 
	
	boolean wrapText() default false; 
	
	short verticalAlignment() default -1; 
	
	short rotation() default -1;
	
	short indention() default -1;
	
	short borderLeft() default -1;
	
	short borderRight() default -1;
	
	short borderTop() default -1;
	
	short borderBottom() default -1;
	
	short leftBorderColor() default -1;
	
	short rightBorderColor() default -1;
	
	short topBorderColor() default -1;
	
	short bottomBorderColor() default -1;

	short fillPattern() default -1; 
	
	short fillBackgroundColor() default -1; 
	
	short fillForegroundColor() default -1; 
    
	boolean shrinkToFit() default false; 

}

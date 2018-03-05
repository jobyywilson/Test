/**
 * 
 */
package com.ark.excel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Joby Wilson Mathews
 *
 */
@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.FIELD})
public @interface Data {
	
	Style style() default @Style();
	
	String fieldName() default "-1";
	
	String referenceFieldName() default "-1";
}

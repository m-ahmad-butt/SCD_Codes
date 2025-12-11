package org.example.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * @Header(className = "Student", author = "Farooq", dateCreated = "21-Oct-2025")
 */
public @interface Header {
    String className();
    String author() default "";
    String dateCreated() default "";
}

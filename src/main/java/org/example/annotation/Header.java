package org.example.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Example custom annotation to store simple metadata about a class.
 * Usage example (from your notes):
 * @Header(className = "Student", author = "Farooq", dateCreated = "21-Oct-2025")
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Header {
    String className();
    String author() default "";
    String dateCreated() default "";
}

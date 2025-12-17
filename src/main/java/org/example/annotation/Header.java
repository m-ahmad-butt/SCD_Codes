package org.example.annotation;
/**
 * @Header(className = "Student", author = "Farooq", dateCreated = "21-Oct-2025")
 */
public @interface Header {
    String className();
    String author() default "";
    String dateCreated() default "";
}

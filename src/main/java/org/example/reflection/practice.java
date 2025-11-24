// package org.example.reflection;
// package org.example.annotation;

// import java.lang.reflect.*;

// import org.example.annotation.StudentWithHeader;

// public class practice {
//     public static void main(String[] args) {
//         // Change this to any class you want to inspect
//         Class<?> cls = StudentWithHeader.class;

//         System.out.println("Class Name: " + cls.getName());
//         System.out.println("Simple Name: " + cls.getSimpleName());
//         System.out.println("Package: " + cls.getPackageName());
//         System.out.println("-----------------------------------");

//         // --- Constructors ---
//         System.out.println("Constructors:");
//         for (Constructor<?> cons : cls.getDeclaredConstructors()) {
//             System.out.println("  " + cons);
//         }
//         System.out.println("-----------------------------------");

//         // --- Fields / Members ---
//         System.out.println("Fields:");
//         for (Field field : cls.getDeclaredFields()) {
//             System.out.println("  " + Modifier.toString(field.getModifiers()) + " " +
//                                field.getType().getSimpleName() + " " +
//                                field.getName());
//         }
//         System.out.println("-----------------------------------");

//         // --- Methods ---
//         System.out.println("Methods:");
//         for (Method method : cls.getDeclaredMethods()) {
//             System.out.print("  " + Modifier.toString(method.getModifiers()) + " " +
//                              method.getReturnType().getSimpleName() + " " +
//                              method.getName() + "(");
//             // list parameters
//             Class<?>[] params = method.getParameterTypes();
//             for (int i = 0; i < params.length; i++) {
//                 System.out.print(params[i].getSimpleName());
//                 if (i < params.length - 1) System.out.print(", ");
//             }
//             System.out.println(")");
//         }
//         System.out.println("-----------------------------------");

//         // --- Superclass and Interfaces ---
//         System.out.println("Superclass: " + cls.getSuperclass());
//         System.out.println("Interfaces:");
//         for (Class<?> iface : cls.getInterfaces()) {
//             System.out.println("  " + iface.getName());
//         }
//     }
// }


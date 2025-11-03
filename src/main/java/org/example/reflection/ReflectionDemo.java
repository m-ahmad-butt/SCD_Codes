package org.example.reflection;

import org.example.annotation.Header;
import org.example.annotation.StudentWithHeader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Small reflection demo showing class introspection, annotation reading,
 * dynamic instantiation, and field/method access.
 */
public class ReflectionDemo {
    public static void main(String[] args) throws Exception {
        Class<StudentWithHeader> cls = StudentWithHeader.class;

        System.out.println("Class: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        System.out.println("Is interface: " + cls.isInterface());
        System.out.println("Superclass: " + cls.getSuperclass().getName());

        // List fields
        System.out.println("\nFields:");
        for (Field f : cls.getDeclaredFields()) {
            System.out.printf("  %s %s %s\n", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName());
        }

        // List constructors
        System.out.println("\nConstructors:");
        for (Constructor<?> c : cls.getDeclaredConstructors()) {
            System.out.println("  " + c);
        }

        // List methods
        System.out.println("\nMethods:");
        for (Method m : cls.getDeclaredMethods()) {
            System.out.println("  " + m);
        }

        // Read our @Header annotation (from annotation package)
        Header hdr = cls.getAnnotation(Header.class);
        if (hdr != null) {
            System.out.println("\n@Header:");
            System.out.println("  className = " + hdr.className());
            System.out.println("  author = " + hdr.author());
            System.out.println("  dateCreated = " + hdr.dateCreated());
        }

        // Dynamic instantiation using no-arg constructor
        System.out.println("\nInstantiate via reflection and set fields:");
        Constructor<StudentWithHeader> noArg = cls.getDeclaredConstructor();
        noArg.setAccessible(true);
        StudentWithHeader inst = noArg.newInstance();

        // Set private fields
        Field firstName = cls.getDeclaredField("firstName");
        Field lastName = cls.getDeclaredField("lastName");
        Field id = cls.getDeclaredField("id");
        firstName.setAccessible(true);
        lastName.setAccessible(true);
        id.setAccessible(true);

        firstName.set(inst, "ReflectedFirst");
        lastName.set(inst, "ReflectedLast");
        id.setInt(inst, 42);

        // Call toString to show values
        System.out.println("  instance: " + inst);

        // Invoke getter method via reflection (optional)
        Method getFirst = cls.getMethod("getFirstName");
        System.out.println("  getFirstName() via reflection: " + getFirst.invoke(inst));
    }
}

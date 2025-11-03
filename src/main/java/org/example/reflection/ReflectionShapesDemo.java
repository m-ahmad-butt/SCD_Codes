package org.example.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Demo that uses reflection to inspect and manipulate Rectangle and Circle.
 */
public class ReflectionShapesDemo {
    public static void main(String[] args) throws Exception {
        inspectClass(Rectangle.class);
        inspectClass(Circle.class);

        // instantiate rectangle using constructor
        Constructor<Rectangle> rCons = Rectangle.class.getDeclaredConstructor(double.class,double.class,double.class,double.class);
        Rectangle r = rCons.newInstance(1.0,2.0,3.0,4.0);
        System.out.println("Before move: " + r + " area=" + r.area());

        // use reflection to call move
        Method move = Rectangle.class.getMethod("move", double.class, double.class);
        move.invoke(r, 5.0, -1.0);
        System.out.println("After move via reflection: " + r);

        // read and write private fields of circle
        Circle c = new Circle(0,0,2.0);
        System.out.println("Circle before change: " + c + " area=" + c.area());
        Field radiusField = Circle.class.getDeclaredField("radius");
        radiusField.setAccessible(true);
        radiusField.setDouble(c, 3.0);
        System.out.println("Circle after radius change via reflection: " + c + " area=" + c.area());
    }

    private static void inspectClass(Class<?> cls) {
        System.out.println("\n--- Inspecting: " + cls.getSimpleName() + " ---");
        System.out.println("Fields:");
        for (Field f : cls.getDeclaredFields()) {
            System.out.println("  " + f);
        }
        System.out.println("Constructors:");
        for (Constructor<?> c : cls.getDeclaredConstructors()) {
            System.out.println("  " + c);
        }
        System.out.println("Methods:");
        for (Method m : cls.getDeclaredMethods()) {
            System.out.println("  " + m);
        }
    }
}

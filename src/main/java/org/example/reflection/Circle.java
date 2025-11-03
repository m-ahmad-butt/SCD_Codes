package org.example.reflection;

/**
 * Circle with private fields to demonstrate reflection access.
 */
public class Circle implements Shape {
    private double cx;
    private double cy;
    private double radius;

    public Circle() { this(0,0,1); }

    public Circle(double cx, double cy, double radius) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public void move(double dx, double dy) {
        this.cx += dx;
        this.cy += dy;
    }

    public double getCx() { return cx; }
    public double getCy() { return cy; }
    public double getRadius() { return radius; }
    public void setRadius(double radius) { this.radius = radius; }

    @Override
    public String toString() {
        return "Circle[cx="+cx+",cy="+cy+",r="+radius+"]";
    }
}

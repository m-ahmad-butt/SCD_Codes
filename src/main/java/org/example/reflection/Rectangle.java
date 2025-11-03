package org.example.reflection;

/**
 * Rectangle with private fields to demonstrate reflection access.
 */
public class Rectangle implements Shape {
    private double x; // origin x
    private double y; // origin y
    private double width;
    private double height;

    public Rectangle() {
        this(0,0,1,1);
    }

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return Math.abs(width * height);
    }

    @Override
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }

    public void setWidth(double width) { this.width = width; }
    public void setHeight(double height) { this.height = height; }

    @Override
    public String toString() {
        return "Rectangle[x="+x+",y="+y+",w="+width+",h="+height+"]";
    }
}

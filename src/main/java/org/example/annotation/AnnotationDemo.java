package org.example.annotation;

public class AnnotationDemo {
    public static void main(String[] args) {
        Class<StudentWithHeader> cls = StudentWithHeader.class;
        Header hdr = cls.getAnnotation(Header.class);
        if (hdr != null) {
            System.out.println("Header metadata for " + cls.getSimpleName() + ":");
            System.out.println("  className: " + hdr.className());
            System.out.println("  author: " + hdr.author());
            System.out.println("  dateCreated: " + hdr.dateCreated());
        } else {
            System.out.println("No @Header annotation present on " + cls.getSimpleName());
        }
    }
}

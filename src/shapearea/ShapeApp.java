package shapearea;

import java.util.Scanner;
class Shape{
    double area(double length,double breadth){
        return length *breadth;
    }
    double area(double radius) {
        return Math.PI*radius*radius;
    }
    double area(double base,double height,String type) {
        return 0.5*base*height;
    }
}
public class ShapeApp{public static void main(String[] args){
    Shape s=new Shape();
    System.out.printf("Circle ar for r=7: %.2f%n",s.area(7));
    System.out.printf("Rectangle ar 8x5: %.2f%n",s.area(8,5));
    System.out.printf("Triangle ar b=6,h=4: %.2f%n",s.area(6,4,"triangle"));
  }
}

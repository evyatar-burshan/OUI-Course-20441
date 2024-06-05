import java.util.Scanner;
/*
 * This program takes 3 inputs from the user that represent the sides of a triangle. Firstly, the program checks if the sides are valid sides of a triangle.
 * Then, it checks what type of triangle it is. If it doesn't find a special type it prints out it's a common triangle.
 */
public class Triangle {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // The following 6 lines request the user for input on the sides of the triangle
        System.out.println("Enter side X of the triangle:");
        int xSide = scan.nextInt();
        System.out.println("Enter side Y of the triangle:");
        int ySide = scan.nextInt();
        System.out.println("Enter side Z of the triangle:");
        int zSide = scan.nextInt();
        
        // Checking if the inputs represent a valid triangle and the type of the triangle
        if (((xSide + ySide > zSide) && (xSide + zSide > ySide) && (zSide + ySide > xSide)) && (xSide > 0 && ySide > 0 && zSide > 0)) { // Checks if the triangle is valid
            if (xSide == ySide && xSide == zSide) { // Checks if the triangle is equilateral
                System.out.println("The numbers: X, Y and Z represent an equilateral triangle");
            } else if (((xSide == ySide) && (xSide != zSide)) || ((xSide == zSide) && (xSide != ySide)) || ((zSide == ySide) && (xSide != zSide))) { // Checks if the triangle is an isosceles triangle
                System.out.println("The numbers: X, Y and Z represent an isosceles triangle");
            } else if ((xSide*xSide + ySide*ySide == zSide*zSide) || (xSide*xSide + zSide*zSide == ySide*ySide) || (zSide*xSide + zSide*zSide == xSide*xSide)) { // Checks if the triangle is a right-angle triangle
                System.out.println("The numbers: X, Y and Z represent a right-angle triangle");
            } else { // If the triangle is none of the above, print it's a common triangle
                System.out.println("The numbers: X, Y and Z represent a common triangle");
            }
        } else { // Prints out thatt the input is invalid to the user
            System.out.println("The numbers: X, Y and Z cannot represent a triangle");
        }
    }
}
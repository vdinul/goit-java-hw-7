/*VOLODYMYR DINUL
GOIT
homework #7
*****
The program prompts the user to choose one of the five shapes: rectangle, squire, circle, triangle or rhombus.
Depending on the user's input for the type of the shape the program prompts the user for the dimensions of those shapes.
For each shape the area and the perimeter is calculated with the dimensions provided by the user.
The results are outputted to the console.

*****
Abstract Class Shape defines two abstract methods, one for calculating the area and one for calculating the perimeter
of the shape.  Class Shape also has a fully implemented method for printing out the results of the computations to the console.

There are five shape classes, Rectangle, Squire, Circle, Triangle and Rhombus.  Each of these five extends Shape and
implements the methods for calculating the area and the perimeter.
* */


//package main.java.ua.goit.hw7.simpleShape;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Shape {
    private double dim1;  // Every shape has one linear dimention

    public Shape(double dim1) {
        this.dim1 = dim1;
    }

    public double getDim1() {
        return dim1;
    }

    public abstract double calcArea(); // every shape should have its own method to calculate the area

    public abstract double calcPerimetro(); // every share should have its own method to calculate the perimetro

    // method to output to the console the results and the name of the shape
    public void printShape(String name, double area, double perimetro) {
        System.out.println("Our shape is " + name.toUpperCase() + "." +
                "\nThe " + name.toLowerCase() + "'s area is " + area + " and " +
                "the " + name.toLowerCase() + "'s perimetro is " + perimetro + ".");
    }
}

// Class rectangle extends Shape and calculates the area and perimetro of the rectangle
class Rectangle extends Shape {
    private double dim2;    // the length of side 2 of the rectangle.
                            // Variable dim1 in class Shape holds the length of side 1

    public Rectangle(double dim1, double dim2) {
        super(dim1);
        this.dim2 = dim2;
    }

    public double calcArea() {
        return getDim1() * dim2;
    }

    public double calcPerimetro() {
        return 2 * (getDim1() + dim2);
    }
}

//class Squire extends Shape and calculates the area and the perimeter of the squire
class Squire extends Shape {

    public Squire(double dim1) {
        super(dim1);  // for a squire one just can do with one dimention, which is the length of the side
    }

    public double calcArea() {
        return getDim1() * getDim1();
    }

    public double calcPerimetro() {
        return getDim1() * 4;
    }
}

/* class triangle extends Shape and calculates the area and the perimeter of triangle

 We need the lengths of the three sides to calculate the perimeter of a triangle and its altitude to calculate the area.
 Hence, three additional variables in this class
*/
class Triangle extends Shape {
    private double dim2;
    private double altitude;
    private double basis;

    public Triangle(double dim1, double dim2, double altitude, double basis) {
        super(dim1);
        this.dim2 = dim2;
        this.altitude = altitude;
        this.basis = basis;
    }

    public double calcArea() {
        return (altitude * basis) / 2;
    }

    public double calcPerimetro() {
        return (getDim1() + dim2 + basis);
    }
}

// class circle extends Shape and calculates the area and the circumference of the circle
class Circle extends Shape {

    public Circle(double dim1) {
        super(dim1);    //radius is the only dimention necessary to calculate the are and the circumference of a circle
                        // we get it from the superclass
    }

    public double calcArea() {
        return getDim1() * getDim1() * Math.PI;
    }

    public double calcPerimetro() {
        return getDim1() * 2 * Math.PI;
    }

    // circle, unlike other shapes, does not have a perimeter.  It has circumference.
    // accordingly, have to adjust the print method
    @Override
    public void printShape(String name, double area, double perimetro) {
        System.out.println("Our shape is " + name.toUpperCase() + "." +
                "\nThe " + name.toLowerCase() + "'s area is " + area + " and " +
                "the " + name.toLowerCase() + "'s circumference is " + perimetro + ".");
    }
}

// class rhombus extends Shape and calculates the area and the perimeter of the circle
class Rhombus extends Shape {
    private double altitude;  // altitude of the rhombus

    public Rhombus(double dim1, double altitude) {
        super(dim1);
        this.altitude = altitude;
    }

    public double calcArea () {
        return getDim1() * altitude;
    }

    public double calcPerimetro() {
        return getDim1() * 4;
    }
}

// class GetUserInput deals with collecting the user's input from the console
class GetUserInput {

    // reader for the user's input
    public BufferedReader consoleReader() {
        return new BufferedReader(new InputStreamReader(System.in));
     }

    /* method getShape() prompts the user to choose one of the five shapes
    * it returns a string which is the name of the shape selected */
    public String getShape () {

        String userInput = "";
        System.out.println("Please type which shape would you like to work with:" +
                "\n\tRectangle" +
                "\n\tSquire" +
                "\n\tRhombus" +
                "\n\tTriangle" +
                "\n\tCircle" +
                "\nPlease type 'exit' to quit.");

        try {
            // the loop goes in circles until the user cooperates and enters the required form of shape of 'exit'
            while (true) {
                userInput = consoleReader().readLine();  // reading the user's input

                // checking whether the user' input is one of the six words we expect from her/him
                if (userInput.toLowerCase().equals("rectangle") ||
                        userInput.toLowerCase().equals("squire") ||
                        userInput.toLowerCase().equals("rhombus") ||
                        userInput.toLowerCase().equals("triangle") ||
                        userInput.toLowerCase().equals("circle") ||
                        userInput.toLowerCase().equals("exit"))
                    break;
                else
                    System.out.println("\nUSAGE.  Please type one of these shapes or 'exit' to quit:" +
                            "\n\tRectangle" +
                            "\n\tSquire" +
                            "\n\tRhombus" +
                            "\n\tTriangle" +
                            "\n\tCircle");
            } //end of while loop
        }
        catch (IOException exc) {
            System.out.println("Error reading console");
        }
        return userInput;
    }

    /* method getShapeDims obtains the chose of the shape from getShape().  Depending of the particular share
    * it asks the user to enter corresponding dimension.
    * The method returns an array of doubles with the corresponding dimensions*/
    public double[] getShapeDims (String userInput) {

        /*declare an array which will hold the dimensions of the shape.
        * The size of the array is 4 which is the largest quantity of dimensions
        * our chosen shapes can feature.
        * A circle has 1 dimension, which is its radius
        * A triangle has 4 dimensions, which is the lengths of its three sides plus the altitude*/

        double[] dimentions = new double[4];
        String numInput = "";

        // shape is RECTANGLE
        // prompting user for lengths of the sides of the rectangle
        if (userInput.toLowerCase().equals("rectangle")) {

            System.out.println("Enter the length of side 1 of your rectangle:");

            try { numInput = consoleReader().readLine(); }
            catch (IOException exc) {
                System.out.println("I/O Error");
            }
            catch (NumberFormatException exc) {
                System.out.println("Invalid format");
            }
            dimentions[0] = Double.parseDouble(numInput);  // the length of side 1 will be stored in the array[0]

            System.out.println("Enter the length of side 2 of your rectangle:");
            // the length of side 2 of the rectangle
            try { numInput = consoleReader().readLine(); }
            catch (IOException exc) {
                System.out.println("I/O Error");
            }
            catch (NumberFormatException exc) {
                System.out.println("Invalid format");
            }
            dimentions[1] = Double.parseDouble(numInput);
        }

        // shape is SQUIRE
        // prompting the user for the length of the side of the squire
        if (userInput.toLowerCase().equals("squire")) {

            System.out.println("Enter the length of the side of your squire:");

            try {
                numInput = consoleReader().readLine();
            } catch (IOException exc) {
                System.out.println("I/O Error");
            } catch (NumberFormatException exc) {
                System.out.println("Invalid format");
            }
            dimentions[0] = Double.parseDouble(numInput);
        }

        // shape is TRIANGLE
        // prompting the user for the length of the side of the triangle
        if (userInput.toLowerCase().equals("triangle")) {

            System.out.println("Enter the length of side 1 of your triangle:");

            try {
                numInput = consoleReader().readLine();
            } catch (IOException exc) {
                System.out.println("I/O Error");
            } catch (NumberFormatException exc) {
                System.out.println("Invalid format");
            }
            dimentions[0] = Double.parseDouble(numInput);

            System.out.println("Enter the length of side 2 of your triangle:");

            try {
                numInput = consoleReader().readLine();
            } catch (IOException exc) {
                System.out.println("I/O Error");
            } catch (NumberFormatException exc) {
                System.out.println("Invalid format");
            }
            dimentions[1] = Double.parseDouble(numInput);

            System.out.println("Enter the length of the basis of your triangle:");

            try {
                numInput = consoleReader().readLine();
            } catch (IOException exc) {
                System.out.println("I/O Error");
            } catch (NumberFormatException exc) {
                System.out.println("Invalid format");
            }
            dimentions[2] = Double.parseDouble(numInput);

            System.out.println("Enter the length of the altitude of your triangle:");

            try {
                numInput = consoleReader().readLine();
            } catch (IOException exc) {
                System.out.println("I/O Error");
            } catch (NumberFormatException exc) {
                System.out.println("Invalid format");
            }
            dimentions[3] = Double.parseDouble(numInput);
        }

        // shape is CIRCLE
        // prompting the user for the radius of the circle
        if (userInput.toLowerCase().equals("circle")) {

            System.out.println("Enter the length of the radius of your circle:");

            try {
                numInput = consoleReader().readLine();
            } catch (IOException exc) {
                System.out.println("I/O Error");
            } catch (NumberFormatException exc) {
                System.out.println("Invalid format");
            }
            dimentions[0] = Double.parseDouble(numInput);
        }

        // shape is RHOMBUS
        // prompting user for lengths of the sides of the rhombus
        if (userInput.toLowerCase().equals("rhombus")) {

            System.out.println("Enter the length of the side of your rhombus:");
            // the length of side 1 of the rectangle
            try { numInput = consoleReader().readLine(); }
            catch (IOException exc) {
                System.out.println("I/O Error");
            }
            catch (NumberFormatException exc) {
                System.out.println("Invalid format");
            }
            dimentions[0] = Double.parseDouble(numInput);

            System.out.println("Enter the length of the altitude of your rhombus:");

            try { numInput = consoleReader().readLine(); }
            catch (IOException exc) {
                System.out.println("I/O Error");
            }
            catch (NumberFormatException exc) {
                System.out.println("Invalid format");
            }
            dimentions[1] = Double.parseDouble(numInput);
        }
        return dimentions;
    }
}

class Principal {
    public static void main(String[] args) {
        GetUserInput gui = new GetUserInput();
        String userInput = gui.getShape();
        double[] dimentions = gui.getShapeDims(userInput);

        switch(userInput) {
            case("rectangle"):
                Shape rectangle = new Rectangle(dimentions[0], dimentions[1]);
                rectangle.printShape(userInput, rectangle.calcArea(), rectangle.calcPerimetro());
                break;
            case("squire"):
                Shape squire = new Squire(dimentions[0]);
                squire.printShape(userInput, squire.calcArea(), squire.calcPerimetro());
                break;
            case("triangle"):
                Shape triangle = new Triangle(dimentions[0], dimentions[1], dimentions[3], dimentions[2]);
                triangle.printShape(userInput, triangle.calcArea(), triangle.calcPerimetro());
                break;
            case("circle"):
                Shape circle = new Circle(dimentions[0]);
                circle.printShape(userInput, circle.calcArea(), circle.calcPerimetro());
                break;
            case("rhombus"):
                Shape rhombus = new Rhombus(dimentions[0], dimentions[1]);
                rhombus.printShape(userInput, rhombus.calcArea(), rhombus.calcPerimetro());
                break;
            case("exit"):
                System.out.println("Muy bien.  Hasta la vista.");
                break;
        }
    }
}
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package employeehierarchy;

import java.util.ArrayList;
import java.util.Scanner;

// -------- Base Class --------
class Employee {
    protected String name;
    protected double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public void displaySalary() {
        System.out.println("Employee Name : " + name);
        System.out.println("Current Salary: " + salary);
    }
}

// -------- Derived Class 1 --------
class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name, double salary) {
        super(name, salary);
    }

    public void calculateSalary() {
        salary = salary + (salary * 0.50);
    }
}

// -------- Derived Class 2 --------
class InternEmployee extends Employee {
    InternEmployee(String name, double salary) {
        super(name, salary);
    }

    public void calculateSalary() {
        salary = salary + (salary * 0.25);
    }
}

public class EmployeeDemo {
    static Scanner sc;
    static ArrayList<Employee> employees;

    static void addEmployee() {
        System.out.println("\n1. Full Time Employee");
        System.out.println("2. Intern Employee");
        System.out.print("Enter type: ");
        int type = sc.nextInt();
        sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Salary: ");
        double salary = sc.nextDouble();
        if (type == 1) {
            FullTimeEmployee fte = new FullTimeEmployee(name, salary);
            fte.calculateSalary();
            employees.add(fte);
        } else {
            if (type != 2) {
                System.out.println("Invalid type");
                return;
            }

            InternEmployee intern = new InternEmployee(name, salary);
            intern.calculateSalary();
            employees.add(intern);
        }

        System.out.println("Employee added successfully.\n");
    }

    static void displayAll() {
        if (employees.isEmpty()) {
            System.out.println("No records available.");
        } else {
            System.out.println("\n--- All Employees ---");

            for(Employee e : employees) {
                e.displaySalary();
                System.out.println();
            }

        }
    }

    static void displayFullTime() {
        System.out.println("\n--- Full Time Employees ---");

        for(Employee e : employees) {
            if (e instanceof FullTimeEmployee) {
                e.displaySalary();
                System.out.println();
            }
        }

    }

    static void displayInterns() {
        System.out.println("\n--- Intern Employees ---");

        for(Employee e : employees) {
            if (e instanceof InternEmployee) {
                e.displaySalary();
                System.out.println();
            }
        }

    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n===== Employee Menu =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Display Full Time Employees");
            System.out.println("4. Display Intern Employees");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    displayAll();
                    break;
                case 3:
                    displayFullTime();
                    break;
                case 4:
                    displayInterns();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while(choice != 5);

        sc.close();
    }

    static {
        sc = new Scanner(System.in);
        employees = new ArrayList<>();
    }
}

package studentresult;

import java.util.*;

class Student {
    private String name;
    private int rollNo;
    private double[] marks;

    public Student(String name, int rollNo, double[] marks) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    public String getName() { return name; }
    public int getRollNo() { return rollNo; }
    public double[] getMarks() { return marks; }
}

// Handles percentage and grading
class ResultProcessor {

    public double getPercentage(Student s) {
        double total = 0;
        for(double m : s.getMarks()) total += m;
        return total / s.getMarks().length;
    }

    public char getGrade(double per) {
        if(per >= 75) return 'A';
        if(per >= 60) return 'B';
        return 'C';
    }

    public void printResult(Student s) {
        double per = getPercentage(s);
        char grade = getGrade(per);

        System.out.println("\n--- Student Report ---");
        System.out.println("Name : " + s.getName());
        System.out.println("Roll : " + s.getRollNo());
        System.out.printf("Percentage : %.2f%%\n", per);
        System.out.println("Grade : " + grade);
    }
}

public class StudentApp {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> list = new ArrayList<>();
    static ResultProcessor rp = new ResultProcessor();

    // take input and store student
    static void addStudent() {
        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Roll No: ");
        int roll = sc.nextInt();

        double[] m = new double[5];
        System.out.println("Enter 5 subject marks");
        for(int i=0;i<5;i++) {
            System.out.print("Subject " + (i+1) + ": ");
            m[i] = sc.nextDouble();
        }
        sc.nextLine(); // consume leftover newline
        list.add(new Student(name, roll, m));
        System.out.println("Saved.\n");
    }

    static void showAll() {
        if(list.isEmpty()) {
            System.out.println("No records yet");
            return;
        }
        for(Student s : list) rp.printResult(s);
    }

    static void deleteStudent() {
        System.out.print("Enter roll number to delete: ");
        int r = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        Iterator<Student> it = list.iterator();
        boolean removed = false;

        while(it.hasNext()) {
            Student s = it.next();
            if(s.getRollNo() == r) {
                it.remove();
                removed = true;
                break;
            }
        }

        if(removed) System.out.println("Deleted successfully\n");
        else System.out.println("Roll number not found\n");
    }

    public static void main(String[] args) {

        System.out.println("Student Record System");
        System.out.println("(You can add as many students as you like)\n");

        int ch;
        do {
            System.out.println("1.Show all");
            System.out.println("2.Add new");
            System.out.println("3.Delete");
            System.out.println("4.Exit");
            System.out.print("Choice: ");
            ch = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch(ch) {
                case 1: showAll(); break;
                case 2: addStudent(); break;
                case 3: deleteStudent(); break;
                case 4: System.out.println("Program closed"); break;
                default: System.out.println("Invalid option");
            }
        } while(ch != 4);

        sc.close();
    }
}

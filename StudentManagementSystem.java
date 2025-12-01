// ===========================================
// PACKAGE: model
// ===========================================
package model;

import java.util.*;

// ----------- ABSTRACT PERSON CLASS -----------
abstract class Person {
    String name;
    String email;

    Person() {}

    Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // abstract method → will be overridden
    public abstract void displayInfo();
}


// ----------- FINAL CLASS FOR DEMO -----------
final class Utility {
    public final void showFinalMessage() {
        System.out.println("This is a final method in a final class.");
    }
}


// ----------- STUDENT CLASS -----------
public class Student extends Person {

    int rollNo;
    String course;
    double marks;
    String researchArea; // for overloaded method demo
    char grade;

    // Constructor Overloading
    public Student(int rollNo, String name, String email, String course, double marks) {
        super(name, email);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    // Overloaded constructor (used for research students)
    public Student(int rollNo, String name, String email, String course, String researchArea) {
        super(name, email);
        this.rollNo = rollNo;
        this.course = course;
        this.researchArea = researchArea;
        this.marks = 0;
        this.grade = 'N'; // Not applicable
    }

    // Grade calculator
    public void calculateGrade() {
        if (marks >= 90) grade = 'A';
        else if (marks >= 75) grade = 'B';
        else if (marks >= 60) grade = 'C';
        else grade = 'D';
    }

    // Overridden method
    @Override
    public void displayInfo() {
        System.out.println("\nStudent Info:");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name   : " + name);
        System.out.println("Email  : " + email);
        System.out.println("Course : " + course);

        if (researchArea != null)
            System.out.println("Research Area: " + researchArea);

        if (marks > 0)
            System.out.println("Marks  : " + marks + " | Grade: " + grade);
    }

    // Overloaded display method
    public void displayInfo(boolean shortDisplay) {
        if (shortDisplay) {
            System.out.println("\n[Note] Overloaded display method:");
            System.out.println("Student Info:");
            System.out.println("Roll No: " + rollNo);
            System.out.println("Name   : " + name);
            System.out.println("Email  : " + email);
            System.out.println("Course : " + course);
        }
    }

    // finalize() demo
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Finalize method called before object is garbage collected.");
    }
}


// ===========================================
// PACKAGE: service
// ===========================================
package service;

import model.*;
import java.util.*;


// ----------- INTERFACE -----------
interface RecordActions {
    void addStudent();
    void deleteStudent();
    void updateStudent();
    void searchStudent();
    void viewAllStudents();
}


// ----------- STUDENT MANAGER -----------
public class StudentManager implements RecordActions {

    Map<Integer, Student> records = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    // ADD STUDENT
    @Override
    public void addStudent() {
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        sc.nextLine();

        if (records.containsKey(roll)) {
            System.out.println("Roll number already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        System.out.print("Is this a research student? (yes/no): ");
        String ans = sc.nextLine();

        Student s;

        if (ans.equalsIgnoreCase("yes")) {
            System.out.print("Enter Research Area: ");
            String area = sc.nextLine();
            s = new Student(roll, name, email, course, area);
        } else {
            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();
            s = new Student(roll, name, email, course, marks);
        }

        records.put(roll, s);
        System.out.println("Student added successfully!");
    }

    // DELETE STUDENT
    @Override
    public void deleteStudent() {
        System.out.print("Enter Roll No to delete: ");
        int roll = sc.nextInt();

        if (records.remove(roll) != null)
            System.out.println("Student removed!");
        else
            System.out.println("Student not found!");
    }

    // UPDATE STUDENT
    @Override
    public void updateStudent() {
        System.out.print("Enter Roll No to update: ");
        int roll = sc.nextInt();
        sc.nextLine();

        if (!records.containsKey(roll)) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter New Course: ");
        String newCourse = sc.nextLine();
        records.get(roll).course = newCourse;

        System.out.println("Record updated!");
    }

    // SEARCH STUDENT
    @Override
    public void searchStudent() {
        System.out.print("Enter Roll No to search: ");
        int roll = sc.nextInt();

        Student s = records.get(roll);

        if (s != null) {
            s.displayInfo();
            s.displayInfo(true);  // method overloading demo

            Utility u = new Utility();
            u.showFinalMessage();
        }
        else System.out.println("Student not found!");
    }

    // VIEW ALL
    @Override
    public void viewAllStudents() {
        if (records.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (Student s : records.values())
            s.displayInfo();
    }
}


// ===========================================
//             MAIN SYSTEM
// ===========================================
package main;

import service.StudentManager;
import java.util.*;

public class StudentManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        int choice = 0;

        while (choice != 6) {
            System.out.println("\n===== Student Management Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student");
            System.out.println("5. View All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> manager.addStudent();
                case 2 -> manager.deleteStudent();
                case 3 -> manager.updateStudent();
                case 4 -> manager.searchStudent();
                case 5 -> manager.viewAllStudents();
                case 6 -> System.out.println("Exiting program.");
                default -> System.out.println("Invalid choice!");
            }
        }

        System.gc(); // to trigger finalize()
        sc.close();
    }
}

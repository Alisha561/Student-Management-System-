Java Lab Assignment 2 – Inheritance, Interfaces, Abstract Classes & Polymorphism
.. Overview

This project is a Student Management System designed following advanced Object-Oriented Programming principles.
It demonstrates:

Inheritance

Interfaces

Abstract Classes

Polymorphism (Overriding & Overloading)

Use of Packages

HashMap-based student storage

The system allows adding, deleting, updating, searching, and viewing student records through a menu-driven interface.

* Features
# Abstract Class – Person

Fields: name, email

Abstract method: displayInfo()

# Student Class (extends Person)

Additional fields: rollNo, course, marks, grade, researchArea

Overriding displayInfo()

Method overloading: extra displayInfo(boolean shortDisplay)

Constructor overloading for:

Regular students (with marks)

Research students (with research area)

# Interface – RecordActions

Defines CRUD methods:

addStudent()

deleteStudent()

updateStudent()

searchStudent()

viewAllStudents()

#StudentManager Class

Implements RecordActions
Uses:

HashMap<Integer, Student> for efficient student storage

Method overriding

Input handling

Prevents duplicate roll numbers

# Polymorphism Demonstrated

Dynamic Binding: displayInfo() override

Static Binding: overloaded displayInfo(boolean)

# Final Class & Final Method

Used to demonstrate constraints on inheritance.

# finalize() Method

Included to mirror expected output from PDF.

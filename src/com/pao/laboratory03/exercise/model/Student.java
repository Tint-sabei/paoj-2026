package com.pao.laboratory03.exercise.model;
import com.pao.laboratory03.exercise.exception.InvalidGradeException;
import com.pao.laboratory03.exercise.exception.InvalidStudentException;
import com.pao.laboratory04.exercise.exception.*;
import java.util.*;

//Private fields: String name, int age, Map<Subject, Double> grades
// *    - Constructor: Student(String name, int age)
// *      → initializes grades as empty HashMap
// *      → validation: if age < 18 or age > 60, throw InvalidStudentException
// *    - Methods: getName(), getAge(), getGrades()
// *    - addGrade(Subject subject, double grade)
// *      → if grade < 1 or grade > 10, throw InvalidGradeException
// *      → puts the grade in the map (overwrites if subject already exists)
// *    - double getAverage()
// *      → calculates the arithmetic mean of grades (returns 0 if no grades)
// *    - toString() → "Student{name='Ana', age=20, avg=8.50}"


public class Student implements Comparable<Student> {
    private String name;
    private int age;
    private Map<Subject, Double> grades;

    public Student(String name, int age){
        if (age < 18 || age > 60) {
            throw new InvalidStudentException("Age " + age + " is invalid (must be 18-60).");
        }

        this.name = name;
        this.age = age;
        this.grades = new HashMap<>();
    }

    public String getName(){return name;}
    public int getAge(){return age;}
    public Map<Subject, Double> getGrades(){return grades;}

    public void addGrade(Subject subject, double grade){
        if (grade < 1 || grade > 10) {
            throw new InvalidGradeException("Grade must be between 1 and 10.");
        }
        grades.put(subject, grade);
    }

    public double getAverage(){
        if (grades.isEmpty()) return 0;

        double sum = 0;
        for (double g : grades.values()){
            sum += g;
        }
        return sum / grades.size();
    }

    @Override
    public String toString(){
        return "(Student{name='" + name + "', age="  + age + ", avg=" + getAverage() + "}";
    }

    @Override
    public int compareTo(Student other){
        return Double.compare(other.getAverage(), this.getAverage());
    }
}



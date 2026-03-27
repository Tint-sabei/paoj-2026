package com.pao.laboratory03.exercise.service;
import com.pao.laboratory03.exercise.exception.StudentNotFoundException;
import com.pao.laboratory03.exercise.model.Student;
import com.pao.laboratory03.exercise.model.Subject;
import com.pao.laboratory04.exercise.model.*;
import com.pao.laboratory04.exercise.exception.*;
import java.util.*;

public class StudentService {
    private static StudentService instance;
    private List<Student> students = new ArrayList<>();

    private StudentService() {  }
    public static StudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public void addStudent(String name, int age){

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                throw new RuntimeException("A student with the same name already exists");
            }
        }
        // check again
        students.add(new Student(name, age));
    }

    public Student findByName(String name) {
        for (Student s : students){
            if (s.getName().equalsIgnoreCase(name)) {return s;}
        }
        throw new StudentNotFoundException("Student is not found.");
    }

    public void addGrade(String studentName, Subject subject, double grade){
        Student student = findByName(studentName);
        student.addGrade(subject, grade);
    }

    public void printAllStudents(){

        int i = 1;
        for (Student s : students){
            System.out.println(i + ". " + s);
            for (Map.Entry<Subject, Double> entry : s.getGrades().entrySet()) {
                System.out.println(entry.getKey().name() + " = " + entry.getValue());
            }
        }
        i++;
    }

    public void printTopStudents() {
        List<Student> sortedList = new ArrayList<>(students);
        Collections.sort(sortedList);

        int i = 1;
        for (Student s : sortedList){
            System.out.println(i + ". " + s.getName() + " — average: " + s.getAverage());
        }
        i++;
    }


    public Map<Subject, Double> getAveragePerSubject() {
        Map<Subject, Double> averages = new HashMap<>();
        for (Subject subject : Subject.values()) {
            double sum = 0;
            int count = 0;
            for (Student s : students) {
                if (s.getGrades().containsKey(subject)) {
                    sum += s.getGrades().get(subject);
                    count++;
                }
            }
            if (count > 0) {
                averages.put(subject, sum / count);
            }

        }
        return averages;

    }

}


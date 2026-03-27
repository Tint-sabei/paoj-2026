package com.pao.laboratory05.angajati;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("The requirements are in Readme.md — Exercise 3 section.");

        AngajatService service = AngajatService.getInstance();
        Scanner myObj = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Employee Management =====");
            System.out.println("1. Add employee");
            System.out.println("2. List by salary");
            System.out.println("3. Search by department");
            System.out.println("0. Exit");

            int option = Integer.parseInt(myObj.nextLine());

            switch (option) {
                case 1:
                    System.out.print("Name: ");
                    String empName = myObj.nextLine();

                    System.out.print("Department (name): ");
                    String deptName = myObj.nextLine();

                    System.out.print("Department (location): ");
                    String deptLoc = myObj.nextLine();

                    System.out.print("Salary: ");
                    double salary = Double.parseDouble(myObj.nextLine());

                    Department dept = new Department(deptName, deptLoc);
                    Angajat newEmp = new Angajat(empName, dept, salary);
                    service.addAngajat(newEmp);
                    break;
                case 2:
                    System.out.println("--- Employees by salary (descending) ---");
                    service.listBySalary();
                    break;
                case 3:
                    System.out.print("Department: ");
                    String findDept = myObj.nextLine();
                    System.out.println("--- Employees from " + findDept + " ---");
                    service.findByDepartament(findDept);
                    break;
                case 0:
                    System.out.println("Goodbye");
                    myObj.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }

        }
    }
}

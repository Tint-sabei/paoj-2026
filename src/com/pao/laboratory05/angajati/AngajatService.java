package com.pao.laboratory05.angajati;
import java.util.Arrays;

public class AngajatService {

    private Angajat[] angajati = new Angajat[0];

    // private constructor
    private AngajatService() {}

    // getInstance()
    private static class Holder {
        private static final AngajatService instance = new AngajatService();
    }

    public static AngajatService getInstance() {
        return AngajatService.Holder.instance;
    }

    public void addAngajat(Angajat a) {
        Angajat[]  newAngajati= new Angajat [angajati.length + 1];
        System.arraycopy(this.angajati, 0, newAngajati, 0, angajati.length);
        newAngajati[angajati.length] = a;
        this.angajati = newAngajati;
        System.out.println("Employee added: " + a.getName());
    }
    public void printAll(){
        // display all employees (array order, unsorted)
        for (Angajat a : angajati) {
            System.out.println(a);
        }
    }

    public void listBySalary() {
        // clone, `Arrays.sort(copy)`, display (descending, natural)
        Angajat[] copy = angajati.clone();
        Arrays.sort(copy);
        for (Angajat a : copy){
            System.out.println(a);
        }
    }

    public void findByDepartament(String deptName){
        // traverse the array, display all employees
        boolean found = false;
        for (Angajat a : angajati) {
            if (a.getDepartment().name().equalsIgnoreCase(deptName)) {System.out.println(a); found = true;}
        }

        if (!found) {System.out.println("No employee in Department:" + deptName);}
    }

}








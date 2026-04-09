package com.pao.laboratory06.exercise3;
import java.util.*;

public class EngineerSalaryComparator implements Comparator<Engineer> {

    @Override
    public int compare(Engineer e1, Engineer e2){

        return Double.compare(e2.salary, e1.salary);
    }


}



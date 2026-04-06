package com.pao.laboratory06.exercise2;
import java.util.Scanner;

public interface IOperationsReadWrite {
    void read(Scanner in);
    void display();
    String contractType();
    default boolean hasBonus(){return false;}
}




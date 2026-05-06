package com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex1.ser";

    public static void main(String[] args) throws Exception {

        // 1. Read N from stdin, then the N transactions (id amount date sourceAccount destinationAccount type)
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        int n = input.nextInt();

        List<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < n; i++){
            int id = input.nextInt();
            double amt = input.nextDouble();
            String date = input.next();
            String srcAcc = input.next();
            String desAcc = input.next();
            TransactionType type = TransactionType.valueOf(input.next());

            Transaction t = new Transaction(id, amt, date, srcAcc, desAcc, type);

            // 2. Set the field note = "processed" on each transaction before serialization
            t.setNote("processed");
            transactions.add(t);
        }

        // 3. Serialize the list of transactions into OUTPUT_FILE using ObjectOutputStream (try-with-resources)
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE))){
            oos.writeObject(transactions);
        } catch (IOException e){ System.out.println("Error: " + e.getMessage());}

        // 4. Deserialize the list from OUTPUT_FILE using ObjectInputStream (try-with-resources)
        List<Transaction> deserializedTransactions = new ArrayList<>();

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OUTPUT_FILE))){
            deserializedTransactions = (List<Transaction>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // 5. Process commands from stdin until EOF:
        while(input.hasNext()){
            String command = input.next();

            switch(command){
                case "LIST":
                    for (Transaction t : deserializedTransactions){ System.out.println(t); }
                    break;
                case "FILTER":
                    String dateFormat = input.next();
                    boolean found = false;

                    for (Transaction t : deserializedTransactions) {
                        if (t.getDate().startsWith(dateFormat)) {
                            System.out.println(t);
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("Niciun rezultat.");
                    }
                    break;

                case "NOTE":
                    int id = input.nextInt();

                    boolean foundNote = false;

                    for (Transaction t : deserializedTransactions) {
                        if (t.getId() == id) {
                            System.out.println("NOTE[" + id + "]: " + t.getNote());
                            foundNote = true;
                            break;
                        }
                    }
                    if (!foundNote) {
                        {System.out.println("NOTE[" + id + "]: not found" );}
                    }
                    break;

            }

        }
        input.close();

    }
}




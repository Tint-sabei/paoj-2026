package com.pao.laboratory10.exercise1;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        LinkedList<Transaction> transactions = new LinkedList<>();

        while (scanner.hasNext()) {
            String command = scanner.next();

            switch (command) {
                case "ENQUEUE": {
                    int id = scanner.nextInt();
                    double amount = scanner.nextDouble();
                    LocalDate date = LocalDate.parse(scanner.next());
                    TransactionType type = TransactionType.valueOf(scanner.next());

                    Transaction t = new Transaction(id, amount, date, type);
                    transactions.addLast(t);
                    break;
                }

                case "DEQUEUE": {
                    if (transactions.isEmpty()) {
                        System.out.println("Coada goala.");
                    } else {
                        Transaction r = transactions.removeFirst();
                        System.out.println("Procesat: " + r);
                    }
                    break;
                }

                case "PUSH": {
                    int id = scanner.nextInt();
                    double amount = scanner.nextDouble();
                    LocalDate date = LocalDate.parse(scanner.next());
                    TransactionType type = TransactionType.valueOf(scanner.next());

                    Transaction t = new Transaction(id, amount, date, type);
                    transactions.addFirst(t);
                    break;
                }

                case "POP": {
                    if (transactions.isEmpty()) {
                        System.out.println("Coada goala.");
                    } else {
                        Transaction r = transactions.removeFirst();
                        System.out.println("Extras: " + r);
                    }
                    break;
                }

                case "REMOVE_DEBIT": {
                    int countDebit = 0;
                    Iterator<Transaction> itrDebit = transactions.iterator();
                    while (itrDebit.hasNext()) {
                        if (itrDebit.next().getType() == TransactionType.DEBIT) {
                            itrDebit.remove();
                            countDebit++;
                        }
                    }
                    System.out.println("Eliminat " + countDebit + " tranzactii DEBIT.");
                    break;
                }

                case "REMOVE_BELOW": {
                    double threshold = scanner.nextDouble();
                    int countBelow = 0;
                    Iterator<Transaction> itrBelow = transactions.iterator();
                    while (itrBelow.hasNext()) {
                        if (itrBelow.next().getAmount() < threshold) {
                            itrBelow.remove();
                            countBelow++;
                        }
                    }
                    System.out.println("Eliminat " + countBelow + " tranzactii sub " + String.format(Locale.US, "%.2f", threshold) + " RON.");
                    break;
                }

                case "PRINT": {
                    for (Transaction trans : transactions) {
                        System.out.println(trans);
                    }
                    break;
                }

                case "SIZE": {
                    System.out.println("Dimensiune coada: " + transactions.size());
                    break;
                }
            }
        }
        scanner.close();
    }
}
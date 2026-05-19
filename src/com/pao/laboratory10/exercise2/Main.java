package com.pao.laboratory10.exercise2;

import com.pao.laboratory10.exercise1.Transaction;
import com.pao.laboratory10.exercise1.TransactionType;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // TODO: Implement according to Readme.md
        //
        // 1. Read N from stdin, then the N transactions (id amount date type) — there may be duplicate ids
        // Store them all in an ArrayList<Transaction> (with duplicates, insertion order)
        //
        // 2. Process commands from stdin until EOF:
        //
        // UNIQUE_IDS → LinkedHashSet<Integer> with ids in order of first appearance
        // display: "Unique IDs (N): [1, 2, 3, ...]"
        //
        // MONTHLY_REPORT → TreeMap<String, ...> grouped by yyyy-MM (substring 0-7 of date)
        // for each month, the CREDIT and DEBIT amounts
        // format: "yyyy-MM: CREDIT X.XX RON, DEBIT Y.YY RON"
        //
        // TOP n → first n transactions by decreasing amount (does not modify the list)
        // display "Top n:" followed by n lines
        //
        // SORT_ASC → Collections.sort with ascending amount; display sorted list
        // SORT_DESC → Collections.sort with descending amount; display sorted list
        // REVERSE → Collections.reverse; display list
        // MIN_MAX → Collections.min/max by amount
        // "MIN: [id] data type: sum RON"
        // "MAX: [id] data type: sum RON"
        //
        // CME_DEMO → try for(t : list) list.remove(t) in try-catch
        // display "ConcurrentModificationException caught: modification in iteration detected."
        //
        // Transaction line format: [id] data type: sum RON
        // Ex: [1] 2024-01-15 CREDIT: 1500.00 RON

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        int n = scanner.nextInt();
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 0; i < n; i++){
            int id = scanner.nextInt();
            double amount = scanner.nextDouble();
            LocalDate date = LocalDate.parse(scanner.next());
            TransactionType type = TransactionType.valueOf(scanner.next());

            Transaction t = new Transaction(id, amount, date, type);
            transactions.add(t);
        }

        while (scanner.hasNext()){

            String command = scanner.next();

            switch(command){
                case "UNIQUE_IDS":
                    LinkedHashSet<Integer> uniqueIds = new LinkedHashSet<>();
                    for (Transaction t : transactions) {
                        uniqueIds.add(t.getId());
                    }
                    System.out.println("IDs unice (" + uniqueIds.size() + "): " + uniqueIds);
                    break;

                case "MONTHLY_REPORT":
                    TreeMap<String, Double> creditReport = new TreeMap<>();
                    TreeMap<String, Double> debitReport = new TreeMap<>();

                    for (Transaction t : transactions) {
                        String month = t.getDate().toString().substring(0, 7);

                        if (t.getType() == TransactionType.CREDIT) {
                            double currentCredit = creditReport.getOrDefault(month, 0.0);
                            creditReport.put(month, currentCredit + t.getAmount());
                        } else if (t.getType() == TransactionType.DEBIT) {
                            double currentDebit = debitReport.getOrDefault(month, 0.0);
                            debitReport.put(month, currentDebit + t.getAmount());
                        }
                    }

                    TreeSet<String> allMonths = new TreeSet<>();
                    allMonths.addAll(creditReport.keySet());
                    allMonths.addAll(debitReport.keySet());

                    for (String month : allMonths) {
                        double totalCredit = creditReport.getOrDefault(month, 0.0);
                        double totalDebit = debitReport.getOrDefault(month, 0.0);

                        System.out.printf(Locale.US, "%s: CREDIT %.2f RON, DEBIT %.2f RON\n",
                                month, totalCredit, totalDebit);
                    }
                    break;

                case "TOP":
                    int nTop = scanner.nextInt();

                    List<Transaction> copyTransactions = new ArrayList<>(transactions);
                    copyTransactions.sort(Comparator.comparingDouble(Transaction::getAmount).reversed());

                    System.out.println("Top n:" + nTop + ":");

                    int count = 0;
                    for (Transaction t : copyTransactions){
                        if (count == nTop) {
                            break;
                        }
                        System.out.println(t);
                        count++;
                    }
                    break;

                case "SORT_ASC":
                    transactions.sort(Comparator.comparingDouble(Transaction::getAmount));
                    for (Transaction t : transactions) {
                        System.out.println(t);
                    }
                    break;

                case "SORT_DESC":
                    transactions.sort(Comparator.comparingDouble(Transaction::getAmount).reversed());
                    for (Transaction t : transactions){
                        System.out.println(t);
                    }
                    break;

                case "REVERSE":
                    Collections.reverse(transactions);
                    for (Transaction t : transactions){
                        System.out.println(t);
                    }
                    break;

                case "MIN_MAX":
                    Transaction minT = Collections.min(transactions, Comparator.comparingDouble(Transaction::getAmount));
                    Transaction maxT = Collections.max(transactions, Comparator.comparingDouble(Transaction::getAmount));
                    System.out.println("MIN: " + minT);
                    System.out.println("MAX: " + maxT);
                    break;

                case "CME_DEMO":
                    try {
                        for (Transaction t : transactions) {
                            transactions.remove(t);
                        }
                    } catch(ConcurrentModificationException e){
                        System.out.println("ConcurrentModificationException prins: modificare in iteratie detectata.");
                    }
                    break;

            }
        }
        scanner.close();

    }
}

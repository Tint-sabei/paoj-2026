package com.pao.laboratory10.exercise3;
import com.pao.laboratory10.exercise1.Transaction;
import com.pao.laboratory10.exercise1.TransactionType;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Transaction2> transactions = new ArrayList<>();

        transactions.add(new Transaction2(1, 10.00, LocalDate.parse("2026-05-03"), TransactionType.CREDIT, "ACC_A"));
        transactions.add(new Transaction2(2, 20.00, LocalDate.parse("2026-05-05"), TransactionType.DEBIT, "ACC_B"));
        transactions.add(new Transaction2(3, 15.50, LocalDate.parse("2026-05-10"), TransactionType.CREDIT, "ACC_A"));
        transactions.add(new Transaction2(4, 45.00, LocalDate.parse("2026-06-03"), TransactionType.DEBIT, "ACC_C"));
        transactions.add(new Transaction2(5, 100.00, LocalDate.parse("2026-06-05"), TransactionType.CREDIT, "ACC_B"));
        transactions.add(new Transaction2(6, 20.00, LocalDate.parse("2026-06-10"), TransactionType.DEBIT, "ACC_A"));
        transactions.add(new Transaction2(7, 30.00, LocalDate.parse("2026-06-15"), TransactionType.CREDIT, "ACC_C"));
        transactions.add(new Transaction2(8, 80.00, LocalDate.parse("2026-07-03"), TransactionType.DEBIT, "ACC_D"));
        transactions.add(new Transaction2(9, 10.00, LocalDate.parse("2026-07-05"), TransactionType.CREDIT, "ACC_A"));
        transactions.add(new Transaction2(10, 25.00, LocalDate.parse("2026-07-10"), TransactionType.DEBIT, "ACC_B"));

        // 1. `filter(type == CREDIT)` | List of all CREDIT transactions |
        List<Transaction> creditT = transactions.stream().filter(t -> t.getType() == TransactionType.CREDIT).collect(Collectors.toList());
        creditT.forEach(System.out::println);
        System.out.println();

        // 2. `ToDouble(amount).sum()` | `Total processed: X.XX RON` |
        double totalProcessed = transactions.stream().mapToDouble(Transaction::getAmount).sum();
        System.out.printf(Locale.US, "Total processed: %.2f RON\n", totalProcessed);
        System.out.println();

        // 3. `Collectors.groupingBy(month, summingDouble(amount))` | Per month: `yyyy-MM: X.XX RON` |
        Map<String, Double> sumPerMonth = transactions.stream().collect(Collectors.groupingBy(t -> t.getDate().toString().substring(0, 7), TreeMap::new, Collectors.summingDouble(Transaction::getAmount)));
        sumPerMonth.forEach((month, sum) -> System.out.printf(Locale.US, "Per Month: Per month: %s %.2f RON\n", month, sum));
        System.out.println();

        // 4. `sorted(comparingDouble.reversed()).limit(3)` | `Top 3 transactions:` + 3 lines |
        transactions.stream().sorted(Comparator.comparingDouble(Transaction::getAmount).reversed()).limit(3).forEach(System.out::println);
        System.out.println();

        // 5. `map(sourceAccount).distinct().collect(toList())` | `Unique source accounts: [ACC_A, ACC_B, ...]` |
        List<String> uniqueAccs = transactions.stream().map(Transaction2::getSourceAccount).distinct().collect(Collectors.toList());
        System.out.println("Unique source accounts:" + uniqueAccs);
        System.out.println();

        // 6. `mapToDouble(amount).average()` | `Average amount: X.XX RON` |
        double avg = transactions.stream().mapToDouble(Transaction::getAmount).average().orElse(0.0);
        System.out.printf(Locale.US, "Average amount: %.2f RON\n", avg);
        System.out.println();

        // 7. `Collectors.groupingBy(month)` with statement format | `ACCOUNT STATEMENT - yyyy-MM: N transactions, total: X.XX RON` per month |
        Map<String, List<Transaction2>> groupByMonth = transactions.stream().collect(Collectors.groupingBy(t -> t.getDate().toString().substring(0, 7), TreeMap::new, Collectors.toList()));
        groupByMonth.forEach((month, list) -> {
            long count = list.size();
            double total = list.stream().mapToDouble(Transaction::getAmount).sum();
            System.out.printf(Locale.US, "ACCOUNT STATEMENT - %s: %d transactions, total: %.2f RON\n", month, count, total);
        });
    }

}

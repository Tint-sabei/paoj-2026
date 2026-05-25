package com.pao.laboratory11.exercise3;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Transaction> data = List.of(
                new Transaction(1, new BigDecimal("100.00"), LocalDate.parse("2024-10-20"), "Romania", "Website"),
                new Transaction(1, new BigDecimal("150.00"), LocalDate.parse("2024-10-21"), "Romania", "Website"),
                new Transaction(2, new BigDecimal("200.00"), LocalDate.parse("2024-10-22"), "Turkey", "Mobile"),
                new Transaction(3, new BigDecimal("300.00"), LocalDate.parse("2024-10-23"), "Italy", "Mobile"),
                new Transaction(4, new BigDecimal("400.00"), LocalDate.parse("2024-10-24"), "France", "Website"),
                new Transaction(5, new BigDecimal("500.00"), LocalDate.parse("2024-10-25"), "Germany", "Website")
        );

        Snapshot snap = data.stream().collect(CustomCollectors.toSnapshot(3));

        System.out.println("Query 1: top transactions (from the snapshot)");
        snap.getTopTransactions().forEach(System.out::println);

        System.out.println("\nQuery 2: total by country (descending)");
        snap.getCountByCountry().entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(System.out::println);

        System.out.println("\nQuery 3: channels sorted by count");
        snap.getCountByChannel().entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(System.out::println);
    }
}
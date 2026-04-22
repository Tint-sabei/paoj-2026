package com.pao.laboratory07.exercise3;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        java.util.Locale.setDefault(java.util.Locale.US);
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        int n = Integer.parseInt(sc.nextLine().trim());
        List<Comanda> comenzi = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            String[] tokens = line.split(" ");
            if (tokens[0].equals("STANDARD")) {
                String nume = tokens[1];
                double pret = Double.parseDouble(tokens[2]);
                String client = tokens[3];
                comenzi.add(new ComandaStandard(nume, pret, client));
            } else if (tokens[0].equals("DISCOUNTED")) {
                String nume = tokens[1];
                double pret = Double.parseDouble(tokens[2]);
                int discount = Integer.parseInt(tokens[3]);
                String client = tokens[4];
                comenzi.add(new ComandaRedusa(nume, pret, discount, client));
            } else if (tokens[0].equals("GIFT")) {
                String nume = tokens[1];
                String client = tokens[2];
                comenzi.add(new ComandaGratuita(nume, client));
            }
        }

        System.out.println();

        for (Comanda c : comenzi) {
            System.out.println(c.description());
        }

        while (sc.hasNextLine()) {
            String commandLine = sc.nextLine().trim();
            if (commandLine.isEmpty()) continue;

            String[] tokens = commandLine.split(" ");
            String cmd = tokens[0];

            if (cmd.equals("QUIT")) {
                System.out.println();
                return;
            }

            switch (cmd) {
                case "STATS":
                    System.out.println("--- STATS ---");
                    Map<String, Double> averages = comenzi.stream()
                            .collect(Collectors.groupingBy(c -> {
                                if (c instanceof ComandaStandard) return "STANDARD";
                                if (c instanceof ComandaRedusa) return "DISCOUNTED";
                                return "GIFT";
                            }, Collectors.averagingDouble(Comanda::pretFinal)));

                    String[] types = {"STANDARD", "DISCOUNTED", "GIFT"};
                    for (String type : types) {
                        if (averages.containsKey(type)) {
                            System.out.printf("%s: average = %.2f lei\n", type, averages.get(type));
                        }
                    }
                    break;

                case "FILTER":
                    double threshold = Double.parseDouble(tokens[1]);
                    System.out.printf("--- FILTER (>= %.2f) ---\n", threshold);

                    List<Comanda> filteredList = comenzi.stream().filter(c -> c.pretFinal() >= threshold).toList();
                    for (Comanda c : filteredList) {
                        System.out.println(c.shortDescription());
                    }
                    break;

                case "SORT":
                    System.out.println("--- SORT (by client, then by price) ---");

                    List<Comanda> sortedList = comenzi.stream().sorted(Comparator.comparing(Comanda::getClient).thenComparing(Comanda::pretFinal)).toList();
                    for (Comanda c : sortedList) {
                        System.out.println(c.shortDescription());
                    }
                    break;

                case "SPECIAL":
                    System.out.println("--- SPECIAL (discount > 15%) ---");

                    List<Comanda> specialList = comenzi.stream().filter(c -> c instanceof ComandaRedusa r && r.getDiscountPercent() > 15).toList();
                    for (Comanda c : specialList) {
                        System.out.println(c.description());
                    }
                    break;
            }
        }
    }
}
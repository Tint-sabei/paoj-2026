package com.pao.laboratory11.exercise3;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;

public class CustomCollectors {
    public static Collector<Transaction, ?, Snapshot> toSnapshot(int topN) {
        class Agg {
            Map<String, Long> countryCounts = new HashMap<>();
            Map<String, Long> channelCounts = new HashMap<>();
            BigDecimal totalAmount = BigDecimal.ZERO;
            List<Transaction> allTransactions = new ArrayList<>();
        }

        return Collector.of(
                Agg::new,
                (agg, tx) -> {
                    agg.countryCounts.merge(tx.getCountry(), 1L, Long::sum);
                    agg.channelCounts.merge(tx.getChannel(), 1L, Long::sum);
                    agg.totalAmount = agg.totalAmount.add(tx.getAmount());
                    agg.allTransactions.add(tx);
                },
                (a, b) -> {
                    b.countryCounts.forEach((k, v) -> a.countryCounts.merge(k, v, Long::sum));
                    b.channelCounts.forEach((k, v) -> a.channelCounts.merge(k, v, Long::sum));
                    a.totalAmount = a.totalAmount.add(b.totalAmount);
                    a.allTransactions.addAll(b.allTransactions);
                    return a;
                },
                agg -> {

                    List<Transaction> topList = agg.allTransactions.stream().sorted(Comparator.comparing(Transaction::getAmount).reversed()).limit(topN).toList();

                    return new Snapshot(agg.countryCounts, agg.channelCounts, agg.totalAmount, topList);
                }
        );
    }
}
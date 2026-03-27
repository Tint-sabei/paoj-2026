package com.pao.laboratory03.enums;

/*
 *
 * STEP 2 — In this Main.java:
 *   a) Iterate through all values using Priority.values() and display:
 *      "emoji name (level=X, color=Y)"
 *   b) Use a switch on a Priority and display a specific message.
 *   c) Convert a String to Priority using Priority.valueOf("HIGH") — display the result.
 *   d) Demonstrate comparison: use == between two enums (NOT .equals()).
 *   e) Display name() and ordinal() for each constant.
 *
 * Expected output:
 *
 * === All priorities ===
 * 🟢 LOW (level=1, color=green)
 * 🟡 MEDIUM (level=2, color=yellow)
 * 🟠 HIGH (level=3, color=orange)
 * 🔴 CRITICAL (level=4, color=red)
 *
 * === Switch on priority ===
 * ⚠️ Warning! High priority!
 *
 * === valueOf ===
 * Priority.valueOf("HIGH") = HIGH
 *
 * === Enum comparison ===
 * HIGH == HIGH? true
 * HIGH == LOW? false
 *
 * === name() and ordinal() ===
 * LOW: name=LOW, ordinal=0
 * MEDIUM: name=MEDIUM, ordinal=1
 * HIGH: name=HIGH, ordinal=2
 * CRITICAL: name=CRITICAL, ordinal=3
 */


public class Main {
    public static void main(String[] args) {

        // a
        System.out.println("=== All priorities ===");
        for (Priority p : Priority.values()) {
            System.out.println(p.getEmoji() + " " + p.name() + " (level=" + p.getLevel() + ", color=" + p.getColor() + ")");
        }

        // b
        System.out.println("\n=== Switch on priority ===");
        Priority current = Priority.HIGH;
        switch (current) {
            case LOW:
            case MEDIUM:
            case HIGH:
            case CRITICAL:
                System.out.println("⚠\uFE0F Warning! High priority!");

            // c
            System.out.println("\n=== valueOf ===");
            Priority pHigh = Priority.valueOf("HIGH");
            System.out.println("Priority.valueOf(\"HIGH\") = " + pHigh);

            // d
            System.out.println("\n=== Enum comparison ===");
            System.out.println("HIGH == HIGH? " + (pHigh == Priority.HIGH));
            System.out.println("HIGH == LOW? " + (pHigh == Priority.LOW));

            // e
            System.out.println("\n=== name() and ordinal() ===");

            for (Priority p1 : Priority.values()){
                System.out.println(p1.name() + ": name="  + p1.name() + ", ordinal=" + p1.ordinal());
            }
        }
    }
}


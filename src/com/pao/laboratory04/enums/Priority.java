package com.pao.laboratory04.enums;

import java.util.HashMap;

public enum Priority {
//    STEP 1 — Create the enum Priority.java (separate file in the same package):
//            *   - Constants: LOW, MEDIUM, HIGH, CRITICAL
// *   - Private fields: int level, String color
// *   - Private constructor: Priority(int level, String color)
// *   - Getters: getLevel(), getColor()
//            *   - Abstract method: String getEmoji() — each constant implements it differently
// *     LOW → "🟢", MEDIUM → "🟡", HIGH → "🟠", CRITICAL → "🔴"
//            *   - Suggested values:
//            *     LOW(1, "green"), MEDIUM(2, "yellow"), HIGH(3, "orange"), CRITICAL(4, "red")

    LOW(1, "green"){ @Override public String getEmoji(){return "🟢";}},
    MEDIUM(2, "yellow"){ @Override public String getEmoji(){return "🟡";}},
    HIGH(3, "orange"){ @Override public String getEmoji(){return "🟠";}},
    CRITICAL(4, "red"){ @Override public String getEmoji(){return "🔴";}};

    private final int level;
    private final String color;

    Priority(int level, String color){
        this.level = level;
        this.color = color;
    }

    public int getLevel(){return level;};
    public String getColor(){return color;};


    public abstract String getEmoji();

}


package com.pao.test;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Utility for automated testing of I/O exercises.
 *
 * Usage from an exercise's Test.java:
 *   IOTest.runParts("src/com/pao/laboratoryNN/exerciseM/tests", Main::main);
 *
 * Test directory convention:
 *   tests/partA/1.in  → input provided to System.in for Part A, test 1
 *   tests/partA/1.out → expected output (exact comparison, ignoring trailing spaces)
 *   tests/partB/1.in, tests/partB/1.out, ... for Part B
 *   ... and so on for partC, partD, etc.
 *
 * .in and .out files must have the same numeric prefix (e.g. 1.in / 1.out).
 * Subdirectories are processed in alphabetical order.
 */


public class IOTest {

    @FunctionalInterface
    public interface MainMethod {
        void run(String[] args) throws Exception;
    }

    /**
     * Runs tests from all partX/ subdirectories of the given directory.
     * Each subdirectory is a "part" of the exercise and receives its own header and summary.
     * At the end, a combined table with results from all parts is displayed.
     *
     * @param testsDir relative path to the project root (e.g. "src/com/pao/laboratory06/exercise1/tests")
     * @param main     reference to the exercise's Main::main being tested
     */
    public static void runParts(String testsDir, MainMethod main) {
        File dir = new File(testsDir);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("ERROR: test directory does not exist: " + dir.getAbsolutePath());
            return;
        }

        // We collect the subdirectories (partA, partB, partC, ...)
        File[] all = dir.listFiles();
        File[] partDirs = (all == null) ? new File[0]
                : Arrays.stream(all)
                        .filter(f -> f.isDirectory())
                        .toArray(File[]::new);
        Arrays.sort(partDirs, Comparator.comparing(File::getName));

        if (partDirs.length == 0) {
            System.out.println("ERROR: there are no subdirectories of type partX/ in " + testsDir);
            System.out.println("Expected structure: tests/partA/, tests/partB/, ...");
            return;
        }

        // Results per part for the final table
        List<String>  partNames   = new ArrayList<>();
        List<Integer> partPassed  = new ArrayList<>();
        List<Integer> partTotal   = new ArrayList<>();

        for (File partDir : partDirs) {
            String partName = partDir.getName();
            System.out.println();
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.printf( "║  Partea: %-52s║%n", partName);
            System.out.println("╚══════════════════════════════════════════════════════════════╝");

            int[] results = runPartDir(partDir, main);
            partNames.add(partName);
            partPassed.add(results[0]);
            partTotal.add(results[1]);
        }

        // Final summary table
        System.out.println();
        System.out.println("══════════════════════════════════════════════════════════════");
        System.out.println("  SUMAR FINAL");
        System.out.println("──────────────────────────────────────────────────────────────");
        int totalP = 0, totalT = 0;
        for (int i = 0; i < partNames.size(); i++) {
            int p = partPassed.get(i), t = partTotal.get(i);
            String status = (p == t && t > 0) ? "✓" : (p == 0 ? "✗" : "~");
            System.out.printf("  %s  %-10s  %d/%d teste%n", status, partNames.get(i), p, t);
            totalP += p;
            totalT += t;
        }
        System.out.println("──────────────────────────────────────────────────────────────");
        System.out.printf("  Total: %d/%d teste trecute%n", totalP, totalT);
        System.out.println("══════════════════════════════════════════════════════════════");
    }

    /**
     * Runs the tests from a single part (subdirectory).
     * Useful in development: IOTest.runPart("src/.../tests", "partA", Main::main)
     *
     * @param testsDir the relative path to the tests/ directory
     * @param partName the name of the subdirectory (e.g. "partA")
     * @param main reference to Main::main
     */
    public static void runPart(String testsDir, String partName, MainMethod main) {
        File partDir = new File(testsDir, partName);
        if (!partDir.exists() || !partDir.isDirectory()) {
            System.out.println("ERROR: the party directory does not exist: " + partDir.getAbsolutePath());
            return;
        }
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.printf( "║  Partea: %-52s║%n", partName);
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        int[] results = runPartDir(partDir, main);
        System.out.println();
        System.out.printf("Result %s: %d/%d tests passed.%n", partName, results[0], results[1]);
    }

    // ── Internal helpers ─────────────────────────────────────────────────────

    /**
     * Runs all tests (.in/.out) from a single part directory.
     * @return int[]{passed, total}
     */
    private static int[] runPartDir(File dir, MainMethod main) {
        File[] all = dir.listFiles();
        File[] inFiles = (all == null) ? new File[0]
                : Arrays.stream(all).filter(f -> f.getName().endsWith(".in")).toArray(File[]::new);
        if (inFiles.length == 0) {
            System.out.println("  (niciun fișier .in găsit în " + dir.getPath() + ")");
            return new int[]{0, 0};
        }
        Arrays.sort(inFiles, Comparator.comparing(File::getName));

        int passed = 0, failed = 0;
        int testIdx = 0;
        for (File inFile : inFiles) {
            testIdx++;
            String base = inFile.getName().replace(".in", "");
            File outFile = new File(dir, base + ".out");

            System.out.println();
            System.out.println("  *** Test " + testIdx + ": " + base + " ***");

            if (!outFile.exists()) {
                System.out.println("  [SKIP] " + base + " — lipsește " + base + ".out");
                System.out.println("  -------------------------------------------------------------");
                continue;
            }

            String input, expected;
            try {
                input    = Files.readString(inFile.toPath()).replace("\r\n", "\n");
                expected = Files.readString(outFile.toPath()).stripTrailing().replace("\r\n", "\n");
            } catch (IOException e) {
                System.out.println("  [ERROR] " + base + " — nu s-a putut citi: " + e.getMessage());
                failed++;
                System.out.println("  -------------------------------------------------------------");
                continue;
            }

            System.out.println();
            System.out.println("  Input:");
            System.out.println("  ---");
            if (input.isEmpty()) System.out.println("  (empty)");
            else { for (String l : input.split("\n", -1)) System.out.println("  " + l); }
            System.out.println("  ---");
            System.out.println("  Expected:");
            System.out.println("  ---");
            if (expected.isEmpty()) System.out.println("  (empty)");
            else { for (String l : expected.split("\n", -1)) System.out.println("  " + l); }
            System.out.println("  ---");

            String actual = capture(input, main);
            if (actual == null) {
                System.out.println();
                System.out.println("  [FAIL] " + base + " — excepție la rularea Main.main()");
                System.out.println("  -------------------------------------------------------------");
                failed++;
                continue;
            }
            actual = actual.stripTrailing().replace("\r\n", "\n");

            System.out.println();
            System.out.println("  Actual:");
            System.out.println("  ---");
            if (actual.isEmpty()) System.out.println("  (empty)");
            else { for (String l : actual.split("\n", -1)) System.out.println("  " + l); }
            System.out.println("  ---");

            if (actual.equals(expected)) {
                System.out.println("  [PASS] " + base);
                System.out.println();
                System.out.println("  -------------------------------------------------------------");
                passed++;
            } else {
                System.out.println("  [FAIL] " + base);
                System.out.println();
                printDiff(expected, actual);
                System.out.println();
                System.out.println("  -------------------------------------------------------------");
                failed++;
            }
        }

        System.out.println();
        System.out.printf("  Rezultat parte: %d/%d teste trecute.%n", passed, passed + failed);
        return new int[]{passed, passed + failed};
    }

    private static String capture(String input, MainMethod main) {
        InputStream  savedIn  = System.in;
        PrintStream  savedOut = System.out;
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            main.run(new String[0]);
            System.out.flush();
            return baos.toString();
        } catch (Exception e) {
            System.setIn(savedIn);
            System.setOut(savedOut);
            System.out.println("  Excepție: " + e);
            return null;
        } finally {
            System.setIn(savedIn);
            System.setOut(savedOut);
        }
    }

    private static void printDiff(String expected, String actual) {
        String[] expLines = expected.replace("\r\n", "\n").split("\n", -1);
        String[] actLines = actual.replace("\r\n", "\n").split("\n", -1);

        int n = expLines.length, m = actLines.length;
        int[][] lcs = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (expLines[i].equals(actLines[j])) lcs[i][j] = lcs[i + 1][j + 1] + 1;
                else lcs[i][j] = Math.max(lcs[i + 1][j], lcs[i][j + 1]);
            }
        }

        class Op { int type; String line; }
        List<Op> ops = new ArrayList<>();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (expLines[i].equals(actLines[j])) {
                Op o = new Op(); o.type = 0; o.line = expLines[i]; ops.add(o); i++; j++;
            } else if (lcs[i + 1][j] >= lcs[i][j + 1]) {
                Op o = new Op(); o.type = 1; o.line = expLines[i]; ops.add(o); i++;
            } else {
                Op o = new Op(); o.type = 2; o.line = actLines[j]; ops.add(o); j++;
            }
        }
        while (i < n) { Op o = new Op(); o.type = 1; o.line = expLines[i++]; ops.add(o); }
        while (j < m) { Op o = new Op(); o.type = 2; o.line = actLines[j++]; ops.add(o); }

        final int CONTEXT = 3;
        List<int[]> hunks = new ArrayList<>();

        int idx = 0;
        while (idx < ops.size()) {
            while (idx < ops.size() && ops.get(idx).type == 0) idx++;
            if (idx >= ops.size()) break;
            int hStart = Math.max(0, idx - CONTEXT);
            int hEnd = idx;
            while (hEnd < ops.size() && ops.get(hEnd).type != 0) hEnd++;
            hEnd = Math.min(ops.size(), hEnd + CONTEXT);
            if (!hunks.isEmpty()) {
                int[] last = hunks.get(hunks.size() - 1);
                if (hStart <= last[1]) { last[1] = hEnd; }
                else { hunks.add(new int[]{hStart, hEnd}); }
            } else {
                hunks.add(new int[]{hStart, hEnd});
            }
            idx = hEnd;
        }

        if (hunks.isEmpty()) {
            System.out.println("    (diferență, dar nu s-au generat hunk-uri)");
            for (int k = 0; k < Math.min(10, ops.size()); k++) {
                Op o = ops.get(k);
                String prefix = o.type == 0 ? " " : (o.type == 1 ? "-" : "+");
                System.out.println("      " + prefix + " " + o.line);
            }
            return;
        }

        java.util.function.Function<String,String> esc = s -> s
            .replace("\\", "\\\\").replace("\"", "\\\"")
            .replace("\n", "\\n").replace("\t", "\\t");

        boolean firstHunk = true;
        for (int[] h : hunks) {
            if (!firstHunk) System.out.println("  ---");
            firstHunk = false;

            int s = h[0], e = h[1];
            int expStartLine = 1, actStartLine = 1;
            int expCount = 0, actCount = 0;
            for (int k = 0; k < s; k++) {
                Op o = ops.get(k);
                if (o.type == 0 || o.type == 1) expStartLine++;
                if (o.type == 0 || o.type == 2) actStartLine++;
            }
            for (int k = s; k < e; k++) {
                Op o = ops.get(k);
                if (o.type == 0 || o.type == 1) expCount++;
                if (o.type == 0 || o.type == 2) actCount++;
            }
            System.out.printf("    @@ -%d,%d +%d,%d @@%n", expStartLine, Math.max(1, expCount), actStartLine, Math.max(1, actCount));
            StringBuilder expBlock = new StringBuilder();
            StringBuilder actBlock = new StringBuilder();
            for (int k = s; k < e; k++) {
                Op o = ops.get(k);
                String prefix = " ";
                if (o.type == 1) prefix = "-"; else if (o.type == 2) prefix = "+";
                System.out.println("      " + prefix + " " + o.line);
                if (o.type == 0 || o.type == 1) { if (expBlock.length() > 0) expBlock.append('\n'); expBlock.append(o.line); }
                if (o.type == 0 || o.type == 2) { if (actBlock.length() > 0) actBlock.append('\n'); actBlock.append(o.line); }
            }
            String expStr = expBlock.toString();
            String actStr = actBlock.toString();
            if (!expStr.equals(actStr)) {
                System.out.println();
                String compactAct = actStr.isEmpty() ? "(empty)" : esc.apply(actStr);
                String compactExp = expStr.isEmpty() ? "(empty)" : esc.apply(expStr);
                System.out.println("      (Found \"" + compactAct + "\" instead of \"" + compactExp + "\")");
            }
        }
        if (hunks.size() > 3) {
            System.out.println("    (... mai multe hunks: " + hunks.size() + ")");
        }
    }
}

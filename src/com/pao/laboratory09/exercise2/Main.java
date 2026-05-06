package com.pao.laboratory09.exercise2;

import com.pao.laboratory09.exercise1.TransactionType;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Scanner;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;

    public static void main(String[] args) throws Exception {
        // TODO: Implement according to Readme.md
        //
        // 1. Read N from stdin, then the N transactions (id amount date type)
        // 2. Write all records to OUTPUT_FILE using DataOutputStream (binary format, RECORD_SIZE=32 bytes/record)
        //    - bytes 0-3:   id (int, little-endian via ByteBuffer)
        //    - bytes 4-11:  amount (double, little-endian via ByteBuffer)
        //    - bytes 12-21: date (String, 10 chars ASCII, right-padded with spaces)
        //    - byte 22:     type (0=CREDIT, 1=DEBIT)
        //    - byte 23:     status (0=PENDING, 1=PROCESSED, 2=REJECTED)
        //    - bytes 24-31: padding (zeros)
        // 3. Process commands from stdin until EOF using RandomAccessFile:
        //    - READ idx       → seek(idx * RECORD_SIZE), read and display the record
        //    - UPDATE idx ST  → seek(idx * RECORD_SIZE + 23), write the new status (0/1/2)
        //                       display "Updated [idx]: STATUS"
        //    - PRINT_ALL      → read and display all records
        //
        // Output line format:
        //   [idx] id=<id> data=<date> tip=<CREDIT|DEBIT> suma=<amount:.2f> RON status=<STATUS/></CREDIT|DEBIT>

        File file = new File("output");

        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        int n = scanner.nextInt();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            for (int i = 0; i < n; i++) {
                int id = scanner.nextInt();
                double amount = scanner.nextDouble();
                String date = scanner.next();
                TransactionType type = TransactionType.valueOf(scanner.next());

                // id
                dos.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(id).array());

                // amount
                dos.write(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(amount).array());

                // date
                byte[] dateBytes = date.getBytes(StandardCharsets.US_ASCII);
                dos.write(dateBytes);
                for (int j = dateBytes.length; j < 10; j++) {
                    dos.write(' ');
                }

                // type
                dos.writeByte(type.ordinal());

                // Status
                dos.writeByte(0);

                // Padding
                dos.write(new byte[8]);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        try (RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")) {
            while (scanner.hasNext()) {
                String command = scanner.next();
                switch (command) {
                    case "READ":
                        printRecord(raf, scanner.nextInt());
                        break;

                    case "UPDATE":
                        int updateIdx = scanner.nextInt();
                        String newST = scanner.next();

                        // Map String to Byte
                        int statusCode;
                        switch (newST) {
                            case "PENDING": statusCode = 0; break;
                            case "PROCESSED": statusCode = 1; break;
                            case "REJECTED": statusCode = 2; break;
                            default: statusCode = 0;
                        };

                        raf.seek(updateIdx * RECORD_SIZE + 23);
                        raf.writeByte(statusCode);
                        System.out.println("Updated [" + updateIdx + "]: " + newST);
                        break;

                    case "PRINT_ALL":
                        // Move pointer to start, then loop through all possible records
                        long numRecords = raf.length() / RECORD_SIZE;
                        for (int i = 0; i < numRecords; i++) {
                            printRecord(raf, i);
                        }
                        break;
                }
            }
        } catch (IOException e) {System.out.println(e.getMessage());}
    }

    private static void printRecord(RandomAccessFile raf, int idx) throws IOException {
        byte[] buffer = new byte[RECORD_SIZE];
        raf.seek(idx * RECORD_SIZE);
        raf.readFully(buffer);

        ByteBuffer bb = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN);
        int id = bb.getInt(0);
        double amount = bb.getDouble(4);
        String date = new String(buffer, 12, 10, StandardCharsets.US_ASCII).trim();
        int typeByte = buffer[22];
        int statusByte = buffer[23];

        String type;
        if (typeByte == 0) {
            type = "CREDIT";
        } else {
            type = "DEBIT";
        }

        String status;
        if (statusByte == 0) {
            status = "PENDING";
        } else if (statusByte == 1) {
            status = "PROCESSED";
        } else {
            status = "REJECTED";
        }

        System.out.printf(Locale.US, "[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s%n", idx, id, date, type, amount, status);

    }

}

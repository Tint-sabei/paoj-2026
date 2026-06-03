package com.pao.project.etapa2.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;


public class SchemaInitializer {

    public static void init(Connection conn) throws SQLException, IOException {
        String url = conn.getMetaData().getURL();
        String schemaFile;

        if (url.contains("mysql")) {
            schemaFile = "schema.sql";
        } else if (url.contains("sqlite")) {
            schemaFile = "schema.sql";
        } else {
            schemaFile = "schema.sql";
        }

        try (InputStream is = SchemaInitializer.class
                .getClassLoader().getResourceAsStream(schemaFile)) {
            if (is == null) {
                throw new IOException("Schema file not found: " + schemaFile);
            }
            String sql = new String(is.readAllBytes());
            for (String stmt : sql.split(";")) {
                String trimmed = stmt.trim();
                if (!trimmed.isEmpty()) {
                    try (Statement s = conn.createStatement()) {
                        s.execute(trimmed);
                    }
                }
            }
        }
        System.out.println("[DB] The schema initialized from " + schemaFile);
    }
}

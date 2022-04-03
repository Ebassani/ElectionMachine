package com.github.ebassani.electionmachine;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Constants {

    public static String DB_ADDRESS;
    public static String DB_PORT;
    public static String DB_DATABASE;
    public static String DB_USERNAME;
    public static String DB_PASSWORD;

    public static void load() throws Exception {

        DB_ADDRESS = loadSecret("DB_ADDRESS");
        DB_PORT = loadSecret("DB_PORT");
        DB_DATABASE = loadSecret("DB_DATABASE");
        DB_USERNAME = loadSecret("DB_USERNAME");
        DB_PASSWORD = loadSecret("DB_PASSWORD");
    }

    private static String loadSecret(String name) throws Exception {
//        System.out.println(new File("secrets.txt").getAbsolutePath());
        Scanner scanner = new Scanner(new File("secrets.txt"));
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("=");
            if (Objects.equals(line[0], name)) return line[1];
        }
        throw new Exception("Secret not found: " + name);
    }

}

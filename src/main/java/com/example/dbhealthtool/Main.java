package com.example.dbhealthtool;

import com.example.dbhealthtool.config.DatabaseConnection;
import com.example.dbhealthtool.model.TableHealth;
import com.example.dbhealthtool.report.HealthReporter;
import com.example.dbhealthtool.scanner.MetadataScanner;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) {
            System.out.println("Connection failed!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        MetadataScanner scanner = new MetadataScanner(connection);
        HealthReporter reporter = new HealthReporter();

        List<String> tableNames = scanner.getAllTableNames();

        System.out.println("===== DATABASE HEALTH TOOL =====");
        System.out.println("Available Tables:");

        for (int i = 0; i < tableNames.size(); i++) {
            System.out.println((i + 1) + ". " + tableNames.get(i));
        }

        System.out.println();
        System.out.println("1. Scan full database");
        System.out.println("2. Scan single table");
        System.out.print("Choose option: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid input! Enter a number.");
            return;
        }
        int choice = sc.nextInt();

        if (choice == 1) {
            List<TableHealth> list = scanner.scanDatabase();
            reporter.printReport(list);
            reporter.saveReportToFile(list);

        } else if (choice == 2) {
            System.out.print("Choose table number: ");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                return;
            }
            int tableChoice = sc.nextInt();

            if (tableChoice >= 1 && tableChoice <= tableNames.size()) {
                String selectedTable = tableNames.get(tableChoice - 1);
                System.out.println("\nScanning table: " + selectedTable + "...\n");
                List<TableHealth> list = scanner.scanSingleTable(selectedTable);
                reporter.printReport(list);
                reporter.saveReportToFile(list);
            } else {
                System.out.println("Invalid table number!");
            }
        } else {
            System.out.println("Invalid option!");
        }

        sc.close();
    }
}
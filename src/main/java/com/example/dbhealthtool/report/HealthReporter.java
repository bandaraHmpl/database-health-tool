package com.example.dbhealthtool.report;

import com.example.dbhealthtool.model.TableHealth;

import java.util.List;

public class HealthReporter {

    public void printReport(List<TableHealth> tableHealthList) {
        int healthScore = 100;

        System.out.println("\n=== DATABASE HEALTH REPORT ===\n");

        for (TableHealth table : tableHealthList) {
            System.out.println("Table: " + table.getTableName());
            System.out.println("- Primary Key: " + (table.isHasPrimaryKey() ? "YES" : "NO"));
            System.out.println("- Columns: " + table.getColumnCount());
            System.out.println("- Index Count: " + table.getIndexCount());

            if (table.getWarnings().isEmpty()) {
                System.out.println("- Warning: No issue found");
            } else {
                for (String warning : table.getWarnings()) {
                    System.out.println("- Warning: " + warning);

                    if (warning.equals("Missing primary key")) {
                        healthScore -= 15;
                    }

                    if (warning.equals("No indexes found")) {
                        healthScore -= 10;
                    }
                }
            }

            if (!table.getSuggestions().isEmpty()) {
                System.out.println("- Suggestions:");
                for (String suggestion : table.getSuggestions()) {
                    System.out.println("  " + suggestion);                
                }
            }

            System.out.println();
        }

        if (healthScore < 0) {
            healthScore = 0;
        }

        String status;
        if (healthScore >= 90) {
            status = "Excellent";
        } else if (healthScore >= 70) {
            status = "Good";
        } else if (healthScore >= 50) {
            status = "Needs Improvement";
        } else {
            status = "Critical";
        }

        System.out.println("Health Score: " + healthScore + "/100");
        System.out.println("Overall Status: " + status);
    }

    public void saveReportToFile(List<TableHealth> tableHealthList) {
        try {
            java.io.FileWriter writer = new java.io.FileWriter("report.txt");

            writer.write("===== DATABASE HEALTH REPORT =====\n\n");

            for (TableHealth table : tableHealthList) {
                writer.write("Table: " + table.getTableName() + "\n");
                writer.write("Primary Key: " + (table.isHasPrimaryKey() ? "YES" : "NO") + "\n");
                writer.write("Columns: " + table.getColumnCount() + "\n");
                writer.write("Index Count: " + table.getIndexCount() + "\n");

                if (table.getWarnings().isEmpty()) {
                    writer.write("Warning: No issue found\n");
                } else {
                    for (String warning : table.getWarnings()) {
                        writer.write("Warning: " + warning + "\n");
                    }
                }

                if (!table.getSuggestions().isEmpty()) {
                    writer.write("Suggestions:\n");
                    for (String suggestion : table.getSuggestions()) {
                        writer.write("  " + suggestion + "\n");
                    }
                 }

                writer.write("\n");
            }

            writer.close();
            System.out.println("Report saved to report.txt ✅");

        } catch (Exception e) {
            System.out.println("Error saving report!");
            e.printStackTrace();
        }
    }
}
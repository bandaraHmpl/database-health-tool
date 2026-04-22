package com.example.dbhealthtool.report;

import com.example.dbhealthtool.model.TableHealth;

public class SuggestionEngine {

    public void generateSuggestions(TableHealth table) {

        if (!table.isHasPrimaryKey()) {
            table.addSuggestion(
                    "ALTER TABLE " + table.getTableName() +
                    " ADD COLUMN id INT AUTO_INCREMENT PRIMARY KEY;"
            );
        }

        if (table.getIndexCount() == 0) {
            table.addSuggestion(
                    "-- Review frequently searched columns in table '" + table.getTableName() + "' and add indexes."
            );
            table.addSuggestion(
                    "-- Example: CREATE INDEX idx_" + table.getTableName() + "_column ON " +
                    table.getTableName() + "(column_name);"
            );
        }

        if (table.getColumnCount() > 10) {
            table.addSuggestion(
                    "-- Table '" + table.getTableName() + "' has many columns. Review normalization if needed."
            );
        }
    }
}
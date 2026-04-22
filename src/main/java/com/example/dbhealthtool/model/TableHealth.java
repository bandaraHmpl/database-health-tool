package com.example.dbhealthtool.model;

import java.util.ArrayList;
import java.util.List;

public class TableHealth {
    private String tableName;
    private int columnCount;
    private boolean hasPrimaryKey;
    private int indexCount;
    private List<String> warnings = new ArrayList<>();
    private List<String> suggestions = new ArrayList<>();

    public TableHealth(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public boolean isHasPrimaryKey() {
        return hasPrimaryKey;
    }

    public void setHasPrimaryKey(boolean hasPrimaryKey) {
        this.hasPrimaryKey = hasPrimaryKey;
    }

    public int getIndexCount() {
        return indexCount;
    }

    public void setIndexCount(int indexCount) {
        this.indexCount = indexCount;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void addWarning(String warning) {
        warnings.add(warning);
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void addSuggestion(String suggestion) {
        suggestions.add(suggestion);
    }
}
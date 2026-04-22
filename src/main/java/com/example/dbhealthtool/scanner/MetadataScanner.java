package com.example.dbhealthtool.scanner;

import com.example.dbhealthtool.model.TableHealth;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MetadataScanner {

    private final Connection connection;

    public MetadataScanner(Connection connection) {
        this.connection = connection;
    }

    public List<TableHealth> scanDatabase() {
        List<TableHealth> tableHealthList = new ArrayList<>();

        try {
            DatabaseMetaData metaData = connection.getMetaData();

            ResultSet tables = metaData.getTables(connection.getCatalog(), null, "%", new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                TableHealth tableHealth = analyzeTable(metaData, tableName);
                tableHealthList.add(tableHealth);
            }

        } catch (Exception e) {
            System.out.println("Error while scanning database: " + e.getMessage());
            e.printStackTrace();
        }

        return tableHealthList;
    }

    public List<TableHealth> scanSingleTable(String tableName) {
        List<TableHealth> tableHealthList = new ArrayList<>();

        try {
            DatabaseMetaData metaData = connection.getMetaData();

            ResultSet tables = metaData.getTables(connection.getCatalog(), null, tableName, new String[]{"TABLE"});

            while (tables.next()) {
                String name = tables.getString("TABLE_NAME");
                TableHealth tableHealth = analyzeTable(metaData, name);
                tableHealthList.add(tableHealth);
            }

        } catch (Exception e) {
            System.out.println("Error while scanning single table: " + e.getMessage());
            e.printStackTrace();
        }

        return tableHealthList;
    }

    private TableHealth analyzeTable(DatabaseMetaData metaData, String tableName) throws Exception {
        TableHealth tableHealth = new TableHealth(tableName);

        ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableName, null);
        int columnCount = 0;
        while (columns.next()) {
            columnCount++;
        }
        tableHealth.setColumnCount(columnCount);

        ResultSet primaryKeys = metaData.getPrimaryKeys(connection.getCatalog(), null, tableName);
        boolean hasPrimaryKey = primaryKeys.next();
        tableHealth.setHasPrimaryKey(hasPrimaryKey);

        if (!hasPrimaryKey) {
            tableHealth.addWarning("Missing primary key");
        }

        ResultSet indexes = metaData.getIndexInfo(connection.getCatalog(), null, tableName, false, false);
        Set<String> uniqueIndexes = new HashSet<>();

        while (indexes.next()) {
            String indexName = indexes.getString("INDEX_NAME");
            if (indexName != null) {
                uniqueIndexes.add(indexName);
            }
        }

        tableHealth.setIndexCount(uniqueIndexes.size());

        if (uniqueIndexes.isEmpty()) {
            tableHealth.addWarning("No indexes found");
        }

        return tableHealth;
    }

    public List<String> getAllTableNames() {
        List<String> tableNames = new ArrayList<>();

        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(connection.getCatalog(), null, "%", new String[]{"TABLE"});

            while (tables.next()) {
                tableNames.add(tables.getString("TABLE_NAME"));
            }

        } catch (Exception e) {
            System.out.println("Error while fetching table names: " + e.getMessage());
            e.printStackTrace();
        }

        return tableNames;
    }
}
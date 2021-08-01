package com.finalexam.cookingapp.database;

import java.util.List;
import java.util.Map;

public class TableInfo {
    private String tableName;

    private Map<String, String> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, String> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, String> columns) {
        this.columns = columns;
    }

    public TableInfo(
            String tableName,
            Map<String, String> columns
    ) {
        this.tableName = tableName;
        this.columns = columns;
    }
}

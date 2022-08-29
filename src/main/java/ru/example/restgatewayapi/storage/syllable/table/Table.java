package ru.example.restgatewayapi.storage.syllable.table;

import ru.example.restgatewayapi.storage.syllable.column.RingingDeafPairColumn;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<RingingDeafPairColumn> tableColumns;

    public Table() {
        this.tableColumns = new ArrayList<>();
    }

    public void addColumn(RingingDeafPairColumn column) {
        this.tableColumns.add(column);
    }
}

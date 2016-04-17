package repository;

import mapdb.MapDbException;
import mapdb.MapDbService;
import model.*;
import xml.XMLReader;
import xml.XMLWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Repository {

    private List<Database> databases;
    private XMLWriter xmlWriter;

    public Repository() {
        xmlWriter = new XMLWriter();
        initializeDatabases();
    }

    private void initializeDatabases() {
        XMLReader XMLReader = new XMLReader();
        databases = XMLReader.getDatabases();
        loadData();
    }

    private void loadData() {
        for (Database database : databases) {
            for (Table table : database.getTables()) {
                List<Record> records = new ArrayList<>();
                for (Map.Entry<String, String> entry : MapDbService.getInstance().getRecords(database.getName(), table.getName()).entrySet()) {
                    Record record = new Record(entry.getKey(), entry.getValue(), table.getAttributes());
                    records.add(record);
                }
                table.setRecords(records);
            }
        }
    }

    public List<Database> getDatabases() {
        return databases;
    }

    public void addDatabase(String databaseName) {
        databases.add(new Database(databaseName));
        xmlWriter.writeDatabases(databases);
    }

    public void addTable(String databaseName, String tableName) {
        getTables(databaseName).add(new Table(tableName, tableName + ".kv"));
        xmlWriter.writeDatabases(databases);
    }

    public void addIndex(String databaseName, String tableName, String attribute) {
        Index index = new Index(tableName + "_" + attribute + ".ind");
        index.addIndexAttribute(attribute);
        getIndexes(databaseName, tableName).add(index);
        xmlWriter.writeDatabases(databases);
    }

    public void addAttribute(String databaseName, String tableName, String attributeName, String attributeType, String length, boolean isPrimary, boolean isNotNull, boolean isUnique) {
        Attribute attribute = new Attribute(attributeName, attributeType);
        if(!length.equals("")) {
            attribute.setLength(Integer.parseInt(length));
        }
        attribute.setCanBeNull(isNotNull);
        attribute.setPrimaryKey(isPrimary);
        attribute.setUniqueKey(isUnique);
        getAttributes(databaseName, tableName).add(attribute);
        xmlWriter.writeDatabases(databases);
    }

    public void addForeignKey(String databaseName, String tableName, String attribute, String refTable, String refAttribute) {
        ForeignKey foreignKey = new ForeignKey();
        foreignKey.addName(attribute);
        foreignKey.setRefTable(refTable);
        foreignKey.addAttribute(refAttribute);
        getForeignKeys(databaseName, tableName).add(foreignKey);
        xmlWriter.writeDatabases(databases);
    }

    public void deleteDatabase(String databaseName) {
        boolean found = false;
        Database databaseToRemove = null;
        for (Database database : databases) {
            if (database.getName().equals(databaseName)) {
                databaseToRemove = database;
                found = true;
            }
        }
        if (found) {
            databases.remove(databaseToRemove);
        }
        xmlWriter.writeDatabases(databases);
    }

    public void deleteTable(String databaseName, String tableName) {
        boolean found = false;
        Table tableToRemove = null;
        for (Table table : getTables(databaseName)) {
            if (table.getName().equals(tableName)) {
                tableToRemove = table;
                found = true;
            }
        }
        if (found) {
            getTables(databaseName).remove(tableToRemove);
        }
        xmlWriter.writeDatabases(databases);
    }

    public Table findTableByName(String databaseName, String tableName) {
        for (Database database : databases) {
            if (database.getName().equals(databaseName)) {
                for (Table table : database.getTables()) {
                    if(table.getName().equals(tableName)) {
                        return table;
                    }
                }
            }
        }
        return null;
    }

    public List<Table> getTables(String databaseName) {
        for (Database database : databases) {
            if (database.getName().equals(databaseName)) {
                return database.getTables();
            }
        }
        return new ArrayList<>();
    }

    public List<Attribute> getAttributes(String databaseName, String tableName) {
        List<Table> tables = getTables(databaseName);
        for (Table table : tables) {
            if (table.getName().equals(tableName)) {
                return table.getAttributes();
            }
        }
        return new ArrayList<>();
    }

    public List<ForeignKey> getForeignKeys(String databaseName, String tableName) {
        List<Table> tables = getTables(databaseName);
        for (Table table : tables) {
            if (table.getName().equals(tableName)) {
                return table.getForeignKeys();
            }
        }
        return new ArrayList<>();
    }

    public List<Index> getIndexes(String databaseName, String tableName) {
        List<Table> tables = getTables(databaseName);
        for (Table table : tables) {
            if (table.getName().equals(tableName)) {
                return table.getIndexes();
            }
        }
        return new ArrayList<>();
    }
}

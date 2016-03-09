package repository;

import model.*;
import xml.XMLReader;
import xml.XMLWriter;

import java.util.ArrayList;
import java.util.List;

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
        attribute.setLength(Integer.parseInt(length));
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

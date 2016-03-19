package service;

import mapdb.MapDbService;
import model.*;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseService {

    private static DatabaseService instance;
    private Repository repository;

    private DatabaseService() {
        repository = new Repository();
    }

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    public List<Database> getDatabases() {
        return repository.getDatabases();
    }

    public List<Table> getTables(String databaseName) {
        return repository.getTables(databaseName);
    }

    public List<Attribute> getAttributes(String databaseName, String tableName) {
        return repository.getAttributes(databaseName, tableName);
    }

    public List<ForeignKey> getForeignKeys(String databaseName, String tableName) {
        return repository.getForeignKeys(databaseName, tableName);
    }

    public List<Index> getIndexes(String databaseName, String tableName) {
        return repository.getIndexes(databaseName, tableName);
    }

    public void addDatabase(String name) {
        repository.addDatabase(name);
    }

    public void addTable(String databaseName, String tableName) {
        repository.addTable(databaseName, tableName);
    }

    public void addRecord(String databaseName, String tableName, List<String> values) {
        Table table = repository.findTableByName(databaseName, tableName);
        Record record = new Record(table.getAttributes(), values);
        MapDbService.getInstance().insert(record, databaseName, tableName);
        table.addRecord(record);
    }

    public List<Record> getRecords(String databaseName, String tableName) {
        List<Record> records = new ArrayList<>();
        Table table = repository.findTableByName(databaseName, tableName);
        for (Map.Entry<String, String> entry : MapDbService.getInstance().getRecords(databaseName, tableName).entrySet()) {
            records.add(new Record(entry.getKey(), entry.getValue(), table.getAttributes()));
        }
        return records;
    }

    public void deleteRecord(String databaseName, String tableName, List<String> values) {
        Table table = repository.findTableByName(databaseName, tableName);
        Record record = new Record(table.getAttributes(), values);
        MapDbService.getInstance().delete(record, databaseName, tableName);
        table.deleteRecord(record);
    }

    public void addAttribute(String databaseName, String tableName, String attributeName, String attributeType, String length, boolean isPrimary, boolean isNotNull, boolean isUnique) {
        repository.addAttribute(databaseName, tableName, attributeName, attributeType, length, isPrimary, isNotNull, isUnique);
    }

    public void addForeignKey(String databaseName, String tableName, String attribute, String refTable, String refAttribute) {
        repository.addForeignKey(databaseName, tableName, attribute, refTable, refAttribute);
    }

    public void addIndex(String databaseName, String tableName, String attribute) {
        repository.addIndex(databaseName, tableName, attribute);
    }

    public void deleteDatabase(String name) {
        repository.deleteDatabase(name);
    }

    public void deleteTable(String databaseName, String tableName) {
        repository.deleteTable(databaseName, tableName);
    }
}

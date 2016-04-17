package service;

import mapdb.MapDbException;
import mapdb.MapDbService;
import model.*;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void addRecord(String databaseName, String tableName, Map<String, String[]> values) throws MapDbException {
        Table table = repository.findTableByName(databaseName, tableName);
        Record record = new Record(table.getAttributes(), values);
        Map<Attribute, String> uniqueKeys = record.getUniqueKeys();
        MapDbService.getInstance().validateRecord(record, databaseName, tableName);
        for (Map.Entry<Attribute, String> entry : uniqueKeys.entrySet()) {
            MapDbService.getInstance().makeUniqueIndexFile(entry.getKey().getIndexName(), entry.getValue(), record.getStoreKey(), databaseName);
        }
        Map<Attribute, String> foreignKeys = record.getForeignKeys();
        for (Map.Entry<Attribute, String> entry : foreignKeys.entrySet()) {
            MapDbService.getInstance().makeNonuniqueIndexFile(entry.getKey().getIndexName(), entry.getValue(), record.getStoreKey(), databaseName);
        }
        MapDbService.getInstance().insert(record, databaseName, tableName);
        table.addRecord(record);
    }

    public List<Record> projection(String databaseName, String tableName, List<Attribute> selectedAttributes) {
        List<Record> records = getRecords(databaseName, tableName);
        return records.stream()
                .map(record -> record.getProjectedRecord(selectedAttributes))
                .collect(Collectors.toList());
    }

    public List<Record> getRecords(String databaseName, String tableName) {
        Table table = repository.findTableByName(databaseName, tableName);
        return table.getRecords();
    }

    public List<String> getRecordsOfAttribute(String databaseName, String tableName, String attributeName) {
        List<String> result = new ArrayList<>();
        Table table = findTableByName(databaseName, tableName);
        Attribute attribute = table.getAttribute(attributeName);
        for (Record record : table.getRecords()) {
            result.add(record.getValue(attribute));
        }
        return result;
    }

    public void deleteRecord(String databaseName, String tableName, String primaryKey) throws MapDbException {
        Table table = repository.findTableByName(databaseName, tableName);
        Record record = table.getRecord(primaryKey);
        if(!MapDbService.getInstance().hasChildren(record, databaseName, table)) {
            MapDbService.getInstance().delete(record, databaseName, tableName);
        } else {
            throw new MapDbException("You cannot delete this record! It is referenced by another table!");
        }
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

    public Table findTableByName(String databaseName, String tableName) {
        return repository.findTableByName(databaseName, tableName);
    }
}

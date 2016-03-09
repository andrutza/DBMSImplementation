package service;

import model.*;
import repository.Repository;

import java.util.List;

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

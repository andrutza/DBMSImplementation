package service;

import model.Attribute;
import model.Database;
import model.Table;
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

    public void addDatabase(String name) {
        repository.addDatabase(name);
    }

    public void deleteDatabase(String name) {
        repository.deleteDatabase(name);
    }
}

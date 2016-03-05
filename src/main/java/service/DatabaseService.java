package service;

import model.Attribute;
import model.Database;
import model.ForeignKey;
import model.Table;
import xml.Reader;
import xml.Writer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 29-02-16.
 */
public class DatabaseService {

    private static DatabaseService instance;
    private List<Database> databases;

    private DatabaseService() {
        Reader reader = new Reader();
        databases = reader.getDatabases();
    }

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    public List<Database> getDatabases() {
        return databases;
    }

    public void addDatabase(String databaseName) {
        databases.add(new Database(databaseName));
        new Writer(databases);
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
        if(found) {
            databases.remove(databaseToRemove);
        }
        new Writer(databases);
    }

    public List<Table> getTables(String databaseName) {
        for (Database database : databases) {
            if(database.getName().equals(databaseName)) {
                return database.getTables();
            }
        }
        return new ArrayList<Table>();
    }

    public List<Attribute> getAttributes(String databaseName, String tableName) {
        List<Table> tables = getTables(databaseName);
        for (Table table : tables) {
            if(table.getName().equals(tableName)) {
                return table.getAttributes();
            }
        }
        return new ArrayList<Attribute>();
    }

    public List<ForeignKey> getForeignKeys(String databaseName, String tableName) {
        List<Table> tables = getTables(databaseName);
        for (Table table : tables) {
            if(table.getName().equals(tableName)) {
                return table.getForeignKeys();
            }
        }
        return new ArrayList<ForeignKey>();
    }
}

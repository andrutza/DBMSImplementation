package repository;

import model.Attribute;
import model.Database;
import model.Table;
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
        xmlWriter.writeDatabases(databases);
    }

    public List<Table> getTables(String databaseName) {
        for (Database database : databases) {
            if(database.getName().equals(databaseName)) {
                return database.getTables();
            }
        }
        return new ArrayList<>();
    }

    public List<Attribute> getAttributes(String databaseName, String tableName) {
        List<Table> tables = getTables(databaseName);
        for (Table table : tables) {
            if(table.getName().equals(tableName)) {
                return table.getAttributes();
            }
        }
        return new ArrayList<>();
    }
}

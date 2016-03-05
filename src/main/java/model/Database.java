package model;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private String name;
    private List<Table> tables;

    public Database(String name) {
        this.setName(name);
        tables = new ArrayList<>();
    }

    public void addTable(Table table){
        tables.add(table);
    }

    public void removeTable(String name){
        for(int i = 0;i<tables.size();i++){
            if(tables.get(i).getName().equals(name)){
                tables.remove(i);
            }
        }
    }

    public Table getTable(String name){
        for(Table table : tables){
            if (table.getName().equals(name)){
                return table;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Table> getTables(){
        return this.tables;
    }
}

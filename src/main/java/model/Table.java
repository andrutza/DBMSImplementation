package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28-02-16.
 */
public class Table {
    private String name;
    private String fileName;
    private List<Attribute> attributes;

    private List<Attribute> primaryKeys;
    private List<Attribute> uniqueKeys;

    private List<ForeignKey> foreignKeys;
    private List<Index> indexes;

    public Table(String name, String fileName) {
        this.setName(name);
        this.setFileName(fileName);
        attributes = new ArrayList<Attribute>();
        indexes = new ArrayList<Index>();
        primaryKeys = new ArrayList<Attribute>();
        uniqueKeys = new ArrayList<Attribute>();
        foreignKeys = new ArrayList<ForeignKey>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<ForeignKey> getForeignKeys() {
        return foreignKeys;
    }

    public List<Index> getIndexes() {
        return indexes;
    }

    public List<Attribute> getPrimaryKeys() {
        return primaryKeys;
    }

    public List<Attribute> getUniqueKeys() {
        return uniqueKeys;
    }

    public void addAttribute(Attribute attr) {
        attributes.add(attr);
    }

    public void addForeignKey(ForeignKey fk) {
        foreignKeys.add(fk);
    }

    public Attribute getAtribute(String name) {
        for (Attribute attr : attributes) {
            if (attr.getName().equals(name)) {
                return attr;
            }
        }
        return null;
    }

    public void addPrimaryKey(Attribute name) {
        primaryKeys.add(name);
    }

    public void addUniqueKey(Attribute attr) {
        uniqueKeys.add(attr);
    }

    public void addIndex(Index index) {
        indexes.add(index);
    }

}

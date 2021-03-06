package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Table {
    private String name;
    private String fileName;
    private List<Attribute> attributes;
    private List<ForeignKey> foreignKeys;
    private List<Index> indexes;
    private List<Record> records;

    public Table(String name, String fileName) {
        this.setName(name);
        this.setFileName(fileName);
        attributes = new ArrayList<>();
        indexes = new ArrayList<>();
        foreignKeys = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ForeignKey getForeignKey(String attribute) {
        for (ForeignKey foreignKey : foreignKeys) {
            if (attribute.equals(foreignKey.getNames().get(0))) {
                getAttribute(attribute).setForeignKey(true);
                return foreignKey;
            }
        }
        return null;
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

    public void addAttribute(Attribute attr) {
        attributes.add(attr);
    }

    public void addForeignKey(ForeignKey fk) {
        foreignKeys.add(fk);
    }

    public Attribute getAttribute(String name) {
        for (Attribute attr : attributes) {
            if (attr.getName().equals(name)) {
                return attr;
            }
        }
        return null;
    }

    public void makeAttributePrimaryKey(String name) {
        Attribute attribute = getAttribute(name);
        if(attribute != null) {
            attribute.setPrimaryKey(true);
        }
    }

    public void makeAttributeUniqueKey(String name) {
        Attribute attribute = getAttribute(name);
        if(attribute != null) {
            attribute.setUniqueKey(true);
        }
    }

    public void addIndex(Index index) {
        indexes.add(index);
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public void deleteRecord(Record record) {
        records.remove(record);
    }

    public List<Attribute> getPrimaryKeys() {
        return attributes.stream()
                .filter(Attribute::isPrimaryKey)
                .collect(Collectors.toList());
    }

    public List<Attribute> getUniqueKeys() {
        return attributes.stream()
                .filter(Attribute::isUniqueKey)
                .collect(Collectors.toList());
    }


    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Record getRecord(String primaryKey) {
        for (Record record : records) {
            if(record.getStoreKey().equals(primaryKey)) {
                return record;
            }
        }
        return null;
    }
}

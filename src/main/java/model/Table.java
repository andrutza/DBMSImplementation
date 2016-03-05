package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Table {
    private String name;
    private String fileName;
    private List<Attribute> attributes;
    private List<ForeignKey> foreignKeys;
    private List<Index> indexes;

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
}

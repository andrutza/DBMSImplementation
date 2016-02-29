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
    private List<Index> indexes;

    public Table(String name, String fileName) {
        this.setName(name);
        this.setFileName(fileName);
        attributes = new ArrayList<Attribute>();
        indexes = new ArrayList<Index>();
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

    public List<Index> getIndexes() {
        return indexes;
    }

}

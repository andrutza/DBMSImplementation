package model;

import java.util.ArrayList;
import java.util.List;

public class ForeignKey {
    private List<String> names;
    private List<String> references;
    private String refTable;


    public ForeignKey() {
        names = new ArrayList<>();
        references = new ArrayList<>();
    }

    public void addName(String name){
        names.add(name);
    }

    public void addAttribute(String reference){
        references.add(reference);
    }

    public List<String> getNames() {
        return names;
    }

    public List<String> getReferences() {
        return references;
    }

    public String getRefTable() {
        return refTable;
    }

    public void setRefTable(String refTable) {
        this.refTable = refTable;
    }

}

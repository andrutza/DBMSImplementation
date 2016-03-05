package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28-02-16.
 */
public class ForeignKey {
    private List<String> names;
    private List<Attribute> references;
    private String refTable;


    public ForeignKey() {
        names = new ArrayList<String>();
        references = new ArrayList<Attribute>();

    }

    public void addName(String name){
        names.add(name);
    }

    public void addAttribute(Attribute reference){
        references.add(reference);
    }

    public List<String> getNames() {
        return names;
    }

    public List<Attribute> getReferences() {
        return references;
    }

    public String getRefTable() {
        return refTable;
    }

    public void setRefTable(String refTable) {
        this.refTable = refTable;
    }

}

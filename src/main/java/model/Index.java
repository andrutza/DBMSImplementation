package model;

import java.util.ArrayList;
import java.util.List;

public class Index {
    private String name;
    private List<Attribute> indexAttributes;

    public Index(String name) {
        indexAttributes = new ArrayList<>();
        this.setName(name);
    }

    public List<Attribute> getIndexAttributes() {
        return indexAttributes;
    }

    public void addIndexAttribute(Attribute a){
        indexAttributes.add(a);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

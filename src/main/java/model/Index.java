package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28-02-16.
 */
public class Index {
    private String name;
    private List<Attribute> indexAttributes;

    public List<Attribute> getIndexAttributes() {
        return indexAttributes;
    }

    public Index(String name) {
        indexAttributes = new ArrayList<Attribute>();
        this.setName(name);
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

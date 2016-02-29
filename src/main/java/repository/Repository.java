package repository;

import model.Attribute;
import model.ForeignKey;
import model.Index;

/**
 * Created by User on 28-02-16.
 */
public class Repository {

    public Repository() {

    }

    public void addForeignKey(ForeignKey fk) {
        foreignKeys.add(fk);
    }

    public void addIndex(Index index) {
        indexes.add(index);
    }

    public void addAttribute(Attribute attr) {
        attributes.add(attr);
    }

    public int getPrimaryKeyIndex() {
        int i = 0;
        for (Attribute a : attributes) {
            if (primaryKeys.contains(a)) {
                return i;
            }
            i++;
        }
        return 0;
    }

    public boolean isPrimaryAttr(String attr) {
        return primaryKeys.contains(getAtribute(attr));
    }

    public boolean isForeignAttr(String attr) {
        for (ForeignKey f : foreignKeys) {
            for (Attribute a : f.getReferences()) {
                if (attr.equals(a.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isUniqueAttr(String attr) {
        for (Attribute a : uniqueKeys) {
            if (attr.equals(a.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean isIndexAttr(String attr) {
        for (Index i : indexes) {
            if (attr.equals(i.getName())) {
                return true;
            }
        }
        return false;
    }

    public Attribute getAtribute(String name) {
        for (Attribute attr : attributes) {
            if (attr.getName().equals(name)) {
                return attr;
            }
        }
        return null;
    }
}

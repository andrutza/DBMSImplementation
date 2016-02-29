package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28-02-16.
 */
public class ForeignKey {
    private Table referenceTable;
    private Table referencedTable;
    private Attribute referenceAttribute;
    private Attribute referencedAttribute;

    public Table getReferenceTable() {
        return referenceTable;
    }

    public void setReferenceTable(Table referenceTable) {
        this.referenceTable = referenceTable;
    }

    public Table getReferencedTable() {
        return referencedTable;
    }

    public void setReferencedTable(Table referencedTable) {
        this.referencedTable = referencedTable;
    }

    public Attribute getReferenceAttribute() {
        return referenceAttribute;
    }

    public void setReferenceAttribute(Attribute referenceAttribute) {
        this.referenceAttribute = referenceAttribute;
    }

    public Attribute getReferencedAttribute() {
        return referencedAttribute;
    }

    public void setReferencedAttribute(Attribute referencedAttribute) {
        this.referencedAttribute = referencedAttribute;
    }
}

package model;

/**
 * Created by User on 28-02-16.
 */
public class Attribute {
    private String name;
    private String type;
    private Integer length;
    private boolean notNull;
    private boolean isPrimaryKey;
    private boolean isUniqueKey;

    public boolean isUniqueKey() {
        return isUniqueKey;
    }

    public void setUniqueKey(boolean uniqueKey) {
        isUniqueKey = uniqueKey;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public Attribute(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }
}

package model;

public class Attribute {
    private String name;
    private String type;
    private Integer length;
    private boolean canBeNull;
    private boolean isPrimaryKey;
    private boolean isUniqueKey;

    public Attribute(String name, String type) {
        this.name = name;
        this.type = type;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeAndLength() {
        return type + (length != null ? "(" + length + ")" : "");
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public boolean isCanBeNull() {
        return canBeNull;
    }

    public void setCanBeNull(boolean canBeNull) {
        this.canBeNull = canBeNull;
    }
}

package model;

/**
 * Created by User on 19-04-16.
 */
public class Filter {

    public enum Operation {
        EQUAL,GREATER_THAN,LESS_THAN,GREATER_OR_EQUAL,LESS_OR_EQUAL,NOT_EQUAL
    }

    private Attribute attribute;
    private Operation operation;
    private String value;

    public Filter(Attribute attribute, Operation operation, String value) {
        this.attribute = attribute;
        this.operation = operation;
        this.value = value;
    }

    public boolean matches(Record record) {
        String value = record.getValue(attribute);
        Comparable toBeCompared;
        Comparable toBeComparedWith;
        switch (attribute.getType()) {
            case "int":
                toBeCompared = Integer.parseInt(value);
                toBeComparedWith =  Integer.parseInt(this.value);
                break;
            case "double:":
                toBeCompared = Double.parseDouble(value);
                toBeComparedWith =  Double.parseDouble(this.value);
                break;
            default:
                toBeCompared = value;
                toBeComparedWith = this.value;
                break;
        }
        switch (operation) {
            case EQUAL:
                return toBeCompared.compareTo(toBeComparedWith) == 0;
            case GREATER_THAN:
                return toBeCompared.compareTo(toBeComparedWith) > 0;
            case LESS_THAN:
                return toBeCompared.compareTo(toBeComparedWith) < 0;
            case GREATER_OR_EQUAL:
                return toBeCompared.compareTo(toBeComparedWith) >= 0;
            case LESS_OR_EQUAL:
                return toBeCompared.compareTo(toBeComparedWith) <= 0;
            case NOT_EQUAL:
                return toBeCompared.compareTo(toBeComparedWith) != 0;
        }
        return false;
    }
}

package model;

import java.util.*;

/**
 * Created by User on 16-03-16.
 */
public class Record {

    private Map<Attribute, String> values = new LinkedHashMap<>();

    private Record(Map<Attribute, String> values) {
        this.values = values;
    }

    public Record(List<Attribute> attributes, Map<String, String[]> values) {
        for (Map.Entry<String, String[]> entry : values.entrySet()) {
            for (Attribute attribute : attributes) {
                if(attribute.getName().equals(entry.getKey())) {
                    this.values.put(attribute, entry.getValue()[0]);
                }
            }
        }
    }

    public Record(List<Attribute> attributes, List<String> values) {
        for (int i = 0; i < attributes.size(); i++) {
            this.values.put(attributes.get(i), values.get(i));
        }
    }

    public Record(String storeKey, String storeValue, List<Attribute> attributes) {
//        String[] primaryKeys = storeKey.split("#");
        String[] values = storeValue.split("#");
//        int primaryKeysIndex = 0;
        int valuesIndex = 0;
        for (Attribute attribute : attributes) {
            if(attribute.isPrimaryKey()) {
//                this.values.put(attribute, primaryKeys[primaryKeysIndex++]);
                this.values.put(attribute, storeKey);
            } else {
                this.values.put(attribute, values[valuesIndex++]);
            }
        }
    }

    public String getStoreKey() {
        String storeKey = null;
        for (Map.Entry<Attribute, String> entry : values.entrySet()) {
            if(entry.getKey().isPrimaryKey()) {
                if(storeKey == null) {
                    storeKey = entry.getValue();
                } else {
                    storeKey += "#"+entry.getValue();
                }
            }
        }
        return storeKey;
    }

    public String getStoreValue() {
        String storeValue = null;
        for (Map.Entry<Attribute, String> entry : values.entrySet()) {
            if(!entry.getKey().isPrimaryKey()) {
                if(storeValue == null) {
                    storeValue = entry.getValue();
                } else {
                    storeValue += "#"+entry.getValue();
                }
            }
        }
        return storeValue;
    }

    public Map<Attribute, String> getUniqueKeys() {
        Map<Attribute, String> map = new HashMap<>();
        for (Map.Entry<Attribute, String> entry : values.entrySet()) {
            if(entry.getKey().isUniqueKey()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    public Map<Attribute, String> getForeignKeys() {
        Map<Attribute, String> map = new HashMap<>();
        for (Map.Entry<Attribute, String> entry : values.entrySet()) {
            if(entry.getKey().isForeignKey()) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    public Record getProjectedRecord(List<Attribute> attributes) {
        Map<Attribute, String> projectedValues = new HashMap<>();
        for (Attribute attribute : attributes) {
            projectedValues.put(attribute, getValue(attribute));
        }
        return new Record(projectedValues);
    }

    public String getValue(Attribute attribute) {
        return values.get(attribute);
    }
}

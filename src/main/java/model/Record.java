package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 16-03-16.
 */
public class Record {

    private Map<Attribute, String> values;

    public Record(List<Attribute> attributes, List<String> values) {
        this.values = new HashMap<>();
        for (int i = 0; i < attributes.size(); i++) {
            this.values.put(attributes.get(i), values.get(i));
        }
    }

    public Record(String storeKey, String storeValue, List<Attribute> attributes) {
        this.values = new HashMap<>();
        String[] primaryKeys = storeKey.split("#");
        String[] values = storeValue.split("#");
        int primaryKeysIndex = 0;
        int valuesIndex = 0;
        for (Attribute attribute : attributes) {
            if(attribute.isPrimaryKey()) {
                this.values.put(attribute, primaryKeys[primaryKeysIndex++]);
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
}

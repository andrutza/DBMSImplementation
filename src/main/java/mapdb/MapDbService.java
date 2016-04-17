package mapdb;

import model.*;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import service.DatabaseService;
import utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 16-03-16.
 */
public class MapDbService {
    public static MapDbService instance;
    public final FileUtils fileUtils = new FileUtils();

    private MapDbService() {
    }

    public static MapDbService getInstance() {
        if (instance == null) {
            instance = new MapDbService();
        }
        return instance;
    }

    public DB getDb(String filename) {
        File tableFile = new File(filename);
        return DBMaker.newFileDB(tableFile).make();
    }

    public void validateRecord(Record record, String databaseName, String tableName) throws MapDbException {
        DB db = getDb(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + tableName + ".kv");
        BTreeMap<String, String> map = db.getTreeMap(tableName);
        if(map.containsKey(record.getStoreKey())) {
            throw new MapDbException("Primary key is already used!");
        }
        for (Map.Entry<Attribute, String> uniqueKey : record.getUniqueKeys().entrySet()) {
            String indexName = uniqueKey.getKey().getIndexName();
            DB dbUniqueKey = getDb(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + indexName);
            BTreeMap<String, String> mapUniqueKey = dbUniqueKey.getTreeMap(indexName);
            if(mapUniqueKey.containsKey(uniqueKey.getValue())) {
                throw new MapDbException("Unique key already exists!");
            }
            dbUniqueKey.close();
        }
        db.close();
    }

    public Record insert(Record record, String databaseName, String tableName) throws MapDbException {
        DB db = getDb(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + tableName + ".kv");
        BTreeMap<String, String> map = db.getTreeMap(tableName);
        map.put(record.getStoreKey(), record.getStoreValue());
        db.commit();
        db.close();
        return record;
    }

    public void makeUniqueIndexFile(String indexName, String indexValue, String primaryKeyValue, String databaseName) throws MapDbException {
        DB db = getDb(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + indexName);
        BTreeMap<String, String> map = db.getTreeMap(indexName);
        map.put(indexValue, primaryKeyValue);
        db.commit();
        db.close();
    }

    public void makeNonuniqueIndexFile(String indexName, String indexValue, String primaryKeyValue, String databaseName) {
        File indexFile = new File(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + indexName);
        DB db = DBMaker.newFileDB(indexFile).make();
        BTreeMap<String, List<String>> map = db.getTreeMap(indexName);
        List<String> primaryKeyValues = map.get(indexValue);
        if(primaryKeyValues == null) {
            primaryKeyValues = new ArrayList<>();
        }
        primaryKeyValues.add(primaryKeyValue);
        map.put(indexValue, primaryKeyValues);
        db.commit();
        db.close();
    }

    public void deleteUniqueIndex(Record record, String databaseName) {
        for (Map.Entry<Attribute, String> entry : record.getUniqueKeys().entrySet()) {
            DB db = getDb(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + entry.getKey().getIndexName());
            BTreeMap<String, String> map = db.getTreeMap(entry.getKey().getIndexName());
            map.remove(record.getStoreKey());
            db.commit();
            db.close();
        }
    }

    public void deleteNonUniqueIndex(Record record, String databaseName) {
        for (Map.Entry<Attribute, String> entry : record.getForeignKeys().entrySet()) {
            DB db = getDb(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + entry.getKey().getIndexName());
            BTreeMap<String, List<String>> map = db.getTreeMap(entry.getKey().getIndexName());
            map.remove(record.getStoreKey());
            db.commit();
            db.close();
        }
    }

    public boolean hasChildren(Record record, String databaseName, Table table) {
        for (Table otherTable : DatabaseService.getInstance().getTables(databaseName)) {
            for (ForeignKey foreignKey : otherTable.getForeignKeys()) {
                if(foreignKey.getRefTable().equals(table.getName())) {
                    Attribute referencedAttribute = table.getAttribute(foreignKey.getReferences().get(0));
                    Attribute attribute = otherTable.getAttribute(foreignKey.getNames().get(0));
                    String indexName = attribute.getIndexName();
                    DB db = getDb(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + indexName);
                    BTreeMap<String, List<String>> map = db.getTreeMap(indexName);
                    if(map.containsKey(record.getValue(referencedAttribute)))
                        return true;
                }
            }
        }
        return false;
    }

    public void delete(Record record, String databaseName, String tableName) {
        File tableFile = new File(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + tableName + ".kv");
        DB db = DBMaker.newFileDB(tableFile).make();
        BTreeMap<String, String> map = db.getTreeMap(tableName);
        map.remove(record.getStoreKey());
        db.commit();
        db.close();
        deleteNonUniqueIndex(record, databaseName);
        deleteUniqueIndex(record, databaseName);
    }

    public Map<String, String> getRecords(String databaseName, String tableName) {
        File tableFile = new File(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + tableName + ".kv");
        DB db = DBMaker.newFileDB(tableFile).make();
        BTreeMap<String, String> map = db.getTreeMap(tableName);
        return map;
    }
}

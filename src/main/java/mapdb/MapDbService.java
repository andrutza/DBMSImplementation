package mapdb;

import model.Record;
import org.mapdb.BTreeMap;
import org.mapdb.DB;
import org.mapdb.DBMaker;
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

    public Record insert(Record record, String databaseName, String tableName) {
        File tableFile = new File(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + tableName + ".kv");
        DB db = DBMaker.newFileDB(tableFile).make();
        BTreeMap<String, String> map = db.getTreeMap(tableName);
        map.put(record.getStoreKey(), record.getStoreValue());
        db.commit();
        db.close();

        return record;
    }

    public void delete(Record record, String databaseName, String tableName) {
        File tableFile = new File(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + tableName + ".kv");
        DB db = DBMaker.newFileDB(tableFile).make();
        BTreeMap<String, String> map = db.getTreeMap(tableName);
        map.remove(record.getStoreKey());
        db.commit();
        db.close();
    }

    public Map<String, String> getRecords(String databaseName, String tableName) {
        File tableFile = new File(fileUtils.getDatabasesRoot() + "/" + databaseName + "/" + tableName + ".kv");
        DB db = DBMaker.newFileDB(tableFile).make();
        BTreeMap<String, String> map = db.getTreeMap(tableName);
        return map;
    }
}

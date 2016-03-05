package xml;

import model.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;
import org.jdom2.Element;
import utils.FileUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {

    private List<Database> databases;
    private final FileUtils fileUtils;

    public XMLReader() {
        fileUtils = new FileUtils();
        SAXBuilder builder = new SAXBuilder();
        try {
            Document document = builder.build(fileUtils.getDatabasesInputStream());
            Element rootNode = document.getRootElement();
            readDatabases(rootNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Database> getDatabases() {
        return databases;
    }

    private void readDatabases(Element rootNode) {
        List<Element> databases = rootNode.getChildren("DataBase");
        this.databases = new ArrayList<>();
        for(Element dbTag : databases){
            String name = dbTag.getAttributeValue("dataBaseName");
            fileUtils.createDirectory(name);
            Database database = new Database(name);
            loadDatabaseTables(dbTag, database);
            this.databases.add(database);
        }
    }

    private void loadDatabaseTables(Element databaseTag, Database database) {
        List<Element> tablesTag = databaseTag.getChildren("Table");
        for(Element tableTag : tablesTag){
            Table table = new Table(tableTag.getAttributeValue("tableName"),tableTag.getAttributeValue("fileName"));
            database.addTable(table);
            loadAttributes(tableTag, table);
            loadPrimaryKeys(tableTag, table);
            loadForeignKeys(database, tableTag, table);
            loadUniqueKeys(tableTag, table);
            loadIndexes(tableTag, table);
        }
    }

    private void loadIndexes(Element tableTag, Table table) {
        Element indexFilesTag = tableTag.getChild("IndexFiles");
        if(indexFilesTag != null){
            List<Element> indexFiles = indexFilesTag.getChildren("IndexFile");
            for(Element indexFileTag : indexFiles){
                Index i = new Index(indexFileTag.getAttributeValue("indexName"));
                Element indexAttributeTag = indexFileTag.getChild("IndexAttributes");
                List<Element> indexAttributes = indexAttributeTag.getChildren("IAttribute");
                for(Element indexAttribute :indexAttributes){
                    i.addIndexAttribute(table.getAttribute(indexAttribute.getText()));
                }
                table.addIndex(i);
            }
        }
    }

    private void loadUniqueKeys(Element tableTag, Table table) {
        Element uniqueKeyTag = tableTag.getChild("uniqueKeys");
        if(uniqueKeyTag != null){
            List<Element> uniqueKeys = uniqueKeyTag.getChildren("UniqueAttribute");
            for(Element uniqueKey : uniqueKeys){
                table.makeAttributeUniqueKey(uniqueKey.getText());
            }
        }
    }

    private void loadForeignKeys(Database database, Element tableTag, Table table) {
        Element foreignKeysTag = tableTag.getChild("foreignKeys");
        if(foreignKeysTag!=null){
            List<Element> foreignKeys = foreignKeysTag.getChildren("foreignKey");
            for(Element foreignKeyTag : foreignKeys){
                ForeignKey foreignKey = new ForeignKey();
                List<Element> fkAttributes = foreignKeyTag.getChildren("fkAttribute");
                for(Element fkAttribute : fkAttributes){
                    foreignKey.addName(fkAttribute.getText());
                }
                Element fkReference = foreignKeyTag.getChild("references");
                String fkRefTable = fkReference.getChildText("refTable");
                foreignKey.setRefTable(fkRefTable);
                List<Element> fkReferenceAttributes = fkReference.getChildren("refAttribute");
                for(Element fkReferenceAttribute : fkReferenceAttributes){
                    foreignKey.addAttribute(database.getTable(fkRefTable).getAttribute(fkReferenceAttribute.getText()));
                }
                table.addForeignKey(foreignKey);
            }
        }
    }

    private void loadPrimaryKeys(Element tableTag, Table table) {
        Element primaryKeyTag = tableTag.getChild("primaryKey");
        List<Element> primaryKeys = primaryKeyTag.getChildren("pkAttribute");
        for(Element primaryKey : primaryKeys){
            table.makeAttributePrimaryKey(primaryKey.getText());
        }
    }

    private void loadAttributes(Element tableTag, Table table) {
        Element structureTag = tableTag.getChild("Structure");
        List<Element> attributes = structureTag.getChildren("Attribute");
        for(Element attribute:attributes){
            table.addAttribute(new Attribute(attribute.getAttributeValue("attributeName"),attribute.getAttributeValue("type")));
        }
    }
}

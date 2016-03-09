package xml;

import model.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import utils.FileUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class XMLWriter {

    private final XMLOutputter xmlOutputter;
    private final FileUtils fileUtils;

    public XMLWriter(){
        xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        fileUtils = new FileUtils();
    }

    public void writeDatabases(List<Database> databases) {
        Element rootElement = new Element("Databases");
        createXMLStructure(databases, rootElement);
        writeDatabasesToXML(rootElement);
    }

    private void writeDatabasesToXML(Element rootElement) {
        Document doc = new Document(rootElement);
        try {
            xmlOutputter.output(doc, fileUtils.getDatabasesOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createXMLStructure(List<Database> databases, Element rootElement) {
        for(Database database:databases){
            Element databaseTag = new Element("DataBase");
            databaseTag.setAttribute("dataBaseName", database.getName());
            rootElement.addContent(databaseTag);
            createTableTag(database, databaseTag);
        }
    }

    private void createTableTag(Database database, Element databaseTag) {
        for(Table table : database.getTables()){
            Element tableTag = new Element("Table");
            tableTag.setAttribute("tableName",table.getName());
            tableTag.setAttribute("fileName",table.getFileName());
            databaseTag.addContent(tableTag);
            createTableStructureTag(table, tableTag);
            createPrimaryKeysTag(table, tableTag);
            createForeignKeysTag(table, tableTag);
            createUniqueKeysTag(table, tableTag);
            createIndexesTag(table, tableTag);
        }
    }

    private void createIndexesTag(Table table, Element tableTag) {
        List<Index> indexes = table.getIndexes();
        if(indexes.size()>0){
            Element indexFiles = new Element("IndexFiles");
            tableTag.addContent(indexFiles);
            for(Index i : indexes){
                Element indexFile = new Element("IndexFile");
                indexFile.setAttribute("indexName",i.getName());
                indexFiles.addContent(indexFile);
                Element indexAttributes = new Element("IndexAttributes");
                indexFile.addContent(indexAttributes);
                for(String a : i.getIndexAttributes()){
                    Element indexAttribute = new Element("IAttribute");
                    indexAttribute.setText(a);
                    indexAttributes.addContent(indexAttribute);
                }
            }
        }
    }

    private void createUniqueKeysTag(Table table, Element tableTag) {
        Element uniqueKey = new Element("uniqueKeys");
        tableTag.addContent(uniqueKey);
        for(Attribute keys : table.getUniqueKeys()){
            Element newAttribute = new Element("UniqueAttribute");
            newAttribute.setText(keys.getName());
            uniqueKey.addContent(newAttribute);
        }
    }

    private void createForeignKeysTag(Table table, Element tableTag) {
        List<ForeignKey> foreignKeys = table.getForeignKeys();
        if(foreignKeys.size()>0){
            Element foreignKey = new Element("foreignKeys");
            tableTag.addContent(foreignKey);
            for(ForeignKey key : foreignKeys){
                Element fKey = new Element("foreignKey");
                foreignKey.addContent(fKey);
                for(String name :key.getNames()){
                    Element fkAttribute = new Element("fkAttribute");
                    fkAttribute.setText(name);
                    fKey.addContent(fkAttribute);
                }
                Element referencesTag = new Element("references");
                fKey.addContent(referencesTag);
                Element refTable = new Element("refTable");
                refTable.setText(key.getRefTable());
                referencesTag.addContent(refTable);
                for(String attr : key.getReferences()){
                    Element refAttr = new Element("refAttribute");
                    refAttr.setText(attr);
                    referencesTag.addContent(refAttr);
                }
            }
        }
    }

    private void createPrimaryKeysTag(Table table, Element tableTag) {
        Element primaryKey = new Element("primaryKey");
        tableTag.addContent(primaryKey);
        for(Attribute keys : table.getPrimaryKeys()){
            Element newAttribute = new Element("pkAttribute");
            newAttribute.setText(keys.getName());
            primaryKey.addContent(newAttribute);
        }
    }

    private void createTableStructureTag(Table table, Element tableTag) {
        Element structure = new Element("Structure");
        tableTag.addContent(structure);
        for(Attribute attribute : table.getAttributes()){
            Element newAttribute = new Element("Attribute");
            newAttribute.setAttribute("attributeName",attribute.getName());
            newAttribute.setAttribute("type",attribute.getType());
            if(attribute.getLength() != null) {
                newAttribute.setAttribute("length",""+attribute.getLength());
            }
            newAttribute.setAttribute("isNull",""+attribute.isCanBeNull());
            structure.addContent(newAttribute);
        }
    }
}

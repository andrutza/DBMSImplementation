package xml;

import model.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;
import org.jdom2.Element;
import utils.DbUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 29-02-16.
 */
public class Reader {

    private List<Database> databases;

    public Reader() {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File("file.xml");
        try {
            Document document = (Document) builder.build(xmlFile);
            Element rootNode = document.getRootElement();
            readDatabases(rootNode);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Database> getDatabases() {
        return databases;
    }

    @SuppressWarnings("unchecked")
    private void readDatabases(Element rootNode) {
        List<Element> databases = rootNode.getChildren("DataBase");
        this.databases = new ArrayList<Database>();
        for(Element dbTag : databases){
            String name = dbTag.getAttributeValue("dataBaseName");
            DbUtils.createDirectory(name);
            Database newDb = new Database(name);
            getDatabaseTables(dbTag, newDb);
            this.databases.add(newDb);
        }
    }

    @SuppressWarnings("unchecked")
    private void getDatabaseTables(Element dbTag, Database newDb) {
        List<Element> tablesTag = dbTag.getChildren("Table");
        for(Element tableTag : tablesTag){
            Table newTable = new Table(tableTag.getAttributeValue("tableName"),tableTag.getAttributeValue("fileName"));
            newDb.addTable(newTable);

            //Structure
            Element structureTag = tableTag.getChild("Structure");
            List<Element> attributes = structureTag.getChildren("Attribute");
            for(Element attribute:attributes){
                newTable.addAttribute(new Attribute(attribute.getAttributeValue("attributeName"),attribute.getAttributeValue("type")));
            }

            //primary key
            Element pk = tableTag.getChild("primaryKey");
            List<Element> primaryKeys = pk.getChildren("pkAttribute");
            for(Element prk : primaryKeys){
                newTable.addPrimaryKey(newTable.getAtribute(prk.getText()));
            }

            //foreign key
            Element fk = tableTag.getChild("foreignKeys");
            if(fk!=null){
                List<Element> foreignKeys = fk.getChildren("foreignKey");
                for(Element foreign : foreignKeys){
                    ForeignKey newForeign = new ForeignKey();
                    List<Element> fkAttributes = foreign.getChildren("fkAttribute");
                    for(Element fkAttribute : fkAttributes){
                        newForeign.addName(fkAttribute.getText());
                    }
                    Element fkReference = foreign.getChild("references");
                    String fkRefTable = fkReference.getChildText("refTable");
                    newForeign.setRefTable(fkRefTable);
                    List<Element> fkReferenceAttributes = fkReference.getChildren("refAttribute");
                    for(Element fkReferenceAttribute : fkReferenceAttributes){
                        newForeign.addAttribute(newDb.getTable(fkRefTable).getAtribute(fkReferenceAttribute.getText()));
                    }
                    newTable.addForeignKey(newForeign);
                }
            }

            //unique key
            Element uniqueKeyTag = tableTag.getChild("uniqueKeys");
            if(uniqueKeyTag != null){
                List<Element> uniqueKeys = uniqueKeyTag.getChildren("UniqueAttribute");
                for(Element uk : uniqueKeys){
                    newTable.addUniqueKey(newTable.getAtribute(uk.getText()));
                }
            }

            //indexes
            Element indexfilesTag = tableTag.getChild("IndexFiles");
            if(indexfilesTag != null){
                List<Element> indexFiles = indexfilesTag.getChildren("IndexFile");
                for(Element indexfile : indexFiles){
                    Index i = new Index(indexfile.getAttributeValue("indexName"));
                    Element indexAttributeTag = indexfile.getChild("IndexAttributes");
                    List<Element> indexAttributes = indexAttributeTag.getChildren("IAttribute");
                    for(Element indexAttribute :indexAttributes){
                        i.addIndexAttribute(newTable.getAtribute(indexAttribute.getText()));
                    }
                    newTable.addIndex(i);
                }
            }


        }
    }
}

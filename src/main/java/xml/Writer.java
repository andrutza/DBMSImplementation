package xml;

import model.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 29-02-16.
 */
public class Writer {

    public Writer(List<Database> databases){
        Element rootElement = new Element("Databases");
        Document doc = new Document(rootElement);
        //doc.setRootElement(rootElement);
        writeDatabases(databases, rootElement);

        XMLOutputter xmlOutput = new XMLOutputter();

        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
            xmlOutput.output(doc, new FileWriter("file.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void writeDatabases(List<Database> dbs, Element rootElement) {
        for(Database db:dbs){
            Element newdb = new Element("DataBase");
            newdb.setAttribute("dataBaseName", db.getName());
            rootElement.addContent(newdb);
            for(Table table : db.getTables()){
                Element newTable = new Element("Table");
                newTable.setAttribute("tableName",table.getName());
                newTable.setAttribute("fileName",table.getFileName());
                newdb.addContent(newTable);

                //structure
                Element structure = new Element("Structure");
                newTable.addContent(structure);
                for(Attribute attribute : table.getAttributes()){
                    Element newAttribute = new Element("Attribute");
                    newAttribute.setAttribute("attributeName",attribute.getName());
                    newAttribute.setAttribute("type",attribute.getType());
                    structure.addContent(newAttribute);
                }

                //primaryKey
                Element primaryKey = new Element("primaryKey");
                newTable.addContent(primaryKey);
                for(Attribute keys : table.getPrimaryKeys()){
                    Element newAttribute = new Element("pkAttribute");
                    newAttribute.setText(keys.getName());
                    primaryKey.addContent(newAttribute);
                }

                //foreignKey
                List<ForeignKey> lista = table.getForeignKeys();
                if(lista.size()>0){
                    Element foreignKey = new Element("foreignKeys");
                    newTable.addContent(foreignKey);
                    for(ForeignKey key : lista){
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

                        for(Attribute attr : key.getReferences()){
                            Element refAttr = new Element("refAttribute");
                            refAttr.setText(attr.getName());
                            referencesTag.addContent(refAttr);
                        }


                    }
                }

                //uniqueKey
                Element uniqueKey = new Element("uniqueKeys");
                newTable.addContent(uniqueKey);
                for(Attribute keys : table.getUniqueKeys()){
                    Element newAttribute = new Element("UniqueAttribute");
                    newAttribute.setText(keys.getName());
                    uniqueKey.addContent(newAttribute);
                }

                //indexes
                List<Index> indexes = table.getIndexes();
                if(indexes.size()>0){

                    Element indexFiles = new Element("IndexFiles");

                    newTable.addContent(indexFiles);
                    for(Index i : indexes){
                        Element indexFile = new Element("IndexFile");
                        indexFile.setAttribute("indexName",i.getName());
                        indexFiles.addContent(indexFile);
                        Element indexAttributes = new Element("IndexAttributes");
                        indexFile.addContent(indexAttributes);
                        for(Attribute a : i.getIndexAttributes()){
                            Element indexAttribute = new Element("IAttribute");
                            indexAttribute.setText(a.getName());
                            indexAttributes.addContent(indexAttribute);
                        }

                    }
                }
            }
        }
    }
}

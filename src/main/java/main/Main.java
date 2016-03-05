package main;

import model.Database;
import xml.Reader;

import java.util.List;

/**
 * Created by User on 01-03-16.
 */
public class Main {

    public static void main(String args[]) {
        Reader reader = new Reader();
        List<Database> databases = reader.getDatabases();
        System.out.println(databases.size());
    }
}

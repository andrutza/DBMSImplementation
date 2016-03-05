package utils;

import java.io.*;
import java.util.Properties;

public class FileUtils {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(FileUtils.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteDirectory(File dir) {
        if (!dir.exists() || !dir.isDirectory()) {
            return false;
        }
        String[] files = dir.list();
        for (String file : files) {
            File f = new File(dir, file);
            if (f.isDirectory()) {
                deleteDirectory(f);
            } else {
                f.delete();
            }
        }
        return dir.delete();
    }

    public boolean createDirectory(String name) {
        File dir = new File(Constants.DB_ROOT + name);
        return dir.exists() || new File(Constants.DB_ROOT + name).mkdir();
    }

    public InputStream getDatabasesInputStream() throws FileNotFoundException {
        return new FileInputStream(properties.getProperty("databasesXML.location"));
    }

    public OutputStream getDatabasesOutputStream() throws FileNotFoundException {
        return new FileOutputStream(properties.getProperty("databasesXML.location"));
    }
}

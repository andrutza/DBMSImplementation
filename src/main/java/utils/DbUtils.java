package utils;

import java.io.File;

/**
 * Created by User on 29-02-16.
 */
public class DbUtils {
    /**
     * Deletes a directory recursively deleting anything inside it.
     *
     * @param dir
     *            The directory to delete
     * @return true if the directory was successfully deleted
     */
    public static boolean deleteDirectory(File dir) {
        if (!dir.exists() || !dir.isDirectory()) {
            return false;
        }

        String[] files = dir.list();
        for (int i = 0, len = files.length; i < len; i++) {
            File f = new File(dir, files[i]);
            if (f.isDirectory()) {
                deleteDirectory(f);
            } else {
                f.delete();
            }
        }
        return dir.delete();
    }

    public static boolean createDirectory(String name){

        File dir = new File(Constants.DB_ROOT + name);
        if(!dir.exists()){
            return new File(Constants.DB_ROOT + name).mkdir();
        }
        return true;
    }
}

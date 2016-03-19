import model.Record;
import service.DatabaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 16-03-16.
 */
public class Main {

    public static void main(String args[]) {
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("2");
//        strings.add("john");
//        DatabaseService.getInstance().addRecord("animalworld", "category", strings);
        List<Record> records = DatabaseService.getInstance().getRecords("animalworld", "category");
    }
}

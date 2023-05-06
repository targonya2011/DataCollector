package targonya.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.*;

public class ParseCsv {

    public Map<String, String> parse(String pathCsv) {
        File file = new File(pathCsv);
        Map<String, String> dates = new HashMap<>();
        try{
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().split(",");
                dates.put(split[0], split[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dates;
    }

}

package targonya.search;

import java.io.File;
import java.util.ArrayList;

public class SearchFile {
    public void searchFormat(File rootfile, ArrayList<File> fileList, String typeSearch) {
        if (rootfile.isDirectory()) {
            File[] directoryFiles = rootfile.listFiles();
            if (directoryFiles != null) {
                for (File file : directoryFiles) {
                    if (file.isDirectory()) {
                        searchFormat(file, fileList, typeSearch);
                    } else {
                        if (file.getName().endsWith(typeSearch)) {
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }
}

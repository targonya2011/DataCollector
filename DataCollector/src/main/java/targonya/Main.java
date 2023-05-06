package targonya;



import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import targonya.creatingJsonFirst.StationCreating;
import targonya.creatingJsonFirst.StationList;
import targonya.creatingJsonSecond.StationsSec;
import targonya.line.Line;
import targonya.parse.ParseCsv;
import targonya.parse.ParseJson;
import targonya.parse.ParsingWeb;
import targonya.parse.StationJson;
import targonya.search.SearchFile;
import targonya.search.UnzipFiles;
import targonya.station.Station;

import java.io.*;

import java.lang.reflect.Type;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        createJsonSecond();



    }


    public static void parseWeb() throws IOException {
        Document document = Jsoup.connect("https://skillbox-java.github.io/").get();
        ParsingWeb parsingWeb = new ParsingWeb();
        List<Line> lines = parsingWeb.parseLines(document);
        List<Station> stations = parsingWeb.parseStations(document);
        lines.forEach(System.out::println);
        stations.forEach(System.out::println);
    }
    public static void unZipFiles() throws IOException {
        String fileZip = "resources/stations-data.zip";
        File destDir = new File("resources/");
        UnzipFiles unzipFiles = new UnzipFiles();
        unzipFiles.unZip(fileZip, destDir);
    }
    public static void searchFiles() {
        ArrayList<File> jsonList = new ArrayList<>();
        ArrayList<File> csvList = new ArrayList<>();
        SearchFile searchFile = new SearchFile();
        searchFile.searchFormat(new File("resources/data"), jsonList, ".json");
        searchFile.searchFormat(new File("resources/data"), csvList, ".csv");
        System.out.println("JSON FILES" + "\n");
        for (File file : jsonList) {
            System.out.println(file.getAbsolutePath() + "\n");
        }
        System.out.println("CSV FILES" + "\n");
        for (File file : csvList) {
            System.out.println(file.getAbsolutePath() + "\n");
        }
    }

    public static Map<Object, Object> parseJsonWithSimple(String path) {
        ParseJson parseJson = new ParseJson();
        try {
            Map<Object, Object> parse = parseJson.parse(path);
            return parseJson.parse(path);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

        public static List<StationJson> parseJsonWithGson () throws FileNotFoundException {
            String path1 = "resources/data/2/4/depths-1.json";
            Type collectionType = new TypeToken<List<StationJson>>() {
            }.getType();
            List<StationJson> lcs = new Gson().fromJson(new FileReader(path1), collectionType);
            for (StationJson lc : lcs) {
                System.out.println("Станция: " + lc.getStation_name() + " Глубина " + lc.getDepth());
            }
            return lcs;
        }

        public static Map<String, String> parseCsv (String pathCsv) {
            ParseCsv parseCsv = new ParseCsv();
            Map<String, String> parse = parseCsv.parse(pathCsv);
//            parse.entrySet().forEach(entry -> System.out.println("Станция: " + entry.getKey() + " Дата открытия: " + entry.getValue()));
            return parse;
    }

        public static Map<String, String> createJsonFirst() throws IOException {
            Document document = Jsoup.connect("https://skillbox-java.github.io/").get();
            ParsingWeb parsingWeb = new ParsingWeb();
            parsingWeb.getAllConnections(document);

            Map<String, String> l = parsingWeb.getAllStationOnLine(document);
            Map <StationCreating, String> m = parsingWeb.getAllConnections(document);
            StationList stationList = new StationList(l, m);
            toJSONFirst(stationList);
            return l;
        }
        public static void toJSONFirst (StationList stationList) throws IOException {
            String pathJson = "resources/depths1.json";
            ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();
            mapper.writeValue(new File(pathJson), stationList);
            System.out.println("json created!");
        }
    public static List<StationsSec> SEC = new ArrayList<>();

        public static void createJsonSecond() throws IOException, ParseException {
            String json1 = "resources/data/2/4/depths-1.json";
            String json2 = "resources/data/7/1/depths-2.json";
            String json3 = "resources/data/4/6/depths-3.json";
            createJsFromJs(json1);
            createJsFromJs(json2);
            createJsFromJs(json3);
            String csv1 = "resources/data/4/6/dates-1.csv";
            String csv2 = "resources/data/0/5/dates-2.csv";
            String csv3 = "resources/data/9/6/dates-3.csv";
            createJsFromCsv(csv1);
            createJsFromCsv(csv2);
            createJsFromCsv(csv3);
            setLine();
            setConnections();
            createJsonFile();
        }

    public static void createJsFromCsv (String path) {
        Map<String, String> map = parseCsv(path);
        map.entrySet().stream().forEach(s ->{
            for (StationsSec stationsSec : SEC) {
                if (stationsSec.getName().equals(s.getKey())) {
                    stationsSec.setDate(s.getValue());
                }
            }
        } );
    }
    public static void createJsFromJs (String path) {
        Map<Object, Object> objectMap = parseJsonWithSimple(path);
        objectMap.entrySet().stream().forEach(s -> {
            StationsSec st = new StationsSec();
            st.setName(s.getKey().toString());
            st.setDepth(s.getValue().toString());
            SEC.add(st);
        });
    }
    public static void setLine() throws IOException {
        Map<String, String> jsonFirst = createJsonFirst();
        jsonFirst.entrySet().stream().forEach(s -> {
            for (StationsSec sec : SEC) {
                String value = s.getValue();
                String name = sec.getName();
                if (value.contains(name)) {
                    sec.setLine(s.getKey());
                }
            }
        });
    }
    public static void createJsonFile() throws IOException {
        String pathJson = "resources/depthsResult.json";
        ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();
        mapper.writeValue(new File(pathJson), SEC);
        System.out.println("json created!");
    }
    public static void setConnections() throws IOException {
        Document document = Jsoup.connect("https://skillbox-java.github.io/").get();
        ParsingWeb parsingWeb = new ParsingWeb();
        Map<StationCreating, String> allConnections = parsingWeb.getAllConnections(document);

        allConnections.entrySet().stream().forEach(s-> {
            for (StationsSec sec : SEC) {
                if (s.getKey().getName().equals(sec.getName())) {
                    sec.setHasConnection(true);
                }
            }
        });

    }
    }




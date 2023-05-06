package targonya.parse;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import targonya.creatingJsonFirst.StationCreating;
import targonya.line.Line;
import targonya.station.Station;

import java.util.*;
import java.util.stream.Collectors;

public class ParsingWeb {
     public List<Line> parseLines(Document document) {
        Elements elements = document.getElementsByClass("t-metrostation-list-header");
        List<Line> lines = new ArrayList<>();
        for (Element element : elements) {
            lines.add(new Line(element.text(), element.attr("data-line")));
        }
        return lines;
    }
    public List<Station> parseStations(Document document) {
        Elements elements = document.getElementsByClass("single-station");
        List <Station> stations = new ArrayList<>();
        for (Element element : elements) {
            stations.add(new Station(element.getElementsByClass("num").text(),
                    element.getElementsByClass("name").text()));
        }
        return stations;
    }
    public Map <String, String> getAllStationOnLine (Document document) {
        Elements data = document.select("div.js-metro-stations");
        Map <String, String> map = new LinkedHashMap<>();
        Set<String> numberLine = parseLines(document).stream().map(Line::getNumber).collect(Collectors.toSet());
        for (int i = 0; i < data.size(); i++) {
            for (String s : numberLine) {
                if (data.get(i).attr("data-line").equals(s)) {
                    map.put(s, data.get(i).text());
                }
            }
        }
             return map;
    }

    public Map<StationCreating, String> getAllConnections(Document document) {

         Map<StationCreating, String> map = new LinkedHashMap<>();
        Elements sel = document.select("p.single-station > span.t-icon-metroln");
        for (int i = 0; i < sel.size(); i++) {
            String title = sel.get(i).attr("title");
            Element parentConnection = sel.get(i).parent();
            String nameConnection = parentConnection.select("span.name").text();
            String attr = parentConnection.parent().select("div.js-metro-stations").attr("data-line");
            map.put(new StationCreating(nameConnection, title, attr), "number connection" + " " + String.valueOf(i + 1));
        }
        return map;
    }


}

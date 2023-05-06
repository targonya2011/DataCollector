package targonya.creatingJsonFirst;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Objects;

public class StationList {
    @JsonProperty("stations")
    private Map<String, String> stationsList;
    @JsonProperty("connection")
    private Map<StationCreating, String> connectionsList;

    public StationList(Map<String, String> stationsList, Map<StationCreating, String> connectionsList) {
        this.stationsList = stationsList;
        this.connectionsList = connectionsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationList that = (StationList) o;
        return Objects.equals(stationsList, that.stationsList) && Objects.equals(connectionsList, that.connectionsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationsList, connectionsList);
    }

    @Override
    public String toString() {
        return "StationList{" +
                "stationsList=" + stationsList +
                ", connectionsList=" + connectionsList +
                '}';
    }
}




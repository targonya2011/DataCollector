package targonya.creatingJsonSecond;

import java.util.Objects;

public class StationsSec {
    private String name;
    private String line;
    private String date;
    private String depth;
    private boolean hasConnection;

    public StationsSec(String name, String line, String date, String depth, boolean hasConnection) {
        this.name = name;
        this.line = line;
        this.date = date;
        this.depth = depth;
        this.hasConnection = hasConnection;
    }

    public StationsSec() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public boolean isHasConnection() {
        return hasConnection;
    }

    public void setHasConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
        return "StationsSec{" +
                "name='" + name + '\'' +
                ", line='" + line + '\'' +
                ", date='" + date + '\'' +
                ", depth='" + depth + '\'' +
                ", hasConnection=" + hasConnection +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationsSec that = (StationsSec) o;
        return hasConnection == that.hasConnection && Objects.equals(name, that.name) && Objects.equals(line, that.line) && Objects.equals(date, that.date) && Objects.equals(depth, that.depth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, line, date, depth, hasConnection);
    }
}

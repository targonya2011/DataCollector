package targonya.creatingJsonFirst;

import java.util.Objects;
public class StationCreating {
    private String name;
    private String connection;

    private String line;

    public StationCreating(String name, String connection, String line) {
        this.name = name;
        this.connection = connection;
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "StationCreating{" +
                "name='" + name + '\'' +
                ", connection='" + connection + '\'' +
                ", line='" + line + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationCreating that = (StationCreating) o;
        return Objects.equals(name, that.name) && Objects.equals(connection, that.connection) && Objects.equals(line, that.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, connection, line);
    }
}

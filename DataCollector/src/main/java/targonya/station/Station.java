package targonya.station;


import java.util.Objects;

public class Station {
        private String number; // номер линии
        private String name;  // Название станции
        private String line;  // Название линии
        private String date; //Дата открытия в формате 19.01.2005

        private int depth;
        private boolean hasConnection;

        public Station(String number, String name) {
                this.name = name;
                this.number = number;
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

        public String getNumber() {
                return number;
        }

        public void setNumber(String number) {
                this.number = number;
        }

        public int getDepth() {
                return depth;
        }

        public void setDepth(int depth) {
                this.depth = depth;
        }

        public boolean isHasConnection() {
                return hasConnection;
        }

        public void setHasConnection(boolean hasConnection) {
                this.hasConnection = hasConnection;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Station station = (Station) o;
                return depth == station.depth && hasConnection == station.hasConnection && Objects.equals(name, station.name) && Objects.equals(line, station.line) && Objects.equals(date, station.date) && Objects.equals(number, station.number);
        }

        @Override
        public int hashCode() {
                return Objects.hash(name, line, date, number, depth, hasConnection);
        }

        @Override
        public String toString() {
                return "Station{" +
                        "number='" + number + '\'' +
                        ", name='" + name + '\'' +
                        '}';
        }
}

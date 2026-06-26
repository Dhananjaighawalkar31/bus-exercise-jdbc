import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/BusExcercise";
    static private final String username = "root";
    private static final String password = "6309744577";
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement stmt = connection.createStatement();
            String busTable = "CREATE TABLE bus (\n" +
                    "    bus_id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    bus_number VARCHAR(20) UNIQUE NOT NULL,\n" +
                    "    starting_point VARCHAR(50) NOT NULL,\n" +
                    "    ending_point VARCHAR(50) NOT NULL,\n" +
                    "    travel_date DATE NOT NULL,\n" +
                    "    capacity INT NOT NULL,\n" +
                    "    available_seats INT NOT NULL\n" +
                    ");";
            stmt.executeUpdate(busTable);
            String bookingTable = "CREATE TABLE booking (\n" +
                    "    passenger_id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    passenger_name VARCHAR(50) NOT NULL,\n" +
                    "    passenger_age INT NOT NULL,\n" +
                    "    boarding_point VARCHAR(50) NOT NULL,\n" +
                    "    destination VARCHAR(50) NOT NULL,\n" +
                    "    date_booked DATE NOT NULL,\n" +
                    "    amount DECIMAL(10,2) NOT NULL,\n" +
                    "    bus_id INT NOT NULL,\n" +
                    "    FOREIGN KEY (bus_id) REFERENCES bus(bus_id)\n" +
                    ");";
            stmt.executeUpdate(bookingTable);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
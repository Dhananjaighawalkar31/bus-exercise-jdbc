import java.sql.*;
import java.util.Scanner;

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
            Scanner s = new Scanner(System.in);
            System.out.println("Give passenger name : ");
            String p_name = s.next();
            System.out.println("Give passenger age : ");
            int p_age = s.nextInt();
            System.out.println("Boarding point ?");
            String boarding_point = s.next();
            System.out.println("Destination : ");
            String destination = s.next();
            System.out.println("Date booked : ");
            String date_booked = s.next();
            System.out.println("Amount Paid : ");
            double amount = s.nextDouble();
            System.out.println("Provide Bus_ID : ");
            int busId = s.nextInt();
            String query = """
        INSERT INTO booking
        (passenger_name, passenger_age, boarding_point,
        destination, date_booked, amount, bus_id)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, p_name);
            ps.setInt(2, p_age);
            ps.setString(3, boarding_point);
            ps.setString(4, destination);
            ps.setDate(5, Date.valueOf(date_booked));
            ps.setDouble(6, amount);
            ps.setInt(7, busId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Booking Added Successfully.");
            } else {
                System.out.println("Booking Failed.");
            }

            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
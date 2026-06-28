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

            String query = "select * from booking join bus on booking.bus_id = bus.bus_id;";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("passenger_name"));
                System.out.println(rs.getInt("passenger_age"));
                System.out.println(rs.getString("bus_number"));
                System.out.println(rs.getString("destination"));
                System.out.println("------------");
            }
            ResultSetMetaData meta = rs.getMetaData();

            System.out.println(meta.getColumnCount());
            System.out.println(meta.getColumnTypeName(1));

            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Database {

    public static Map<String, String> getHashMap() {
        Map<String, String> hashMap = new HashMap<>();
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:language.db");


            Statement statement = connection.createStatement();


            ResultSet set = statement.executeQuery("SELECT * FROM lang");

            while (set.next()) {
                hashMap.put(set.getString("ru").toLowerCase(), set.getString("en").toLowerCase());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return hashMap;
    }
}
import java.sql.*;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DBConnection {
    private static Connection connection;
    private static StringBuilder insertQuery = new StringBuilder();
    private static ConcurrentLinkedQueue<String> insertQueue = new ConcurrentLinkedQueue<>();
    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "q1w2e3r4ty";


    public static ConcurrentLinkedQueue<String> getInsertQueue() {
        return insertQueue;
    }

    public static StringBuilder getInsertQuery() {
        return insertQuery;
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/learn?useSSL=false&serverTimezone=UTC", dbUser, dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id), " +
                        "UNIQUE KEY name_date(name(50), birthDate))");
                connection.createStatement().execute("SET GLOBAL max_allowed_packet=16777216");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate, `count`)" +
                "VALUES" + insertQuery.toString() +
                "ON DUPLICATE KEY UPDATE `count`=`count`+ 1";
        Connection connection = DBConnection.getConnection();
        connection.createStatement().execute(sql);
//        connection.close();

    }

    public static void insertToBD() throws SQLException {
        while (!insertQueue.isEmpty()) {
            insertQuery.append((insertQuery.length() == 0 ? "" : ",") + insertQueue.poll());
            if (insertQuery.length() > 3_000_000) {
                executeMultiInsert();
            insertQuery.delete(0, insertQuery.length());
            }
        }
        executeMultiInsert();

    }

    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        insertQueue.add("('" + name + "', '" + birthDay + "', 1)");
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        Connection connection = DBConnection.getConnection();
        ResultSet rs = connection.createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
        connection.close();
    }

    public static void closeConnection() throws SQLException {
        connection.close();
        connection = null;
    }
}

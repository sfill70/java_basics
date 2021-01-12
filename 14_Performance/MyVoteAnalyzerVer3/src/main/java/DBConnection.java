import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DBConnection implements Runnable {
    private Connection connection;
    private StringBuilder insertQuery = new StringBuilder();
    private List<String> insertList = new ArrayList<>();
    //    private static ConcurrentLinkedQueue<String> insertQueue = new ConcurrentLinkedQueue<>();
    private static ConcurrentLinkedQueue<String> insertQueue = new ConcurrentLinkedQueue<>();
    private String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "q1w2e3r4ty";

    public DBConnection() {
    }

    public static ConcurrentLinkedQueue<String> getInsertQueue() {
        return insertQueue;
    }

    public StringBuilder getInsertQuery() {
        return insertQuery;
    }

    public List<String> getInsertList() {
        return insertList;
    }

    public static void createDB() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/learn?useSSL=false&serverTimezone=UTC", dbUser, dbPass);) {
            connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
            connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "count INT NOT NULL, " +
                    "PRIMARY KEY(id)/*, " +
                    "UNIQUE KEY name_date(name(50), birthDate)*/)");
            connection.createStatement().execute("SET GLOBAL max_allowed_packet=16777216");
            connection.createStatement().execute("SET innodb_lock_wait_timeout=100");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/learn?useSSL=false&serverTimezone=UTC", dbUser, dbPass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void executeMultiInsert() throws SQLException, InterruptedException {

        String sql = "INSERT INTO voter_count(name, birthDate, count)" +
                "VALUES" + insertList.toString().substring(1,insertList.toString().length()-1);
        getConnection().createStatement().execute(sql);
    }

    public void insertToBD() throws SQLException, InterruptedException {
       /* while (!insertQueue.isEmpty()) {
            insertQuery.append((insertQuery.length() == 0 ? "" : ",") + insertQueue.poll());
            if (insertQuery.length() > 9_000_000) {
                executeMultiInsert();
                insertQuery.delete(0, insertQuery.length());
            }
        }*/
        while (!insertQueue.isEmpty()) {
            insertList.add(insertQueue.poll());
            if (insertList.size() > 250_000) {
                executeMultiInsert();
                insertList = new ArrayList<>();
            }
        }
        executeMultiInsert();
    }

    public static void countVoter(String name, String birthDay) throws SQLException {
        birthDay = birthDay.replace('.', '-');
        insertQueue.add("('" + name + "', '" + birthDay + "', 1)");

    }

    public void printVoterCounts() throws SQLException {
        String sql = "select name, birthDate, COUNT(*) as count_voter from voter_count GROUP BY name, birthDate HAVING count_voter > 1";
        ResultSet rs = getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count_voter"));
        }
    }

    public void updateDB() throws SQLException {
        String query = "DELETE voter_count FROM voter_count LEFT OUTER JOIN " +
                "(SELECT MIN(id) AS id, name, birthDate, COUNT(*) as count from voter_count GROUP BY name, birthDate) AS tmp ON \n" +
                "\tvoter_count.id = tmp.id WHERE voter_count.id IS NULL";
        getConnection().createStatement().execute(query);
    }

    /*public void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE count > 1";
        ResultSet rs = getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }*/

    public void closeConnection() throws SQLException {
        connection.close();
        connection = null;
    }

    @Override
    public void run() {
        try {
            insertToBD();
        } catch (SQLException | InterruptedException throwables) {
            throwables.printStackTrace();
        }

    }
}

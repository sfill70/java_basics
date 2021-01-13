import connectionDB.ExceptionConnectionMysql;
import connectionDB.MysqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InsertToDBRunnable implements Runnable {
    private ConcurrentLinkedQueue<String> insertQueue;
    private List<String> insertList = new ArrayList<>();

    public InsertToDBRunnable() {
        this.insertQueue = DBConnection.getInsertQueue();
    }

    private void executeMultiInsert(Statement statement) throws SQLException, InterruptedException {
        String sql = "INSERT INTO voter_count(name, birthDate, count)" +
                "VALUES" + insertList();
        statement.execute(sql);
    }

    private String insertList() {
        return insertList.toString().substring(1, insertList.toString().length() - 1);
    }

    public void insertToBD() {
        try (Connection connection = MysqlConnection.connectionMysql();
             Statement statement = connection.createStatement()) {
            while (!insertQueue.isEmpty()) {
                insertList.add(insertQueue.poll());
                if (insertList.size() > 250_000) {
                    executeMultiInsert(statement);
                    insertList = new ArrayList<>();
                }
            }
            executeMultiInsert(statement);
        } catch (SQLException | InterruptedException | ExceptionConnectionMysql throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void run() {
        insertToBD();
    }
}

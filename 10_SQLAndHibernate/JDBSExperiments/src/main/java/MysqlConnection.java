import java.sql.*;

public class MysqlConnection {
    static final String URL = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
//                    +"useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASSWORD = "q1w2e3r4ty";

    protected static Connection connectionMysql() throws ExceptionConnectionMysql {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e
        ) {
            throw new ExceptionConnectionMysql("Ошибка соеденения с базой данных");
        }
    }
}

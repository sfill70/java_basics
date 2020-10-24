import java.sql.*;

public class Main {

    public static void main(String[] args) {
        String query = "SELECT course_name,COUNT(subscription_date) / (1 + max(MONTH(subscription_date))-min(MONTH(subscription_date)))" +
                " AS AveragePurchase FROM PurchaseList GROUP BY course_name";
        try (Connection connection = MysqlConnection.connectionMysql();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + " - ");
                System.out.println(resultSet.getString(2));
            }
        } catch (NullPointerException ex) {
            System.out.println("Ошибка соеденения с базой данных");
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

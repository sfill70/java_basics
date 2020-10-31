import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String query = "SELECT course_name,COUNT(subscription_date) / (1 + max(MONTH(subscription_date))-min(MONTH(subscription_date)))" +
                " AS AveragePurchase FROM PurchaseList GROUP BY course_name";
        String query1 = "select st_id,id as id_course from (select student_name,course_name,id as st_id from purchaselist, " +
                "students where purchaselist.student_name = students.name)as tb, courses where tb.course_name = courses.name order by st_id";

        try (Connection connection = MysqlConnection.connectionMysql();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query1);
            while (resultSet.next()) {
                System.out.print(resultSet.getInt(1) + " - ");
                System.out.println(resultSet.getInt(2));
            }
        } catch (NullPointerException ex) {
            System.out.println("Ошибка соеденения с базой данных");
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

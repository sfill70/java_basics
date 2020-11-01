import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = MysqlConnection.connectionMysql();
             Statement statement = connection.createStatement()
        ) {
            statement.execute("CREATE TABLE IF NOT EXISTS LinkedPurchaseList(student_id INT, course_id INT, primary key(student_id, course_id))");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (SessionFactory sessionFactory = BuildSession.getSessionFactory();
             Session session = sessionFactory.openSession();
             Connection connection = MysqlConnection.connectionMysql();
             Statement statement = connection.createStatement()
        ) {
            String query = "SELECT st_id,id AS id_course From (SELECT student_name,course_name,id AS\n" +
                    "st_id FROM PurchaseList, Students WHERE PurchaseList.student_name = Students.name)as tb,Courses WHERE tb.course_name = Courses.name order by st_id";
            Transaction transaction = session.beginTransaction();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
                System.out.print(resultSet.getInt(1) + " - ");
                System.out.println(resultSet.getInt(2));
                LinkedPurchaseList.Id id = new LinkedPurchaseList.Id(resultSet.getInt(1), resultSet.getInt(2));
                linkedPurchaseList.setId(id);
                session.save(linkedPurchaseList);
            }
            transaction.commit();

        } catch (NullPointerException ex) {
            System.out.println("Ошибка соеденения с базой данных");
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

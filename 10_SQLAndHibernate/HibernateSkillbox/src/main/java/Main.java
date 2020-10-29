import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = BuildSession.getSessionFactory();
             Session session = sessionFactory.openSession();
             Connection connection = MysqlConnection.connectionMysql();
             Statement statement = connection.createStatement()
        ) {
            String query = "select st_id,id as id_course from (select student_name,course_name,id as st_id from purchaselist, " +
                    "students where purchaselist.student_name = students.name)as tb, " +
                    "courses where tb.course_name = courses.name order by st_id";
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

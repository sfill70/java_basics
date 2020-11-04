import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = MysqlConnection.connectionMysql();
             Statement statement = connection.createStatement()
        ) {
            statement.execute("CREATE TABLE IF NOT EXISTS LinkedPurchaseList(student_id INT, course_id INT, primary key(student_id, course_id))");
//            statement.execute("CREATE TABLE IF NOT EXISTS LinkedPurchaseList(student_id INT, course_id INT)");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (SessionFactory sessionFactory = BuildSession.getSessionFactory();
             Session session = sessionFactory.openSession();
             Connection connection = MysqlConnection.connectionMysql();
             Statement statement = connection.createStatement()
        ) {
            Transaction transaction = session.beginTransaction();
            List<PurchaseList> purchaseLists = session.createQuery("FROM PurchaseList", PurchaseList.class).getResultList();

            for (PurchaseList pl : purchaseLists
            ) {
                Student student = session.createQuery("FROM Student WHERE name = '" + pl.getStudentName() + "'", Student.class).getSingleResult();
                Course course = session.createQuery("FROM Course WHERE name = '" + pl.getCourseName() + "'", Course.class).getSingleResult();
                System.out.println(student + " - " + course);
                LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList(student, course);
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

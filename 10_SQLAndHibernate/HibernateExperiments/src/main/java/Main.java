import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = BuildSession.getSessionFactory()) {
            Session session = sessionFactory.openSession();
            Course course = session.get(Course.class, 3);
            System.out.printf("Курс: %s - Количество студентов: %s ", course.getName(), course.getStudentCount() + System.lineSeparator());
        } catch (NullPointerException ex) {
            System.out.println("Ошибка соеденения с базой данных");
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

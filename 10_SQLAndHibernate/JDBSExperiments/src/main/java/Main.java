import java.sql.*;

public class Main {

    public static void main(String[] args) {
       /*Напишите код, который выведет среднее количество покупок в месяц для каждого курса за
      2018 год. Учитывайте диапазон месяцев, в течение которого были продажи. Подробнее в примере.*/
        /*String query = "SELECT course_name,COUNT(subscription_date) / (1 + max(MONTH(subscription_date))-min(MONTH(subscription_date)))" +
                " AS AveragePurchase FROM PurchaseList GROUP BY course_name";*/

                                                   /* Домашняя работа 1.10*/

        /* Написать запрос для выбора студентов в порядке их регистрации на сервисе.*/
        String query1 = "select name, registration_date registration_date from Students order by registration_date";
        /* Написать запрос для выбора 5 самых дорогих курсов, на которых более 4 студентов, и которые длятся более 10 часов.*/
        String query2 = "select  name, price from courses where students_count > 4 and duration >10 order by price DESC LIMIT 5";
       /* Написать один (!) запрос, который выведет одновременно список из имен трёх самых молодых студентов, имен трёх
        самых старых учителей и названий трёх самых продолжительных курсов.*/
        String query3 = "(select Students.name from Students order by Students.age LIMIT 3) union " +
                "(select teachers.name from Teachers order by Teachers.age DESC LIMIT 3)" +
                "union (select Courses.name from Courses order by Courses.duration DESC LIMIT 3)";
        /*Написать запрос для выбора среднего возраста всех учителей с зарплатой более 10 000*/
        String query4 = "select avg(age) from Teachers where salary > 10000";
        /*Написать запрос для расчета суммы, сколько будет стоить купить все курсы по дизайну.*/
        String query5 = "select  sum(price) from Courses where type = 'DESIGN'";
        /*Написать запрос для расчёта, сколько минут (!) длятся все курсы по программированию*/
        String query6 = "select  sum(duration*60 ) from Courses where type = 'PROGRAMMING'";
        /*Напишите запрос, который выводит сумму, сколько часов должен в итоге проучиться каждый студент
        (сумма длительности всех курсов на которые он подписан).
          В результате запрос возвращает две колонки: Имя Студента — Количество часов.*/
        String query7 = "select name, durations from(select student_id, sum(duration) as durations from Subscriptions, " +
                "Courses where Subscriptions.course_id = Courses.id group by Subscriptions.student_id order by " +
                "Subscriptions.student_id) as tb, Students where student_id = id";
        /*Напишите запрос, который посчитает для каждого учителя средний возраст его учеников.
       В результате запрос возвращает две колонки: Имя Учителя — Средний Возраст Учеников.*/
        String query8 = "select teachers_name, avg(age) from (select teachers_name, student_id from " +
                "(select Teachers.name as teachers_name, Courses.id as courses_id from Teachers, " +
                "Courses where teachers.id = Courses.teacher_id order by Teachers.id) as tb, " +
                "subscriptions where tb.courses_id = Subscriptions.course_id) as tb2, Students where tb2.student_id = students.id " +
                "group by teachers_name";
        /*Напишите запрос, который выводит среднюю зарплату учителей для каждого типа курса (Дизайн/Программирование/Маркетинг и т.д.).
        В результате запрос возвращает две колонки: Тип Курса — Средняя зарплата.*/
        String query9 = "select type, avg(salary) from Courses, Teachers where Courses.teacher_id = Teachers.id group by Courses.type";

        /*String query10 = "select st_id,id as id_course from (select student_name,course_name,id as st_id from purchaselist, " +
                "students where purchaselist.student_name = students.name)as tb, courses where tb.course_name = courses.name order by st_id";*/

        try (Connection connection = MysqlConnection.connectionMysql();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = null;
            /*System.out.println(System.lineSeparator() + "//Напишите код, который выведет среднее количество покупок в месяц для каждого курса за \n" +
                    "//2018 год. Учитывайте диапазон месяцев, в течение которого были продажи. Подробнее в примере.");
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + " - ");
                System.out.println(resultSet.getString(2));
            }*/
            System.out.println(System.lineSeparator() + "Написать запрос для выбора студентов в порядке их регистрации на сервисе.");
            resultSet = statement.executeQuery(query1);
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + " - ");
                System.out.println(resultSet.getString(2));
            }
            System.out.println(System.lineSeparator() + "Написать запрос для выбора 5 самых дорогих курсов, на которых более 4 студентов, и которые длятся более 10 часов.");
            resultSet = statement.executeQuery(query2);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " - ");
            }
            System.out.println(System.lineSeparator() + "//Написать один (!) запрос, который выведет одновременно список из имен трёх самых молодых студентов, имен трёх\n" +
                    "//самых старых учителей и названий трёх самых продолжительных курсов.");
            resultSet = statement.executeQuery(query3);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " - ");
            }
            System.out.println(System.lineSeparator() + "Написать запрос для выбора среднего возраста всех учителей с зарплатой более 10 000");
            resultSet = statement.executeQuery(query4);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " - ");
            }
            System.out.println(System.lineSeparator() + "Написать запрос для расчета суммы, сколько будет стоить купить все курсы по дизайну.");
            resultSet = statement.executeQuery(query5);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " - ");
            }
            System.out.println(System.lineSeparator() + "Написать запрос для расчёта, сколько минут (!) длятся все курсы по программированию");
            resultSet = statement.executeQuery(query6);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " - ");
            }
            System.out.println(System.lineSeparator() + "//Напишите запрос, который выводит сумму, сколько часов должен в итоге проучиться каждый студент (сумма длительности всех курсов на которые он подписан).\n" +
                    "//В результате запрос возвращает две колонки: Имя Студента — Количество часов.");
            resultSet = statement.executeQuery(query7);
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + " - ");
                System.out.println(resultSet.getString(2));
            }
            System.out.println(System.lineSeparator() + "//Напишите запрос, который посчитает для каждого учителя средний возраст его учеников.\n" +
                    "//В результате запрос возвращает две колонки: Имя Учителя — Средний Возраст Учеников.");
            resultSet = statement.executeQuery(query8);
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + " - ");
                System.out.println(resultSet.getString(2));
            }
            System.out.println(System.lineSeparator() + "//Напишите запрос, который выводит среднюю зарплату учителей для каждого типа курса (Дизайн/Программирование/Маркетинг и т.д.).\n" +
                    "//В результате запрос возвращает две колонки: Тип Курса — Средняя зарплата.");
            resultSet = statement.executeQuery(query9);
            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + " - ");
                System.out.println(resultSet.getString(2));
            }

        } catch (ExceptionConnectionMysql ex) {
            ex.printStackTrace();
            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

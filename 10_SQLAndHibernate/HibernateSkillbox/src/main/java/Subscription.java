import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscription {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionsDate;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getSubscriptionsDate() {
        return subscriptionsDate;
    }

    public void setSubscriptionsDate(Date subscriptionsDate) {
        this.subscriptionsDate = subscriptionsDate;
    }
}

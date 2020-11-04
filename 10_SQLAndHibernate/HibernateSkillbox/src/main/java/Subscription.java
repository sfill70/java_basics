import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "Subscriptions")
public class Subscription implements Serializable{

    @EmbeddedId
    private Key key;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Subscription(){}

    public Subscription(Key key) {
        this.key = key;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;

        Subscription that = (Subscription) o;

        if (!Objects.equals(key, that.key)) return false;
        return Objects.equals(subscriptionDate, that.subscriptionDate);
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (subscriptionDate != null ? subscriptionDate.hashCode() : 0);
        return result;
    }

    @Embeddable
    public static class Key implements Serializable {

        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "student_id")
        public Student student;

        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "course_id")
        public Course course;

        public Key(){};

        public Key(Student student, Course course) {
            this.student = student;
            this.course = course;
        }

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


    }
}





/*@Entity
@Table(name = "Subscriptions")
public class Subscription implements Serializable {
    @EmbeddedId
    private Id id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    @Column(name = "subscription_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subscriptionsDate;

   *//* @Column(name = "subscription_date")
    private Date subscriptionsDate;*//*
   public Subscription(Id id) {
       this.id = id;
   }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;

        Subscription that = (Subscription) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(subscriptionsDate, that.subscriptionsDate);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (subscriptionsDate != null ? subscriptionsDate.hashCode() : 0);
        return result;
    }

    @Embeddable
    public static class Id implements Serializable{

        public Id(){}

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "student_id", referencedColumnName="id")
        public Student student;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "course_id", referencedColumnName="id")
        public Course course;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Id)) return false;

            Id id = (Id) o;

            if (!Objects.equals(student, id.student)) return false;
            return Objects.equals(course, id.course);
        }

        @Override
        public int hashCode() {
            int result = student != null ? student.hashCode() : 0;
            result = 31 * result + (course != null ? course.hashCode() : 0);
            return result;
        }
    }
}*/

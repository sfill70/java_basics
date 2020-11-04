import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList implements Serializable {
    @EmbeddedId
    private Id id;

    @ManyToOne
    @JoinColumn(name = "student_id", updatable = false, insertable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", updatable = false, insertable = false)
    private Course course;

    public LinkedPurchaseList() {
    }

    public LinkedPurchaseList(Student student, Course course) {
        this.id = new Id(student.getId(), course.getId());
        this.student = student;
        this.course = course;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "student_id")
        public int studentId;

        @Column(name = "course_id")
        public int courseId;

        public Id() {
        }

        public Id(int studentId, int courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Id)) return false;

            Id id = (Id) o;

            if (studentId != id.studentId) return false;
            return courseId == id.courseId;
        }

        @Override
        public int hashCode() {
            int result = studentId;
            result = 31 * result + courseId;
            return result;
        }
    }
}

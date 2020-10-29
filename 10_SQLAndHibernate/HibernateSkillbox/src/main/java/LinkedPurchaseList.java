
import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList implements Serializable {
    @EmbeddedId
    @Column(name = "student_id")
    private Id id;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @Embeddable
    public static class Id implements Serializable {

        public Id() {}

        public Id(int studentId, int courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }

        @Column(name = "student_id")
        private int studentId;

        @Column(name = "course_id")
        private int courseId;

        public int getStudentId() {
            return studentId;
        }

        public void setStudentId(int studentId) {
            this.studentId = studentId;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
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

import javax.persistence.*;
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int duration;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;
    private String description;
    @Column(name = "teacher_id")
    private int teacherId;
    @Column(name = "students_count")
    private int studentCount;
    private int price;
    @Column(name = "price_per_hour")
    private float pricePerHour;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public CourseType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public int getPrice() {
        return price;
    }

    public float getPricePerHour() {
        return pricePerHour;
    }
}

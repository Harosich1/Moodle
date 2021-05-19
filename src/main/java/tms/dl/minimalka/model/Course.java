package tms.dl.minimalka.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="courseID", updatable = false)
    private long courseID;

    @Column(name="courseName", nullable = false, columnDefinition = "TEXT")
    private String courseName;

    @Column(name="rk1", nullable = false, columnDefinition = "TEXT")
    private String rk1 = "0.0";

    @Column(name="rk2", nullable = false, columnDefinition = "TEXT")
    private String rk2 = "0.0";

    @Column(name="rkMean", nullable = false, columnDefinition = "TEXT")
    private String rkMean = "0.0";

    @Column(name="finalEx", nullable = false, columnDefinition = "TEXT")
    private String finalEx = "0.0";

    @Column(name="result", nullable = false, columnDefinition = "TEXT")
    private String result = "0.0";

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name="student_id", nullable = false)
    private Student student;

    @OneToMany
    private Set<Work> works;

    public Course() {}

    public Course(String courseName, Student student) {
        this.courseName = courseName;
        this.student = student;
    }

    public long getCourseID() {
        return courseID;
    }

    public void setCourseID(long id) {
        this.courseID = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String name) {
        this.courseName = name;
    }

    public String getRk1() {
        return rk1;
    }

    public void setRk1(String rk1) {
        this.rk1 = rk1;
    }

    public String getRk2() {
        return rk2;
    }

    public String getRkMean() {
        return rkMean;
    }

    public void setRkMean(String rkMean) {
        this.rkMean = rkMean;
    }

    public String getFinalEx() {
        return finalEx;
    }

    public void setFinalEx(String finalEx) {
        this.finalEx = finalEx;
    }

    public void setRk2(String rk2) {
        this.rk2 = rk2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + courseID +
                ", name='" + courseName + '\'' +
                ", student=" + student +
                '}';
    }
}

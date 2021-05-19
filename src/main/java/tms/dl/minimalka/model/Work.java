package tms.dl.minimalka.model;

import javax.persistence.*;

@Entity
@Table(name="works")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="workID", updatable = false)
    private long workID;

    @Column(name="workName", nullable = false, columnDefinition = "TEXT")
    private String workName;

    @Column(name="score", nullable = false)
    private Double score = 0.0;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public Work() {}

    public Work(String workName, Course course) {
        this.workName = workName;
        this.course = course;
    }

    public long getWorkID() {
        return workID;
    }

    public void setWorkID(long id) {
        this.workID = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String name) {
        this.workName = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + workID +
                ", name='" + workName + '\'' +
                ", score='" + score + '\'' +
                ", course=" + course +
                '}';
    }
}

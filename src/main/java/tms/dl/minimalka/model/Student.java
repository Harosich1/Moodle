package tms.dl.minimalka.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="studentID", updatable = false)
    private long studentID;

    @Column(name="password", updatable = false)
    private String password;

    @Column(name="firstName", nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(name="lastName", nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(name="email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @OneToMany
    private Set<Course> courses;

    public Student() {}

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long id) {
        this.studentID = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + studentID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

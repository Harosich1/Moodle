package tms.dl.minimalka.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tms.dl.minimalka.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CourseRepo extends CrudRepository<Course, Long> {
    @Query(value = "select * from courses where student_id = :id", nativeQuery = true)
    Set<Course> findCoursesByStudentId(@Param("id") long id);

    @Query(value = "select * from courses where course_name = :name", nativeQuery = true)
    Course finddCourseByName(@Param("name") String name);

    @Query(value = "select * from courses where course_name like CONCAT('%',:name,'%') and student_id = :id", nativeQuery = true)
    Set<Course> searchCoursesByNameAndStudentId(@Param("name") String name, @Param("id") long id);

    @Query(value = "select * from courses where course_name = :name and student_id = :id", nativeQuery = true)
    Course findCoursesByNameAndStudentId(@Param("name") String name, @Param("id") long id);

    @Query(value = "select * from courses where course_name = :name and student_id = :studentId and course_id = :courseId", nativeQuery = true)
    Course findCoursesByNameAndStudentIdAndCourseId(@Param("name") String name, @Param("studentId") long studentId, @Param("courseId") long courseId);
}

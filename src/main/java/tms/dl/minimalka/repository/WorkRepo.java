package tms.dl.minimalka.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tms.dl.minimalka.model.Course;
import tms.dl.minimalka.model.Work;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface WorkRepo extends CrudRepository<Work, Long> {
    @Query(value = "select * from works where course_id = :id", nativeQuery = true)
    Set<Work> findWorksByCourseId(@Param("id") long id);
}

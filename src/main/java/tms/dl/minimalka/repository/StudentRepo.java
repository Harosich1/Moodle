package tms.dl.minimalka.repository;

import tms.dl.minimalka.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Long> {

}

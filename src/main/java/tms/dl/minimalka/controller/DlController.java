package tms.dl.minimalka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tms.dl.minimalka.model.Course;
import tms.dl.minimalka.model.Student;
import tms.dl.minimalka.model.Work;
import tms.dl.minimalka.repository.CourseRepo;
import tms.dl.minimalka.repository.StudentRepo;
import tms.dl.minimalka.repository.WorkRepo;

import java.util.Optional;

@Controller
@RequestMapping("/{id}/home")
public class DlController {

    private StudentRepo studentRepo;
    private CourseRepo courseRepo;
    private WorkRepo workRepo;

    @Autowired
    public DlController(StudentRepo studentRepo, CourseRepo courseRepo, WorkRepo workRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.workRepo = workRepo;
    }

    @GetMapping()
    public String showAllCoursesForSpecificStudent(@PathVariable("id") Long id, Model courseModel, Model studentModel) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        courseModel.addAttribute("courses", courseRepo.findCoursesByStudentId(optionalStudent.get()
                .getStudentID()));
        studentModel.addAttribute("student", optionalStudent.get());
        return "home";
    }

    @GetMapping("/students")
    public String allStudents() {
        return studentRepo.findAll().toString();
    }

    @PostMapping("/students/new")
    public String newStudent(@RequestBody Student student) {
        studentRepo.save(student);
        return student.getFirstName();
    }

    @PostMapping("/courses/new")
    public String newCourse(@RequestBody Course course, @PathVariable("id") Long id) {
        Optional<Student> optionalStudent=studentRepo.findById(id);
        course.setStudent(optionalStudent.get());
        courseRepo.save(course);
        return course.getCourseName();
    }

    @GetMapping("/{id}/courses")
    public String NewWork(@PathVariable("id") Long id) {
        Optional<Student> optionalStudent=studentRepo.findById(id);
        return courseRepo.findCoursesByStudentId(optionalStudent.get().getStudentID()).toString();
    }

    @PostMapping("/works/new")
    public String newWork(@RequestBody Work work) {
        workRepo.save(work);
        return work.getWorkName();
    }

    @GetMapping("/search")
    public String searchAllCoursesForSpecificStudent(@PathVariable("id") Long id, @RequestParam(name = "course") String course, Model courseModel, Model studentModel) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        courseModel.addAttribute("courses", courseRepo.searchCoursesByNameAndStudentId(course, optionalStudent.get()
                .getStudentID()));
        studentModel.addAttribute("student", optionalStudent.get());
        return "home";
    }
}

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
import java.util.Set;

@Controller
@RequestMapping("/{id}/{courseName}")
public class CourseController {

    private StudentRepo studentRepo;
    private CourseRepo courseRepo;
    private WorkRepo workRepo;

    @Autowired
    public CourseController(StudentRepo studentRepo, CourseRepo courseRepo, WorkRepo workRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.workRepo = workRepo;
    }

   @GetMapping()
   public String showCourse(@PathVariable("courseName") String courseName, Model model) {
       model.addAttribute("course", courseRepo.finddCourseByName(courseName));
       return "coursePage";
   }

    @GetMapping("/allWorks")
    public String showAllWorks(@PathVariable("id") Long id, @PathVariable("courseName") String courseName,
                               Model woksModel, Model courseModel) {
        woksModel.addAttribute("works", workRepo.findWorksByCourseId(courseRepo.finddCourseByName(courseName).getCourseID()));
        courseModel.addAttribute("course", courseRepo.finddCourseByName(courseName));
        return "scorePage";
    }

    @PatchMapping("/update")
    public String updateScore(@PathVariable("id") Long id, @PathVariable("courseName") String courseName) {
        Optional<Student> optionalStudent = studentRepo.findById(id);
        Course course = courseRepo.findCoursesByNameAndStudentId(courseName, optionalStudent.get()
                .getStudentID());
        Set<Work> works = workRepo.findWorksByCourseId(course.getCourseID());
        course.setRk1(works.stream().mapToDouble(Work::getScore).sum() + "");
        course.setRk2(works.stream().mapToDouble(Work::getScore).sum() + "");
        course.setRkMean((Double.parseDouble(course.getRk1()) + Double.parseDouble(course.getRk2()))/2 + "");
        course.setResult(course.getRkMean() + course.getFinalEx() + "");

        courseRepo.save(course);
        return "scorePage";
    }
}

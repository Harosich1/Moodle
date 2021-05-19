package tms.dl.minimalka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tms.dl.minimalka.repository.CourseRepo;
import tms.dl.minimalka.repository.StudentRepo;
import tms.dl.minimalka.repository.WorkRepo;

@Controller
@RequestMapping("/{id}/profile")
public class ProfileController {

    private StudentRepo studentRepo;
    private CourseRepo courseRepo;
    private WorkRepo workRepo;

    @Autowired
    public ProfileController(StudentRepo studentRepo, CourseRepo courseRepo, WorkRepo workRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.workRepo = workRepo;
    }

   @GetMapping()
   public String showProfile(@PathVariable("id") Long id, Model model) {
       model.addAttribute("student", studentRepo.findById(id).get());
       return "profile";
   }
}

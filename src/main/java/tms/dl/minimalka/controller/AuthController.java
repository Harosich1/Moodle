package tms.dl.minimalka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tms.dl.minimalka.repository.CourseRepo;
import tms.dl.minimalka.repository.StudentRepo;
import tms.dl.minimalka.repository.WorkRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping()
public class AuthController {

    private StudentRepo studentRepo;
    private CourseRepo courseRepo;
    private WorkRepo workRepo;

    @Autowired
    public AuthController(StudentRepo studentRepo, CourseRepo courseRepo, WorkRepo workRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.workRepo = workRepo;
    }

   @GetMapping("/logout")
   public String showAllCourses(HttpServletRequest request, HttpServletResponse response) {
       new SecurityContextLogoutHandler().logout(request, null, null);
       return "login";
   }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login_perform")
    public String perform(@RequestParam(name = "id") String id) {
        return "redirect:/"+id+"/home";
    }
}

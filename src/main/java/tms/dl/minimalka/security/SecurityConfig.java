package tms.dl.minimalka.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import tms.dl.minimalka.model.Student;
import tms.dl.minimalka.repository.StudentRepo;

import java.util.ArrayList;
import java.util.Set;

import static tms.dl.minimalka.security.Role.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private StudentRepo studentRepo;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, StudentRepo studentRepo) {
        this.passwordEncoder = passwordEncoder;
        this.studentRepo = studentRepo;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("**/home").hasRole(USER.name())
                    .anyRequest()
                    .authenticated()
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        Iterable<Student> students = studentRepo.findAll();

        ArrayList<UserDetails> users = new ArrayList<>();
        students.forEach(student -> {
            users.add(User.builder().username("" + student.getStudentID())
                    .password(passwordEncoder.encode(student.getPassword()))
                    .roles(USER.name())
                    .build());
        });

        return new InMemoryUserDetailsManager(
                users
        );
    }
}

package com.nhnacademy.mvc_project.listener;

import com.nhnacademy.mvc_project.Gender;
import com.nhnacademy.mvc_project.Student;
import com.nhnacademy.mvc_project.repository.MapStudentRepository;
import com.nhnacademy.mvc_project.repository.StudentRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;
import java.util.Random;

@WebListener
public class WebApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();
        Random rd = new Random(System.currentTimeMillis());


        for(int i=1; i<=10; i++){
            // ... student 1 ~ 10 생성하기
            // 나이 : random 처리 : 20~30
            String studentId = "student" + String.valueOf(i);
            String studentName = "아카데미" + String.valueOf(i);
            Gender studentGender = Gender.M;

            if(rd.nextInt(10) % 2 != 0){
                studentGender = Gender.F;
            }
            int studentAge = rd.nextInt(10)+1;

            Student student = new Student(studentId, studentName, studentGender, studentAge);

            studentRepository.save(student);
        }
        // ... application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기
        context.setAttribute("studentRepository", studentRepository);
    }
}

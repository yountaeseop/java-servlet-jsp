package com.nhnacademy.mvc_project.servlet;

import com.nhnacademy.mvc_project.Gender;
import com.nhnacademy.mvc_project.Student;
import com.nhnacademy.mvc_project.repository.StudentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String studentId = req.getParameter("studentId");
        Student student = studentRepository.getStudentById(studentId);

        if (Objects.isNull(student)) {
            throw new RuntimeException("Student not found :" + studentId);
        }
        req.setAttribute("student", student);
        req.setAttribute("action", "/student/update.do");
        req.setAttribute("view", "/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // 요청 파라미터에서 학생 정보 추출
        String studentId = req.getParameter("studentId");
        String name = req.getParameter("name");

        Gender gender = null;
        if(Objects.nonNull(req.getParameter("gender"))){
            gender = Gender.valueOf(req.getParameter("gender"));
        }

        Integer age = null;
        if(Objects.nonNull(req.getParameter("age"))){
            age = Integer.parseInt(req.getParameter("age"));
        }

        if(Objects.isNull(studentId) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)){
            throw new RuntimeException("id,name,gender,age 확인해주세요!");
        }
        // Student 객체 생성
        Student student = new Student(studentId, name, gender, age);
        studentRepository.update(student);

        req.setAttribute("view", "redirect:/student/view.do?studentId=" + studentId);
    }
}


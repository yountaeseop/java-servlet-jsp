package com.nhnacademy.mvc_project.servlet;

import com.nhnacademy.mvc_project.Student;
import com.nhnacademy.mvc_project.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String studentId = req.getParameter("studentId");
        if(Objects.isNull(studentId)){
            throw new RuntimeException("parameter [id] : null");
        }

        Student student = studentRepository.getStudentById(studentId);
        if(Objects.isNull(student)){
            throw new RuntimeException("studentId를 확인해주세요");
        }

        log.error("student:{}", student);
        req.setAttribute("student",student);

        req.setAttribute("view", "/view.jsp");
    }

}

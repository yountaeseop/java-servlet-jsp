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

        req.setCharacterEncoding("UTF-8"); // 인코딩 설정

        //todo id null check
        String studentId = req.getParameter("studentId");

        if(studentId == null){
            System.out.println("학생 아이디가 없습니다.");
            return;
        }

        //todo student 조회
        Student student = studentRepository.getStudentById(studentId);
        req.setAttribute("student",student);

        //todo /student/view.jsp <-- forward
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view.jsp");
        dispatcher.forward(req, resp);
    }

}

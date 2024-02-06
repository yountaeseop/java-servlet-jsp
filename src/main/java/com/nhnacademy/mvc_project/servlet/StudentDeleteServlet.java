package com.nhnacademy.mvc_project.servlet;

import com.nhnacademy.mvc_project.Student;
import com.nhnacademy.mvc_project.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "studentDeleteServlet", urlPatterns = "/student/delete")
public class StudentDeleteServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8"); // 인코딩 설정

        //todo get parameter  : id , id가 존재하지 않을경우 throw new RuntimeException("...")
        String studentId = req.getParameter("studentId");

        if(studentId == null){
            System.out.println("학생 아이디가 없습니다.");
            throw new RuntimeException("ID가 존재하지 않습니다.");
        }

        studentRepository.deleteById(studentId);

        //todo /student/list <-- redirect
        resp.sendRedirect("/student/list");
    }
}

package com.nhnacademy.mvc_project.servlet;

import com.nhnacademy.mvc_project.Gender;
import com.nhnacademy.mvc_project.Student;
import com.nhnacademy.mvc_project.repository.StudentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // /student/register.jsp <- forward 하기
        RequestDispatcher dispatcher = req.getRequestDispatcher("/studentRegister.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");

        // 요청 파라미터에서 학생 정보 추출
        String studentId = req.getParameter("studentId");
        String name = req.getParameter("name");
        Gender gender = Gender.valueOf(req.getParameter("gender").toUpperCase());
        String age = req.getParameter("age");

        //todo null check
        if (studentId == null || studentId.isEmpty() || name == null || name.isEmpty() || gender == null || age == null || age.isEmpty()) {
            // 필수 항목 중 하나라도 누락된 경우 로그 출력
            System.out.println("Required parameters are missing.");
            return;
        }

        // 숫자로 변환 가능한지 체크
        int ageValue;
        try {
            ageValue = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            // 나이가 숫자로 변환되지 않는 경우 로그 출력
            System.out.println("Invalid age format: " + age);
            return;
        }

        // Student 객체 생성
        Student student = new Student(studentId, name, gender, ageValue);

        //todo save 구현
        studentRepository.save(student);

        //todo redirect /student/view?id=stdent1
        resp.sendRedirect("/student/view?studentId="+studentId);
        //

    }
}

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

@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo 학생조회
        req.setCharacterEncoding("UTF-8"); // 인코딩 설정

        //todo id null check
        String studentId = req.getParameter("studentId");

        if (studentId == null) {
            System.out.println("학생 아이디가 없습니다.");
            return;
        }

        //todo student 조회
        Student student = studentRepository.getStudentById(studentId);
        req.setAttribute("student", student);
        req.setAttribute("action", "/student/update");

        //todo forward : /register.jsp
        RequestDispatcher dispatcher = req.getRequestDispatcher("/studentRegister.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        studentRepository.update(student);

        //todo redirect /student/view?id=stdent1
        resp.sendRedirect("/student/view?studentId=" + studentId);

    }
}


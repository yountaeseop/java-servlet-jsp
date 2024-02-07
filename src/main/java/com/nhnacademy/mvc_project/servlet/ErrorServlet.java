package com.nhnacademy.mvc_project.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@WebServlet(name = "errorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("exception_type", req.getAttribute("javax.servlet.error.exception_type"));
        req.setAttribute("message", req.getAttribute("javax.servlet.error.message"));
        req.setAttribute("exception", req.getAttribute("javax.servlet.error.exception"));
        req.setAttribute("request_uri", req.getAttribute("javax.servlet.error.request_uri"));


        //todo /error.jsp forward 처리
        RequestDispatcher dispatcher = req.getRequestDispatcher("/error.jsp");
        dispatcher.forward(req, resp);

    }

}

package com.nhnacademy.mvc_project.servlet;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.RequestDispatcher.ERROR_STATUS_CODE;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {
    private static final String REDIRECT_PREFIX="redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //공통 처리 - 응답 content-type, character encoding 지정.
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        try{
            //실제 요청 처리할 servlet을 결정
            String servletPath = resolveServlet(req.getServletPath());
            RequestDispatcher rd = req.getRequestDispatcher(servletPath);
            rd.include(req, resp);

            //실제 요청을 처리한 servlet이 'view'라는 request 속성값으로 view를 전달해 줌.
            String view = (String) req.getAttribute("view");

            if (view.startsWith(REDIRECT_PREFIX)) {
                log.error("redirect-url : {}", view.substring(REDIRECT_PREFIX.length()+1));

                //`redirect:`로 시작하면 redirect 처리.
                String redirectUrl = view.substring(REDIRECT_PREFIX.length());
                if(redirectUrl.equals("redirect:")){
                    resp.sendRedirect(redirectUrl);
                }

            } else {
                //todo redirect 아니면 JSP에게 view 처리를 위임하여 그 결과를 include시킴.
                RequestDispatcher jspDispatcher = req.getRequestDispatcher(view);
                jspDispatcher.include(req, resp);
            }
        }catch(Exception ex){
            //공통 error 처리 - ErrorServlet 참고해서 처리
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute("javax.servlet.error.exception_type"));
            req.setAttribute("message", req.getAttribute("javax.servlet.error.message"));
            req.setAttribute("exception", req.getAttribute("javax.servlet.error.exception"));
            req.setAttribute("request_uri", req.getAttribute("javax.servlet.error.request_uri"));

            //forward - /error.jsp
            RequestDispatcher errorDispatcher = req.getRequestDispatcher("/error.jsp");
            errorDispatcher.forward(req, resp);
        }
    }

    private String resolveServlet(String servletPath){
        String processingServlet = null;
        if("/student/list.do".equals(servletPath)){
            processingServlet = "/student/list";
        } else if("/student/register.do".equals(servletPath)){
            processingServlet = "/student/list";
        } else if("/student/view.do".equals(servletPath)){
            processingServlet = "/student/list";
        } else if("/student/update.do".equals(servletPath)){
            processingServlet = "/student/list";
        } else if("/student/delete.do".equals(servletPath)){
            processingServlet = "/student/list";
        }

        return processingServlet;
    }

}

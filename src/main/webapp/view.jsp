<%@ page import="com.nhnacademy.mvc_project.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>학생-조회</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h1>학생 조회</h1>
<%--request에서 학생 정보를 가져옴--%>
<%Student student = (Student)request.getAttribute("student");%>
<style>
    *{
        font-size:20pt;
    }
    table,th,td {
        border-collapse: collapse;
        border: 2px solid darkgray;
        width: 800px;
    }
    input {
        margin: 5px;
        border-radius: 5px;
    }
    input[type="radio"] {
        width: 20px; /* 라디오 버튼의 너비 조정 */
        height: 20px; /* 라디오 버튼의 높이 조정 */
    }
    .menu{
        display: flex;
        flex-direction: row;
    }

</style>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <td><%= student.getId()%></td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>이름</th>
            <td><%= student.getName()%></td>
        </tr>
        <tr>
            <th>성별</th>
            <td><%= student.getGender()%></td>
        </tr>
        <tr>
            <th>나이</th>
            <td><%= student.getAge()%></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <th>등록일</th>
            <td><%= student.getCreateAt()%></td>
        </tr>
        </tfoot>
    </table>
    </br>
    <div class="menu">
        <a href="/student/list.do">&nbsp;리스트</a>&nbsp;
        <!-- todo ${update_link} 설정 c:url -->
        <a href="/student/update.do?studentId=${student.id}">수정</a>&nbsp;
        <form action="/student/delete.do?studentId=${student.id}" method="post" id="deleteForm">
            <button type="submit">삭제</button>
        </form>
    </div>
</body>
</html>
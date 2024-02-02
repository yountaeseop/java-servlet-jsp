<%@ page import="com.nhnacademy.mvc_project.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>student - list</title>
    <link rel="stylesheet" href="/style.css" />
</head>

<body>
<h1>학생 리스트</h1>
<p><a href="/student/register" >학생(등록)</a></p>
<%List<Student> studentList = (List<Student>)request.getAttribute("studentList");%>
<style>
    *{
        font-size:20pt;
    }
    table,th,td {
        border-collapse: collapse;
        border: 2px solid darkgray;
        width: 800px;
    }
</style>
<table>
    <thead>
    <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>성별</th>
        <th>나이</th>
        <th>cmd</th>
    </tr>
    </thead>
    <tbody>
    <!--todo list 구현하기 c:foreach -->
    <c:forEach var="student" items="${studentList}">
        <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.gender}</td>
            <td>${student.age}</td>
            <td><a href="/student/view" >조회</a></td>
        </tr>
    </c:forEach>
    <%for(Student student : studentList) {%>
        <tr>
            <td><%= student.getId()%></td>
            <td><%= student.getName()%></td>
            <td><%= student.getGender()%></td>
            <td><%= student.getAge()%></td>
            <td><a href="/student/view" >조회</a></td>
        </tr>
    <%}%>
    </tbody>
    <tfoot>
        <tr>
            <td>foot</td>
            <td>foot</td>
            <td>foot</td>
            <td>foot</td>
            <td>foot</td>
        </tr>
    </tfoot>
</table>
</body>
</html>
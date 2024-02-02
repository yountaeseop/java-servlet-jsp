<%@ page import="com.nhnacademy.mvc_project.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>student - list</title>
    <link rel="stylesheet" href="/style.css" />
</head>

<body>
<h1>학생 등록</h1>
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
    input {
        margin: 5px;
        border-radius: 5px;
    }

    input[type="radio"] {
        width: 20px; /* 라디오 버튼의 너비 조정 */
        height: 20px; /* 라디오 버튼의 높이 조정 */
    }
</style>
<form action="/student/register" method="post">
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <td><input type="text" name="studentId"></td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>이름</th>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <th>성별</th>
                <td>
                    <input type="radio" name="gender" value="M"/>남
                    <input type="radio" name="gender" value="F"/>여
                </td>
            </tr>
        </tbody>
        <tfoot>
        <tr>
            <th>나이</th>
            <td><input type="text" name="age"></td>
        </tr>
        </tfoot>
    </table>
    </br>
    <input type="submit" value="등록">
</form>
</body>
</html>
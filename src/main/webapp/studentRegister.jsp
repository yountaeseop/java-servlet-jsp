<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>student - list</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
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
<h1>학생 등록</h1>
<!-- todo action 주소 설정
           //등록
               action = /student/register
           //수정
               action = /student/update
       -->
<form action="${action}" method="post">
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <td><input type="text" name="studentId" value="${student.id}" required></td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>이름</th>
                <td><input type="text" name="name" value="${student.name}" required></td>
            </tr>
            <tr>
                <th>성별</th>
                <td>
                    <input type="radio" name="gender" value="M" ${student.gender.toString() == 'M' ? 'checked' : ''} required/>남
                    <input type="radio" name="gender" value="F" ${student.gender.toString() == 'F' ? 'checked' : ''} required/>여
                </td>
            </tr>
        </tbody>
        <tfoot>
        <tr>
            <th>나이</th>
            <td><input type="text" name="age" value="${student.age}" required></td>
        </tr>
        </tfoot>
    </table>
    </br>
    <button type="submit">
        <c:choose>
            <c:when test="${empty student}">
                등록
            </c:when>
            <c:otherwise>
                수정
            </c:otherwise>
        </c:choose>
    </button>
</form>
</body>
</html>
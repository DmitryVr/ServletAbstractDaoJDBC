<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <title>Main</title>
</head>
<body>
<h1>Welcome</h1>

<a href="getAllBooks">Get all books</a>
<br/>

<%--добавить или обновить--%>
<form action="/addOrUpdate" method="post">
    <table>
        <tr>
            <c:if test="${book.id != 0}">
            <td>
                <input type="hidden" name="id" value="${book.id}"/>
            </td>
            </c:if>
        </tr>
        <tr>
            <td>Название</td>
            <td>
                <input type="text" name="bookName"
                       <c:if test="${book.id != 0}">value="${book.bookName}"</c:if>/>
            </td>
        </tr>
        <tr>
            <td>Описание</td>
            <td>
                <textarea name="description" rows="5" cols="20"><c:if test="${book.id != 0}">${book.description}</c:if></textarea>
            </td>
        </tr>
        <tr>
            <td>Жанр</td>
            <td>
                <select name="genre">
                    <c:forEach var="genreVar" items="${genreList}">
                        <option value="${genreVar.id}"
                                <c:if test="${book.id != 0 && book.genre.id == genreVar.id}">selected</c:if>
                        >
                            ${genreVar.genreName}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Отправить">
            </td>
        </tr>
    </table>
</form>

<%--список книг--%>
<c:if test="${not empty bookList}">
    <div>
        <table>
            <tr>
                <th>Идентификатор</th>
                <th>Название</th>
                <th>Описание</th>
                <th>Жанр</th>
                <th>Удалить</th>
            </tr>
            <c:forEach var="book" items="${bookList}">
            <tr>
                <td>
                    <a href="getBookById?id=${book.id}">Update ${book.id}</a>
                </td>
                <td>${book.bookName}</td>
                <td>
                    <textarea rows="4" cols="20" readonly>${book.description}</textarea>
                </td>
                <td>${book.genre.genreName}</td>
                <td>
                    <a href="deleteBookById?id=${book.id}">Delete</a>
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
</c:if>

</body>
</html>
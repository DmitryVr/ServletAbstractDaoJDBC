package controller;

import dao.BookDao;
import model.Book;
import model.Genre;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddOrUpdateServlet extends HttpServlet {
    private BookDao bookDao = new BookDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String bookName = request.getParameter("bookName");
        String description = request.getParameter("description");
        String genreId = request.getParameter("genre");

        Genre genre = new Genre();
        genre.setId(Integer.parseInt(genreId));

        Book book = new Book(bookName, description, genre);

        if (id.equals("")) {
            bookDao.create(book);
        } else {
            book.setId(Integer.parseInt(id));
            bookDao.update(book);
        }

        response.sendRedirect("getAllBooks");
    }
}

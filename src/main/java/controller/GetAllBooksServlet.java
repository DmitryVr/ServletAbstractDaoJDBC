package controller;

import dao.BookDao;
import dao.GenreDao;
import model.Book;
import model.Genre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetAllBooksServlet extends HttpServlet {
    private BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> bookList = bookDao.getAll();
//        request.setAttribute("bookList", bookList);

        HttpSession session = request.getSession(true);
        session.setAttribute("bookList", bookList);
//        session.setAttribute("genreList", genreList);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/main.jsp");
        rd.forward(request, response);
    }
}

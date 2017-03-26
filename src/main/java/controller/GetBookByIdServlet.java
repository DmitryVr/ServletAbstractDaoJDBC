package controller;

import dao.BookDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetBookByIdServlet extends HttpServlet {
    private BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("book", bookDao.getById(id));

//        response.sendRedirect("getAllBooks");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/main.jsp");
        rd.forward(request, response);
    }
}

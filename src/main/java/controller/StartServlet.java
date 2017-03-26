package controller;

import dao.GenreDao;
import model.Genre;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class StartServlet extends HttpServlet {
    private GenreDao genreDao = new GenreDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Genre> genreList = genreDao.getAll();
        HttpSession session = request.getSession(true);
        session.setAttribute("genreList", genreList);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/view/main.jsp");
        rd.forward(request, response);
    }
}

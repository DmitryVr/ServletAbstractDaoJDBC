package dao;

import model.Book;
import model.Genre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao extends AbstractDao<Book> {
    @Override
    protected void fillCreatePreparedStatement(PreparedStatement preparedStatement, Book model) {
        try {
            preparedStatement.setString(1, model.getBookName());
            preparedStatement.setString(2, model.getDescription());
            preparedStatement.setInt(3, model.getGenre().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void fillUpdatePreparedStatement(PreparedStatement preparedStatement, Book model) {
        try {
            preparedStatement.setString(1, model.getBookName());
            preparedStatement.setString(2, model.getDescription());
            preparedStatement.setInt(3, model.getGenre().getId());
            preparedStatement.setInt(4, model.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private GenreDao genreDao = new GenreDao();

    @Override
    protected Book getModel(ResultSet resultSet) {
        Book book = new Book();
        try {
            book.setId(resultSet.getInt("id"));
            book.setBookName(resultSet.getString("bookName"));
            book.setDescription(resultSet.getString("description"));

            Integer genre_id = resultSet.getInt("genre_id");
            Genre genre = genreDao.getById(genre_id);

            book.setGenre(genre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    protected String getCreateQuery() {
        return dbUtil.getQuery("create.book");
    }

    @Override
    protected String getDeleteQuery() {
        return dbUtil.getQuery("delete.book.by.id");
    }

    @Override
    protected String getUpdateQuery() {
        return dbUtil.getQuery("update.book");
    }

    @Override
    protected String getGetByIdQuery() {
        return dbUtil.getQuery("get.book.by.id");
    }

    @Override
    protected String getAllQuery() {
        return dbUtil.getQuery("get.all.books");
    }
}

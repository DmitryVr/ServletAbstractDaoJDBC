package dao;

import model.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreDao extends AbstractDao<Genre> {
    @Override
    protected void fillCreatePreparedStatement(PreparedStatement preparedStatement, Genre model) {
        try {
            preparedStatement.setString(1, model.getGenreName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void fillUpdatePreparedStatement(PreparedStatement preparedStatement, Genre model) {
        try {
            preparedStatement.setString(1, model.getGenreName());
            preparedStatement.setInt(2, model.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Genre getModel(ResultSet resultSet) {
        Genre genre = new Genre();
        try {
            genre.setId(resultSet.getInt("id"));
            genre.setGenreName(resultSet.getString("genreName"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }

    @Override
    protected String getCreateQuery() {
        return dbUtil.getQuery("create.genre");
    }

    @Override
    protected String getDeleteQuery() {
        return dbUtil.getQuery("delete.genre.by.id");
    }

    @Override
    protected String getUpdateQuery() {
        return dbUtil.getQuery("update.genre");
    }

    @Override
    protected String getGetByIdQuery() {
        return dbUtil.getQuery("get.genre.by.id");
    }

    @Override
    protected String getAllQuery() {
        return dbUtil.getQuery("get.all.genres");
    }
}

package dao;

import model.BaseModel;
import org.apache.log4j.Logger;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractDao<T extends BaseModel> implements ItemDao<T> {
    private static final Logger log = Logger.getLogger(AbstractDao.class);
    protected DatabaseUtil dbUtil = new DatabaseUtil();

    @Override
    public void create(T model) {
        log.info("Entrance to create");
        String query = getCreateQuery();
        log.info("query = " + query);

        try (Connection connection = dbUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            fillCreatePreparedStatement(preparedStatement, model);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        log.info("Entrance to delete");
        String query = getDeleteQuery();

        try (Connection connection = dbUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T model) {
        log.info("Entrance to update");
        String query = getUpdateQuery();
        log.info("query = " + query);

        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            fillUpdatePreparedStatement(preparedStatement, model);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getById(Integer id) {
        log.info("Entrance to getById");
        String query = getGetByIdQuery();
        log.info("query = " + query);

        ResultSet resultSet = null;

        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getModel(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        log.info("Entrance to getAll");
        String query = getAllQuery();
        log.info("query = " + query);
        List<T> list = new ArrayList<>();

        try (Connection connection = dbUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                list.add(getModel(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    protected abstract void fillCreatePreparedStatement(PreparedStatement preparedStatement, T model);
    protected abstract void fillUpdatePreparedStatement(PreparedStatement preparedStatement, T model);
    protected abstract T getModel(ResultSet resultSet);

    protected abstract String getCreateQuery();
    protected abstract String getDeleteQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getGetByIdQuery();
    protected abstract String getAllQuery();
}

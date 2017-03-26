package util;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DatabaseUtil {
    private static final Logger log = Logger.getLogger(DatabaseUtil.class);
    private Properties queries;

    public DatabaseUtil() {
        loadProperties();
    }

    public Connection getConnection() {
        log.info("Entrance to getConnection");
        Connection connection = null;
        try {
            Context context = new InitialContext();
            Context initContext = (Context) context.lookup("java:comp/env");
            DataSource dataSource = (DataSource) initContext.lookup("jdbc/root");
            connection = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    private void loadProperties() {
        log.info("Entrance to loadProperties");

        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream("queries.properties")) {
            queries = new Properties();
            queries.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getQuery(String propertyName) {
        log.info("Entrance to getQuery");
        if (queries == null)
            loadProperties();

        String query = queries.getProperty(propertyName);
        return query;
    }
}

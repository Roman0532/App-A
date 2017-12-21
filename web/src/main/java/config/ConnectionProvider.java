package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import exception.ExceptionProviders;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;
import service.DbException;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class ConnectionProvider implements ExceptionProviders<Connection> {
    @InjectLogger
    private
    Logger logger;
    private ComboPooledDataSource cpds = new ComboPooledDataSource();

    private static final String DRIVER = "driver";
    private static final String URL = "url";
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";
    private static final String CONFIG_PROPERTIES = "../config.properties";

    /**
     * Подключение к БД
     */
    @Override
    public Connection get() throws DbException {
        Properties property = new Properties();

        try (InputStream fileInputStream = this.getClass().getResourceAsStream(CONFIG_PROPERTIES)) {
            property.load(fileInputStream);

            cpds.setDriverClass(property.getProperty(DRIVER));
            cpds.setJdbcUrl(property.getProperty(URL));
            cpds.setUser(System.getenv(LOGIN));
            cpds.setPassword(System.getenv(PASSWORD));

            cpds.setMaxStatements(180);
            cpds.setMaxStatementsPerConnection(180);
            cpds.setMinPoolSize(50);
            cpds.setAcquireIncrement(10);
            cpds.setMaxPoolSize(60);
            cpds.setMaxIdleTime(30);
            dbMigration(property);
            return cpds.getConnection();
        } catch (Exception e) {
            logger.error("Ошибка соеденения с базой данных");
            throw new DbException("Соеденение не установленно", e);
        }
    }

    private void dbMigration(Properties property) throws DbException {
        logger.debug("Выполнение миграций");
        try {
            Flyway flyway = new Flyway();
            flyway.setDataSource(property.getProperty(URL), System.getenv(LOGIN), System.getenv(PASSWORD));
            flyway.migrate();
        } catch (Exception e) {
            logger.error("Не могу выполнить миграцию");
            throw new DbException("Не могу выполнить миграцию", e);
        }
    }
}


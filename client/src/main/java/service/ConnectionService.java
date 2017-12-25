package service;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import provider.ConnectionProvider;
import lombok.extern.log4j.Log4j2;
import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
@Log4j2

public class ConnectionService implements ConnectionProvider<Connection> {
    private static final String DRIVER = "driver";
    private static final String URL = "url";
    private static final String LOGIN = "LOGIN";
    private static final String PASSWORD = "PASSWORD";
    private static final String CONFIG_PROPERTIES = "/config.properties";

    private ComboPooledDataSource cpds = new ComboPooledDataSource();
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
            return cpds.getConnection();
        } catch (Exception e) {
            log.error("Ошибка соеденения с базой данных");
            throw new DbException("Соеденение не установленно", e);
        }
    }

    public void dbMigration() throws DbException {
        Properties property = new Properties();
        try (InputStream fileInputStream = this.getClass().getResourceAsStream(CONFIG_PROPERTIES)) {
            property.load(fileInputStream);
        } catch (IOException e) {
            log.error("Файл не найден ", e);
            throw new DbException("Файл не найден", e);
        }

        try {
            Flyway flyway = new Flyway();
            flyway.setValidateOnMigrate(false);
            flyway.setDataSource(property.getProperty(URL), System.getenv(LOGIN), System.getenv(PASSWORD));
            flyway.migrate();
        } catch (Exception e) {
            log.error("Не могу мигрировать", e);
            throw new DbException("Не могу мигрировать", e);
        }
    }
}

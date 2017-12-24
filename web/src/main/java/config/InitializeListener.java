package config;

import org.apache.logging.log4j.Logger;
import service.ConnectionService;
import service.DbException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitializeListener implements ServletContextListener {
    @InjectLogger
    private
    Logger logger;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ConnectionService connectionService = new ConnectionService();
        try {
            connectionService.dbMigration();
        } catch (DbException e) {
          logger.error("Не могу мигрировать");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

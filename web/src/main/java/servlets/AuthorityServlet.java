package servlets;

import com.google.gson.Gson;
import config.ConnectionProvider;
import config.InjectLogger;
import dao.AuthenticationDao;
import dao.AuthorizationDao;
import org.apache.logging.log4j.Logger;
import service.DbException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

@Singleton
public class AuthorityServlet extends HttpServlet {
    @InjectLogger
    private
    Logger logger;
    @Inject
    private
    Gson gson;
    @Inject
    private
    ConnectionProvider connectionProvider;
    private String json;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        logger.debug("Установка соеденения");
        try (Connection dbConn = connectionProvider.get()) {
            logger.debug("Соединение прошло успешно");
            AuthenticationDao authenticationDao = new AuthenticationDao(dbConn);
            AuthorizationDao authorizationDao = new AuthorizationDao(dbConn, authenticationDao);

            if (req.getParameter("id") != null) {
                findRoleById(req, authorizationDao);
            } else if (req.getParameter("userId") != null) {
                findRoleByUserId(req, authorizationDao);
            } else {
                findAllRoles(authorizationDao);
            }
            out.println(json);
        } catch (DbException | SQLException e) {
            logger.error("Произошла ошибка связанная с работой базы данных");
            getServletContext().getRequestDispatcher("/error500.jsp").forward(req, resp);
        }
    }

    private void findAllRoles(AuthorizationDao authorizationDao) throws DbException {
        ArrayList roles = authorizationDao.findAllRoles();
        if (roles != null) {
            json = gson.toJson(roles);
        } else {
            json = "Роли не найдены";
        }
    }

    private void findRoleByUserId(HttpServletRequest req, AuthorizationDao authorizationDao) throws DbException {
        ArrayList userRoles = authorizationDao.findUserRole(Integer.parseInt(req.getParameter("userId")));
        if (userRoles != null) {
            json = gson.toJson(userRoles);
        } else {
            json = "Пользователя с таким id не существует";
        }

    }

    private void findRoleById(HttpServletRequest req, AuthorizationDao authorizationDao) throws DbException {
        String role = authorizationDao.findRoleById(Integer.parseInt(req.getParameter("id")));
        if (role != null) {
            json = gson.toJson(role);
        } else {
            json = "Записи с таким id не существует";
        }
    }
}

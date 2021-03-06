package servlets;

import com.google.gson.Gson;
import com.google.inject.Inject;
import config.InjectLogger;
import dao.AuthenticationDao;
import domain.User;
import org.apache.logging.log4j.Logger;
import service.DbException;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Singleton
public class UserServlet extends HttpServlet {
    @InjectLogger
    private
    Logger logger;
    @Inject
    private
    Gson gson;
    @Inject
    private AuthenticationDao authenticationDao;
    private String json;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            if (req.getParameter("id") != null) {
                findUserById(req, authenticationDao);
            } else {
                findAllUsers(authenticationDao);
            }
            out.println(json);
        } catch (DbException e) {
            logger.error("Произошла ошибка связанная с работой базы данных");
            getServletContext().getRequestDispatcher("/error500.jsp").forward(req, resp);
        }
    }

    private void findAllUsers(AuthenticationDao authenticationDao) throws DbException {
        ArrayList users = authenticationDao.findAllUsers();
        if (users != null) {
            json = gson.toJson(users);
        } else {
            json = "Пользователей не существует";
        }
    }

    private void findUserById(HttpServletRequest req, AuthenticationDao authenticationDao) throws DbException {
        User user = authenticationDao.findLoginById(Integer.parseInt(req.getParameter("id")));
        if (user != null) {
            json = gson.toJson(user);
        } else {
            json = "Пользователя с таким id не существует";
        }
    }
}
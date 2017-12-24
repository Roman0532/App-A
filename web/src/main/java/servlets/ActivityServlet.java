package servlets;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import config.InjectLogger;
import dao.AccountingDao;
import domain.Accouning;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Singleton
public class ActivityServlet extends HttpServlet {
    @InjectLogger
    private
    Logger logger;
    @Inject
    private
    AccountingDao accountingDao;
    @Inject
    private
    Gson gson;
    private String json;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            if (req.getParameter("id") != null) {
                findActivityById(req, accountingDao);
            } else if (req.getParameter("authorityId") != null) {
                findActivityByAuthorityId(req, accountingDao);
            } else {
                findAllActivity(accountingDao);
            }
            out.println(json);
        } catch (Exception e) {
            logger.error("Произошла ошибка связанная с работой базы данных");
            getServletContext().getRequestDispatcher("/error500.jsp").forward(req, resp);
        }
    }

    private void findActivityByAuthorityId(HttpServletRequest req, AccountingDao accountingDao) throws Exception {
        ArrayList activity;
        String role = accountingDao.findRoleByAuthorityId(Integer.parseInt(req.getParameter("authorityId")));
        if (role == null) {
            json = "Такой роли не существует";
        } else {
            activity = accountingDao.findActivityByRole(role);
            if (activity != null) {
                json = gson.toJson(activity);
            } else {
                json = "Действия с такой ролью не существует";
            }
        }
    }

    private void findAllActivity(AccountingDao accountingDao) throws Exception {
        ArrayList activity;
        activity = accountingDao.findAllActivity();
        if (activity != null) {
            json = gson.toJson(activity);
        } else {
            json = "Действий не найдено";
        }
    }

    private void findActivityById(HttpServletRequest req,
                                  AccountingDao accountingDao) throws Exception {

        Accouning activity = accountingDao.findActivityById(Integer.parseInt(req.getParameter("id")));
        if (activity != null) {
            json = gson.toJson(activity);
        } else {
            json = "Действия с таким id не существует";
        }
    }
}

package servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class echoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        String urlGet = ("http://localhost:8080/web-1.0-SNAPSHOT/echo/get");
        req.setCharacterEncoding("UTF-8");
        if (req.getRequestURL().toString().equals(urlGet)) {
            out.println("<html>");
            out.println("<body>");
            out.println("<p>" + req.getParameter("id") + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("get?id=" + req.getParameter("id"));
    }
}

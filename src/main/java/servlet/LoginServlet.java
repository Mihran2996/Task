package servlet;

import manager.UserManager;
import model.User;
import model.UserType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserManager userManager = new UserManager();
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User user = userManager.getByEmailAndPassword(email, password);
            if (UserType.USER == (user.getType())) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/home");
                rd.forward(req, resp);
            } else {
                req.getRequestDispatcher("/manager.jsp").forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

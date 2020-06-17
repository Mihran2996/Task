package servlet;

import manager.TaskManager;
import manager.UserManager;
import model.Task;
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
import java.text.ParseException;
import java.util.List;


@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserManager userManager = new UserManager();
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User user = userManager.getByEmailAndPassword(email, password);
            if (user != null) {
                if (UserType.USER == (user.getType())) {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/home");
                    rd.forward(req, resp);
                } else if (UserType.MANAGER == (user.getType())) {
                    req.getRequestDispatcher("/manager.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("message", "Invalid password or login !");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserManager userManager = new UserManager();
            TaskManager taskManager = new TaskManager();
            String str = String.valueOf(req.getParameter("ep"));
            String[] split = str.split(",");
            User user = userManager.getByEmailAndPassword(split[0], split[1]);
            if (UserType.USER == (user.getType())) {
                List<Task> allTask = taskManager.getTask(user.getId());
                req.setAttribute("user", user);
                req.setAttribute("task", allTask);
                req.getRequestDispatcher("/userTask.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/manager.jsp").forward(req, resp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}

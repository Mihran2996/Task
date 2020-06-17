package servlet;

import lombok.SneakyThrows;
import manager.TaskManager;
import manager.UserManager;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/remove")
public class RemoveUserServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int userId = Integer.parseInt(id);
        UserManager userManager = new UserManager();
        try {
            userManager.deleteById(userId);
            List<User> allUsers = userManager.getAllUsers();
            req.setAttribute("allUsers", allUsers);
            req.getRequestDispatcher("/manager.jsp").forward(req, resp);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

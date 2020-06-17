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
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class UserHomeServlet extends HttpServlet {
    User currentUser;
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskManager taskManager = new TaskManager();
        UserManager userManager = new UserManager();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userManager.getByEmailAndPassword(email, password);
        if (user != null) {
            currentUser = user;
            List<Task> allTask = taskManager.getTask(user.getId());
            req.setAttribute("user", user);
            req.setAttribute("task", allTask);
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
        }

    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskManager taskManager = new TaskManager();
        String status = req.getParameter("status");
        String id = req.getParameter("id");
        int tasId = Integer.parseInt(id);
        List<Task> tasks = taskManager.changeStatus(tasId, status, currentUser.getId());
        req.setAttribute("task", tasks);
        req.setAttribute("user", currentUser);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);

    }

}

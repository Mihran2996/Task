package servlet;

import lombok.SneakyThrows;
import manager.TaskManager;
import manager.UserManager;
import model.Task;
import model.TaskStatus;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(urlPatterns = "/man")
public class ManagerServlet extends HttpServlet {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            UserManager userManager = new UserManager();
            String type = req.getParameter("type");
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            User user = User.builder()
                    .type(UserType.valueOf(type))
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build();
            userManager.addUser(user);
            List<User> allUsers = userManager.getAllUsers();
            req.setAttribute("user", allUsers);
            req.getRequestDispatcher("/manager.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskManager taskManager = new TaskManager();
        String status = req.getParameter("status");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String deadline = req.getParameter("deadline");
        String userId = req.getParameter("userId");
        Task task = Task.builder()
                .status(TaskStatus.valueOf(status))
                .name(name)
                .description(description)
                .deadline(sdf.parse(deadline))
                .userId(Integer.parseInt(userId))
                .build();
        taskManager.TaskAdd(task);
        req.getRequestDispatcher("/manager.jsp").forward(req, resp);

    }
}
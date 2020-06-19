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
            User byEmail = userManager.getByEmail(email);
            StringBuilder msg=new StringBuilder();
            if (byEmail!=null && byEmail.getEmail().equals(email)) {
                msg.append("<span style=\"color:red\">Email already exists!</span>");
            }else {
                msg.append("<span style=\"color:green\">User was added</span>");
            }
            req.getSession().setAttribute("msg",msg.toString());
            userManager.addUser(user);
            resp.sendRedirect("/manager.jsp");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    @SneakyThrows

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
        taskManager.taskAdd(task);
        resp.sendRedirect("/manager.jsp");


    }
}

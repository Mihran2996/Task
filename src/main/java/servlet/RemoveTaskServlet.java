package servlet;

import lombok.SneakyThrows;
import manager.TaskManager;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/removetask")
public class RemoveTaskServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int taskId = Integer.parseInt(id);
        TaskManager taskManager = new TaskManager();
        try {
            taskManager.deleteById(taskId);
            List<Task> allTask = taskManager.getAllTask();
            req.setAttribute("allTask", allTask);
            req.getRequestDispatcher("/manager.jsp").forward(req, resp);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


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
import java.util.List;

@WebServlet(urlPatterns = "/change")
public class ChangeTaskUser extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TaskManager taskManager = new TaskManager();
        String id = req.getParameter("id");
        int taskId = Integer.parseInt(id);
        String userId = req.getParameter("userId");
        int usrId = Integer.parseInt(userId);
        taskManager.changeTaskId(taskId, usrId);
        List<Task> allTask = taskManager.getAllTask();
        req.setAttribute("tasks", allTask);
        req.getRequestDispatcher("/manager.jsp").forward(req, resp);

    }
}

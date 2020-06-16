package manager;

import db.DBConnectionProvider;
import model.Task;
import model.TaskStatus;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Connection connection;

    public TaskManager() throws SQLException {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void TaskAdd(Task task) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO task(status,name,description,deadline,user_id) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, String.valueOf(task.getStatus()));
        preparedStatement.setString(2, task.getName());
        preparedStatement.setString(3, task.getDescription());
        preparedStatement.setString(4, sdf.format(task.getDeadline()));
        preparedStatement.setString(5, String.valueOf(task.getUserId()));

        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            int anInt = resultSet.getInt(1);
            task.setId(anInt);
        }
    }

    public List<Task> getTask(int id) throws SQLException, ParseException {
        List<Task> tasks = new ArrayList<Task>();
        String sql = "SELECT * FROM task WHERE user_id=" + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Task task = Task.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .deadline(resultSet.getString(4) == null ? null : sdf.parse(resultSet.getString(4)))
                    .status(TaskStatus.valueOf(resultSet.getString(5)))
                    .userId(resultSet.getInt(6))
                    .build();
            tasks.add(task);
        }
        return tasks;
    }

    public List<Task> changeStatus(int id, String status, int userId) throws SQLException, ParseException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `task` SET status=? WHERE id=?");
        preparedStatement.setString(1, status);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        List<Task> task = getTask(userId);
        return task;
    }
    public void changeTaskId(int id,int userId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `task` SET user_id=? WHERE id=?");
        preparedStatement.setInt(2, id);
        preparedStatement.setInt(1,userId);
        preparedStatement.executeUpdate();
    }
    public List<Task> getAllTask() throws SQLException, ParseException {
        List<Task> tasks = new ArrayList<Task>();
        String sql = "SELECT * FROM `task`";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Task task = Task.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .description(resultSet.getString(3))
                    .deadline(resultSet.getString(4) == null ? null : sdf.parse(resultSet.getString(4)))
                    .status(TaskStatus.valueOf(resultSet.getString(5)))
                    .userId(resultSet.getInt(6))
                    .build();
            tasks.add(task);
        }
        return tasks;
    }
}

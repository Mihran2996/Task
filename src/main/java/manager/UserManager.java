package manager;


import db.DBConnectionProvider;
import model.Task;
import model.TaskStatus;
import model.User;
import model.UserType;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserManager {
    Connection connection;
    public UserManager() throws SQLException {
        connection= DBConnectionProvider.getInstance().getConnection();
    }

    public void addUser(User user) {
        String sql = "INSERT INTO `user`(user_type,name,surname,email,password) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(user.getType()));
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            User byEmailAndPassword = getByEmailAndPassword(user.getEmail(),user.getPassword());
            if(!byEmailAndPassword.getEmail().equals(user.getEmail())){
                preparedStatement.executeUpdate();
            }
            ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    user.setId( rs.getInt(1));
                }
            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public User getByEmailAndPassword(String email, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE email=? AND password=? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return getUserFromResultSet(resultSet);
        }
        return null;
    }
    private User getUserFromResultSet(ResultSet resultSet) {
        User user = null;
        try {
            user = User.builder()
                    .id( resultSet.getInt(1))
                    .type(UserType.valueOf(resultSet.getString(2)))
                    .name(resultSet.getString(3))
                    .surname(resultSet.getString(4))
                    .email(resultSet.getString(5))
                    .password(resultSet.getString(6))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }
    public  void deleteById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE  FROM user WHERE id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
public List<User> getAllUsers() throws SQLException {
    List<User> users = new ArrayList<User>();
    String sql = "SELECT * FROM user ";
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery(sql);
    while (resultSet.next()) {
        users.add(getUserFromResultSet(resultSet));
    }
    return users;
}

}

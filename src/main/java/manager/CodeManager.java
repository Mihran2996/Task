package manager;

import db.DBConnectionProvider;
import model.Code;
import model.User;
import model.UserType;

import java.sql.*;

public class CodeManager {
    Connection connection;

    public CodeManager() throws SQLException {
        connection = DBConnectionProvider.getInstance().getConnection();

    }

    public int addCode(Code code) throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `password_code`(code,user_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, code.getCode());
            preparedStatement.setInt(2, code.getUserId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            code.setId(rs.getInt(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Code code1 = getCodeByCode(code.getCode());
        return code1.getId();
    }

    public Code getCodeByCode(int code) throws SQLException {
        String sql = "SELECT * FROM password_code WHERE code=? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, code);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Code code1 = null;
            return code1 = Code.builder()
                    .id(resultSet.getInt(1))
                    .code(resultSet.getInt(2))
                    .userId(resultSet.getInt(3))
                    .build();
        }
        return null;
    }

    public Code getCodeById(int id) throws SQLException {
        String sql = "SELECT * FROM password_code WHERE id=? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Code co = null;
        while (resultSet.next()) {

            co = Code.builder()
                    .id(resultSet.getInt(1))
                    .code(resultSet.getInt(2))
                    .userId(resultSet.getInt(3))
                    .build();
            return co;
        }
        return null;
    }

}

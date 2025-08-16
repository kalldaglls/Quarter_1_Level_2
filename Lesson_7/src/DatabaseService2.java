import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseService2 implements Service<UserCredentials> {


    @Override
    public Collection<UserCredentials> findAll() {
        Connection connection = null;
        try {
            connection = ConnectionUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userCredentials");

            List<UserCredentials> userCredentialsList = new ArrayList<UserCredentials>();
            while (resultSet.next()) {
                UserCredentials userCredentials = new UserCredentials(
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name")
                );
                userCredentialsList.add(userCredentials);
            }
            return userCredentialsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public Collection<UserCredentials> findAllH2() {
        return List.of();
    }

    @Override
    public UserCredentials findByLoginAndPassword(String login, String password) {
        Connection connection = null;
        try {
            connection = ConnectionUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userCredentials where login = '" + login + "' and password = '" + password + "'");

            List<UserCredentials> userCredentialsList = new ArrayList<UserCredentials>();
            while (resultSet.next()) {
                UserCredentials userCredentials = new UserCredentials(
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name")
                );
                userCredentialsList.add(userCredentials);
            }
            return userCredentialsList.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void update(UserCredentials userCredentials) {

    }

    @Override
    public boolean insert(UserCredentials userCredentials) {
        Connection connection = null;
        try {
            connection = ConnectionUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO userCredentials (login, password, name) VALUES (?, ?, ?)");
            preparedStatement.setString(1, userCredentials.getLogin());
            preparedStatement.setString(2, userCredentials.getPassword());
            preparedStatement.setString(3, userCredentials.getName());
            return preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
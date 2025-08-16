
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class AuthenticationService {
    private Set<Client> clients;
    private UserCredentials userCredentials;

    public AuthenticationService() {
        clients = Set.of(
                new Client("l1", "p1", "u1"),
                new Client("l2", "p2", "u2"),
                new Client("l3", "p3", "u3")
        );
    }

    public Client findByLoginAndPassword(String login, String password) {
        for (Client c : clients) {
            if (c.getLogin().equals(login) && c.getPassword().equals(password)) {
                return c;
            }
        }
        return null;
    }

    public UserCredentials findByLoginAndPasswordSQL(String login, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = ConnectionUtils.getConnection();
            System.out.println(connection.getMetaData());
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        DatabaseService2 databaseService2 = new DatabaseService2();
        userCredentials = databaseService2.findByLoginAndPassword(login, password);
        return userCredentials;
    }

    public Client findByLogin(String login) {
        for (Client c : clients) {
            if (c.getLogin().equals(login)) {
                return c;
            }
        }
        return null;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    static public class Client {
        private String login;
        private String password;
        private String name;

        public Client(String login, String password, String name) {
            this.login = login;
            this.password = password;
            this.name = name;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }
    }
}
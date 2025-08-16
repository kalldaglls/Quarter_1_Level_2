import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseService implements Service<Books> {


    @Override
    public Collection<Books> findAll() {
        Connection connection = null;
        try {
            connection = ConnectionUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book");

            List<Books> booksList = new ArrayList<Books>();
            while (resultSet.next()) {
                Books books = new Books(
                        resultSet.getInt("book_id"),
                        resultSet.getInt("author_Id"),
                        resultSet.getString("title"),
                        resultSet.getInt("genre_Id"),
                        resultSet.getBigDecimal("price"),
                        resultSet.getInt("amount")
                );
                booksList.add(books);
            }
            return booksList;
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
    public Collection<Books> findAllH2() {
        return List.of();
    }

    @Override
    public Books findByLoginAndPassword(String login, String password) {
        return null;
    }
//
//    @Override
//    public Books findById(int id) {
//        return null;
//    }

    @Override
    public void update(Books books) {

    }

    @Override
    public boolean insert(Books books) {
        Connection connection = null;
        try {
            connection = ConnectionUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book (author_id, title, genre_id, price, amount) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, books.getAuthor_Id());
            preparedStatement.setString(2, books.getTitle());
            preparedStatement.setInt(3, books.getGenre_Id());
            preparedStatement.setBigDecimal(4, books.getPrice());
            preparedStatement.setInt(5, books.getAmount());
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
import java.util.Collection;

public interface Service<T> {
    Collection<T> findAll();
    Collection<T> findAllH2();
    T findByLoginAndPassword(String login, String password);
    void update(T t);
    boolean insert(T t);
}

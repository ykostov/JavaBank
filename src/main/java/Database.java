import java.sql.SQLException;

public interface Database {
    public void createDb();
    public void addData(String username, String passwd, String email) throws SQLException;
    public boolean checkUsernameAndPassword(String username, String passwd);
    public boolean checkUsername(String username);
}

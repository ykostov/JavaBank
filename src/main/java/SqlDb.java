import java.sql.*;

public class SqlDb{

    Connection con = DriverManager.getConnection("jdbc:sqlite:/home/spooky/Videos/sqlite/usersdb.db");
    Statement statement = con.createStatement();


    public SqlDb() throws SQLException {
    }



    public void addData(String username, String passwd, String email) throws SQLException {

    try {
        String sql = "INSERT INTO users (name, password) VALUES ('" + username + "', '" + passwd + "');";
        statement.executeUpdate(sql);
        System.out.println("data created successfully");
    } catch (SQLException e)
    {
        e.printStackTrace();
    }


    }


    public boolean checkUsernameAndPassword(String username, String passwd) {

        try {
            String sql = "SELECT 1 FROM users WHERE name = '" + username + "' AND password = '" + passwd + "';";
            ResultSet rs = statement.executeQuery(sql);

            return rs.next();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }


    public boolean checkUsername(String username) {

        try {
            String sql = "SELECT 1 FROM users WHERE name = '" + username + "';";
            ResultSet rs = statement.executeQuery(sql);

            return rs.next();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }
}

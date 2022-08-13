import java.sql.*;

public class SqlDb{

    Connection con = DriverManager.getConnection("jdbc:sqlite:/home/spooky/Videos/sqlite/usersdb.db");
    //Statement statement = con.createStatement();


    public SqlDb() throws SQLException {
    }


    public void createDb() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (name STRING, password STRING, status STRING);";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.executeUpdate();
    }
    public void addData(String username, String passwd, String email, boolean isAdmin) throws SQLException {
        String status = isAdmin ? "admin" : "user";

    try {
        //String sql = "INSERT INTO users (name, password) VALUES ('" + username + "', '" + passwd + "');";

        String sql = "INSERT INTO users (name, password, status) VALUES (? , ? , ?);";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, passwd);
        ps.setString(3, status);

        ps.executeUpdate();

        System.out.println("data created successfully");
    } catch (SQLException e)
    {
        e.printStackTrace();
    }


    }


    public boolean checkUsernameAndPassword(String username, String passwd, boolean isAdmin) {
        String status = isAdmin ? "admin" : "user";
        try {

            String sql = "SELECT 1 FROM users WHERE name = ? AND password = ? AND status = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, passwd);
            ps.setString(3, status);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }


    public boolean checkUsername(String username, boolean isAdmin) {

        try {
            String status = isAdmin ? "admin" : "user";
            String sql = "SELECT 1 FROM users WHERE name = ? AND status = ? ;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, status);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }
}

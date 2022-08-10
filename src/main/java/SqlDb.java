import java.sql.*;

public class SqlDb{

    Connection con = DriverManager.getConnection("jdbc:sqlite:/home/spooky/Videos/sqlite/usersdb.db");
    //Statement statement = con.createStatement();


    public SqlDb() throws SQLException {
    }



    public void addData(String username, String passwd, String email) throws SQLException {

    try {
        //String sql = "INSERT INTO users (name, password) VALUES ('" + username + "', '" + passwd + "');";

        String sql = "INSERT INTO users (name, password) VALUES (? , ?);";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);
        ps.setString(2, passwd);

        ps.executeUpdate();

        System.out.println("data created successfully");
    } catch (SQLException e)
    {
        e.printStackTrace();
    }


    }


    public boolean checkUsernameAndPassword(String username, String passwd) {

        try {
            //String sql = "SELECT 1 FROM users WHERE name = '" + username + "' AND password = '" + passwd + "';";
            String sql = "SELECT 1 FROM users WHERE name = ? AND password = ? ;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, passwd);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }


    public boolean checkUsername(String username) {

        try {

            String sql = "SELECT 1 FROM users WHERE name = ? ;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }
}

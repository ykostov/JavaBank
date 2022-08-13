import java.sql.SQLException;

public class Database {
    private boolean isDbFile = false;
    private final Filedb filedb = new Filedb("users");
    private final SqlDb sdb = new SqlDb();

    public boolean switcher()
    {
        isDbFile = !isDbFile;
        return isDbFile;
    }


    public Database() throws SQLException {
        sdb.createDb();
        filedb.createDb();
    }

    public void addData(String username, String passwd, String email, boolean isAdmin) throws SQLException
    {
        if (isDbFile)
        {
            filedb.addData(username, passwd, email, isAdmin);
            return;
        }
        sdb.addData(username, passwd, email, isAdmin);
    }
    public boolean checkUsernameAndPassword(String username, String passwd, boolean isAdmin)
    {
        if (isDbFile)
        {
            return filedb.checkUsernameAndPassword(username, passwd, isAdmin);

        }
        return sdb.checkUsernameAndPassword(username, passwd, isAdmin);

    }
    public boolean checkUsername(String username, boolean isAdmin)
    {
        if (isDbFile)
        {
            return filedb.checkUsername(username, isAdmin);

        }
        return sdb.checkUsername(username, isAdmin);

    }
}

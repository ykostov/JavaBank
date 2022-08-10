import lombok.Data;

import java.sql.SQLException;

public class Database {
    private boolean isDbFile = false;
    private boolean isAdminDb = false;
    private final Filedb filedb = new Filedb("users");
    private final SqlDb sdb = new SqlDb();

    public boolean switcher()
    {
        isDbFile = !isDbFile;
        return isDbFile;
    }


    public Database(boolean isAdminDb) throws SQLException {
        this.isAdminDb = isAdminDb;
    }

    public void addData(String username, String passwd, String email) throws SQLException
    {
        if (isDbFile)
        {
            filedb.addData(username, passwd, email);
            return;
        }
        sdb.addData(username, passwd, email);
    }
    public boolean checkUsernameAndPassword(String username, String passwd)
    {
        if (isDbFile)
        {
            return filedb.checkUsernameAndPassword(username, passwd);

        }
        return sdb.checkUsernameAndPassword(username, passwd);

    }
    public boolean checkUsername(String username)
    {
        if (isDbFile)
        {
            return filedb.checkUsername(username);

        }
        return sdb.checkUsername(username);

    }
}

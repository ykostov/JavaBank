import java.io.*;
import java.util.logging.Logger;

public class Database {
    private String name;
    private String passwd;
    private String email;

    public Database(String name, String passwd, String email) {
        this.name = name;
        this.passwd = passwd;
        this.email = email;
    }

    public void createDb()
    {
        // checking if database.txt exists.

        try {
            FileReader fr = new FileReader("database.txt");
            System.out.println("file exists");
        }
        catch (FileNotFoundException e) {
            try
            {
                FileWriter fr = new FileWriter("database.txt");
            }
            catch (Exception ex)
            {
                // write data to looger
            }

            System.out.println("file created");
        }
    }

    public void addData() {


        //adding data to database.txt

        try {
            RandomAccessFile raf = new RandomAccessFile("database.txt", "rw");
            raf.writeBytes("Username:" + name + "\r\n");
            raf.writeBytes("Password:" + passwd + "\r\n");
            raf.writeBytes("Email:" + email + "\r\n");

        } catch (FileNotFoundException e)
        {
            // write data in logger
        }
        catch (Exception e)
        {
            //write data in logger
        }

    }


}

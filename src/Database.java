import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
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
                System.out.println("file created");
            }
            catch (Exception ex)
            {
                // write data to logger
            }

        }
    }

    public void addData() {


        //adding data to database.txt

        try {
            RandomAccessFile raf = new RandomAccessFile("database.txt", "rw");
            raf.writeBytes("Username:" + name + "Password:" + passwd + "Email:" + email + "\r\n");


        } catch (FileNotFoundException e)
        {
            // write data in logger
        } catch (IOException e) {
            //throw new RuntimeException(e); write data in logger
        }

    }

    public void checkData(String username, String passwd) throws IOException {

        try
        {
            String customPatternForSearchingInDb = "Username:" + username + "Password:" + passwd;
            List<String> lines = Files.readAllLines(Path.of("database.txt"));
            for (String line : lines) {
                if (line.contains(customPatternForSearchingInDb)) {
                    System.out.println("good");
                }
            }
        }
        catch (Exception e)
        {
            //write data in logger
        }




    }


}

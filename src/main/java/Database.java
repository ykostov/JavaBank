import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/* This class creates a Database with given by employee/admin nameOfDB
Then, createDb() is called.
*/
public class Database {

    private String nameOfDb;

    public Database(String nameOfDb) {
        this.nameOfDb = nameOfDb;
    }

    // this method checks if the database with the given nameOfDb variable exists.
    // For example if nameOfDb is "example" the database requires a file called example.txt used for a simple DB.
    // If example.txt is not found, the FileWriter creates it.

    public void createDb()
    {
        try {
            FileReader fr = new FileReader(nameOfDb + ".txt");
            System.out.println("database exists");
        }
        catch (FileNotFoundException e) {
            try
            {
                FileWriter fr = new FileWriter(nameOfDb + ".txt");
                System.out.println("database created");
            }
            catch (Exception ex)
            {
                // write data to logger
            }

        }
    }

    /*
    This method is only used when registering users.
    By given username, passwd and email it creates a new row used to store users' identifiers.
    All rows are saved by identical way, e.g. Username:mitkoPassword12345Emailmitko@gmail.com
    Thus they are later used for searching.

     */

    public void addData(String username, String passwd, String email) {
        String dataToBeWritten = "Username:" + username + "Password:" + passwd + "Email:" + email + "\r\n";

        try {

            Writer output = new FileWriter(nameOfDb + ".txt", true);
            output.write(dataToBeWritten);
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }

       // System.out.println("User is successfully registered");

    }

    // this method is used to check if username with password exists in db

    public boolean checkUsernameAndPassword(String username, String passwd) {

        try
        {
            String customPatternForSearchingInDb = "Username:" + username + "Password:" + passwd + "Email";
            List<String> lines = Files.readAllLines(Path.of(nameOfDb + ".txt"));

            for (String line : lines) {
                if (line.contains(customPatternForSearchingInDb)) {
                    //there is a user
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            //write data in logger
        }

        return false;

    }


    public boolean checkUsername(String username) {

        try
        {
            String customPatternForSearchingInDbForUserName = "Username:" + username + "Password:";
            List<String> lines = Files.readAllLines(Path.of(nameOfDb + ".txt"));

            for (String line : lines) {
                if (line.contains(customPatternForSearchingInDbForUserName)) {
                    //there is a user
                    return true;
                }
            }
        }
        catch (Exception e)
        {
            //write data in logger
        }

        return false;

    }


}

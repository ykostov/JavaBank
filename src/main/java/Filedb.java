import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Filedb{

    private String nameOfDb;

    public Filedb(String nameOfDb) {
        this.nameOfDb = nameOfDb;
        createDb();
    }

    // this method checks if the database with the given nameOfDb variable exists.
    // For example if nameOfDb is "example" the database requires a file called example.txt used for a simple DB.
    // If example.txt is not found, the FileWriter creates it.

    public void createDb()
    {
        try {
            FileReader fr = new FileReader(nameOfDb + ".txt");
        }
        catch (FileNotFoundException e) {
            try
            {
                FileWriter fr = new FileWriter(nameOfDb + ".txt");
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

    public void addData(String username, String passwd, String email, boolean isAdmin) {
        String dataToBeWritten = "Username:" + username + "Password:" + passwd + "Email:" + email + "\r\n";

        try {
            Writer output;
            if (isAdmin) { output = new FileWriter("admins.txt", true); }
            else { output = new FileWriter("users.txt", true);}
            output.write(dataToBeWritten);
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }

       // System.out.println("User is successfully registered");

    }

    // this method is used to check if username with password exists in db

    public boolean checkUsernameAndPassword(String username, String passwd, boolean isAdmin) {

        try
        {
            String customPatternForSearchingInDb = "Username:" + username + "Password:" + passwd + "Email";
            List<String> lines;
            if (isAdmin) {lines = Files.readAllLines(Path.of("admins" + ".txt")); }
            else { lines = Files.readAllLines(Path.of("users" + ".txt"));}



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
            e.getStackTrace();
            System.out.println(e.getMessage());
        }

        return false;

    }


    public boolean checkUsername(String username, boolean isAdmin) {

        try
        {
            String customPatternForSearchingInDbForUserName = "Username:" + username + "Password:";
            List<String> lines;
            if (isAdmin) {lines = Files.readAllLines(Path.of("admins" + ".txt")); }
            else { lines = Files.readAllLines(Path.of("users" + ".txt"));}

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

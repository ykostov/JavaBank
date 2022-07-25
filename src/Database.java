import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Database {

    String nameOfDb;

    public Database(String nameOfDb) {
        this.nameOfDb = nameOfDb;
    }

    public void createDb()
    {
        // checking if database.txt exists.

        try {
            FileReader fr = new FileReader(nameOfDb + ".txt");
            System.out.println("file exists");
        }
        catch (FileNotFoundException e) {
            try
            {
                FileWriter fr = new FileWriter(nameOfDb + ".txt");
                System.out.println("file created");
            }
            catch (Exception ex)
            {
                // write data to logger
            }

        }
    }

    public void addData(String username, String passwd, String email) {
        String dataToBeWritten = "Username:" + username + "Password:" + passwd + "Email:" + email + "\r\n";

        //adding data to database.txt

        try {
            // Creates a Writer using FileWriter
            Writer output = new FileWriter(nameOfDb + ".txt", true);
            output.write(dataToBeWritten);
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }

    }

    public boolean checkData(String username, String passwd) {

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


}

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

public class Database {


    public Database() {

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

    public void addData(String username, String passwd, String email) {
        String dataToBeWritten = "Username:" + username + "Password:" + passwd + "Email:" + email + "\r\n";

        //adding data to database.txt

        try {
            // Creates a Writer using FileWriter
            Writer output = new FileWriter("database.txt", true);
            output.write(dataToBeWritten);
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }

    }

    public void checkData(String username, String passwd) throws IOException {
        boolean userFound = false;

        try
        {
            String customPatternForSearchingInDb = "Username:" + username + "Password:" + passwd + "Email";
            List<String> lines = Files.readAllLines(Path.of("database.txt"));
            for (String line : lines) {
                if (line.contains(customPatternForSearchingInDb)) {
                    System.out.println("there is a user with that password");
                    userFound = true;
                    break;
                }
            }
        }
        catch (Exception e)
        {
            //write data in logger
        }

        if (!userFound)
        {
            System.out.println("there is NO user with that password");
        }


    }


}

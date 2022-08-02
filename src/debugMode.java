import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

public class debugMode {

    public static void createDebugFile()
    {
        try {
            FileReader fr = new FileReader("debugFile.txt");
            System.out.println("database exists");
        }
        catch (FileNotFoundException e) {
            try
            {
                FileWriter fr = new FileWriter("debugFile.txt");
                System.out.println("database created");
            }
            catch (Exception ex)
            {
                // write data to logger
            }
        }
    }

    public static void addDataToLogger(String date, String info) {
        try {
            Writer output = new FileWriter("debugFile.txt", true);
            output.write(date + " " + info + "\r\n");
            output.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }
    }

}

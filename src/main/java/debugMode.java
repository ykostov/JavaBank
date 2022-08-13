import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Arrays;

public class debugMode {

    private static boolean isWorking = false;

    public static boolean switcher()
    {
        isWorking = !isWorking;
        return isWorking;
    }

    public static void createDebugFile()
    {
        try {
            FileReader fr = new FileReader("debugFile.txt");

        }
        catch (FileNotFoundException e) {
            try
            {
                FileWriter fr = new FileWriter("debugFile.txt");

            }
            catch (Exception ex)
            {
                // write data to logger
            }
        }
    }

    public static void addDataToLogger(String date, String info) {
        if (isWorking) {
            try {
                Writer output = new FileWriter("debugFile.txt", true);
                output.write(date + " " + info + "\r\n");
                output.close();
            } catch (Exception e) {
                debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "ERROR: at addDataToLogger: " + Arrays.toString(e.getStackTrace()));
            }
        }
    }


}

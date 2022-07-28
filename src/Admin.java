import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Admin {

    private static final boolean isAdmin = true;
    public static void main() throws IOException {
        Database adminDb = new Database("admins");
        adminDb.createDb();
        while (true)
        {
            System.out.println("Login/Register/Exit");
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            if (userInput.toLowerCase().trim().startsWith("log"))
            {
                Extranet.login(adminDb, isAdmin);
                break;
            } else if (userInput.toLowerCase().trim().startsWith("reg")) {
                Extranet.register(adminDb, isAdmin);
                break;
            } else if (userInput.toLowerCase().trim().startsWith("exit")) {
                break;
            }
            else {
                System.out.println("try again..");
            }
        }

    }
}


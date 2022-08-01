import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final boolean isAdmin = false;
    public static void main(String[] args) throws IOException {


        Database newdb = new Database("users");
        newdb.createDb();

        Menu.ASCII();


        openMainMenu(newdb);


    }

    private static void openMainMenu(Database newdb) throws IOException {
        while (true)
        {

        System.out.println(Message.WELCOME);  // Hello World
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();

            if (userInput.toLowerCase().trim().startsWith("log"))
            {
                Extranet.login(newdb, isAdmin);

            }
            else if (userInput.toLowerCase().trim().startsWith("reg"))
            {
                Extranet.register(newdb, isAdmin);

            }
            else if (userInput.toLowerCase().trim().startsWith("admin"))
            {
                Admin.main(newdb);

            }
            else if (userInput.toLowerCase().trim().startsWith("exit"))
            {
                break;
            }
            else
            {
                System.out.println(Message.BADINPUT);
            }
        }
    }


}

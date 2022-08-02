import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final boolean isAdmin = false;
    private static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws IOException {


        Database newdb = new Database("users");
        newdb.createDb();

//        Menu.ASCII();

        Messages.setupMap();
        openMainMenu(newdb);


    }

    private static void chooseLanguage()
    {
        while(true)
        {
            System.out.println("Choose language - en for english or bg for bulgarian");
            String languageInput = scan.nextLine();
            if (languageInput.startsWith("e"))
            {
                Extranet.setCurrentLanguage("en");
                break;
            }
            else if (languageInput.startsWith("b"))
            {
                Extranet.setCurrentLanguage("bg");
                break;
            }
            else
            {
                System.out.println("I did not understand you. Plz try again");
            }
        }

    }

    private static void openMainMenu(Database newdb) throws IOException {
        while (true)
        {

        System.out.println(Messages.getMessage(Extranet.getCurrentLanguage()+"-welcome"));  // Hello World
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
            else if (userInput.toLowerCase().trim().startsWith("lang"))
            {
                chooseLanguage();
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

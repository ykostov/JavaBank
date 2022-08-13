import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scan = new Scanner(System.in);

  static Database newdb;

    static {
        try {
            newdb = new Database();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

        Menu.ASCII();

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

    private static void openMainMenu(Database newdb) {
        while (true) {

            System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-welcome"));  // Hello World
            String userInput = scan.nextLine();
            try {


                if (userInput.toLowerCase().trim().startsWith("log")) {
                    Extranet.login(newdb, false);

                } else if (userInput.toLowerCase().trim().startsWith("reg")) {
                    Extranet.register(newdb, false);

                } else if (userInput.toLowerCase().trim().startsWith("admin")) {
                    Admin.main(newdb);

                } else if (userInput.toLowerCase().trim().startsWith("lang")) {
                    chooseLanguage();
                }
                else if (userInput.toLowerCase().trim().startsWith("db")) {
                    changeDb();
                }else if (userInput.toLowerCase().trim().startsWith("exit")) {
                    break;
                } else {
                    System.out.println(Message.BADINPUT);
                    debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "ERROR: Bad input at openMainMenuBad");
                }
            } catch (Exception e)
            {
                debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "ERROR: Very very bad: " + e.getMessage() + Arrays.toString(e.getStackTrace()));
            }
        }
    }

    private static void changeDb() {
        if (newdb.switcher())
        {
            System.out.println("your db is now in File mode");
            return;
        }
        System.out.println("Your db is now in SQLite mode");
    }

}

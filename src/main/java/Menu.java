import java.io.IOException;
import java.util.Scanner;

// this menu class is used for all big menus in JavaBank

public class Menu {
    private static final Scanner scan = new Scanner(System.in);
    public static void mainMenu(Filedb newdb) throws IOException {

        while(true) {
            System.out.println(Messages.getMessage("separator"));
         // System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-mainMenu"));
            System.out.println(Message.MAINMENU);
            System.out.println("You currently have " + ATM.getMoneyATM() + " " + ATM.getCurrencyInATM() + " money in ATM. ");

            String userInput = scan.nextLine();

            if (userInput.toLowerCase().trim().startsWith("acc")) {
                Account acc = new Account();
                System.out.println(Messages.getMessage("separator"));
                debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "Account opened by user - " + Extranet.getCurrentUserName());
                acc.openAccount(newdb);
            } else if (userInput.startsWith("add mo")) { ATM.addMoney(); }

            else if (userInput.toLowerCase().trim().startsWith("exc")) { ATM.exchangeMoney(); }

            else if (userInput.startsWith("exit")) { break; }
        }
    }

    public static void adminMenu() throws IOException {
        System.out.println(Message.SEPARATOR);
        while(true)
        {
            System.out.println("hello admin " + Extranet.getCurrentUserName());
            System.out.println("'block' or 'delete' or 'percent' or start 'debug' or 'deldebug' or 'exit' an account:");
            Scanner scan = new Scanner(System.in);
            String adminInput = scan.nextLine().toLowerCase().trim();
            if (adminInput.startsWith("bl")) {
                Admin.blockAccount();
            } else if (adminInput.startsWith("del")) {
                Admin.deleteAccount();
            } else if (adminInput.startsWith("per")) {
                Admin.choosePercent();
            }
            else if (adminInput.startsWith("deb")) {
                debugMode.createDebugFile();
                System.out.println(debugMode.switcher() ? "it is now working":"it is now stopped");
            }
//            else if (adminInput.startsWith("deldeb")) {
//                System.out.println("got here0");
//                debugMode.delLogger();
//            }
            else if (adminInput.startsWith("exit"))
            {
                break;
            }
        }

    }

    public static void ASCII()
    {
        System.out.println("\n" +
                "\n" +
                "    /$$$$$                                /$$$$$$$                      /$$      \n" +
                "   |__  $$                               | $$__  $$                    | $$      \n" +
                "      | $$  /$$$$$$  /$$    /$$  /$$$$$$ | $$  \\ $$  /$$$$$$  /$$$$$$$ | $$   /$$\n" +
                "      | $$ |____  $$|  $$  /$$/ |____  $$| $$$$$$$  |____  $$| $$__  $$| $$  /$$/\n" +
                " /$$  | $$  /$$$$$$$ \\  $$/$$/   /$$$$$$$| $$__  $$  /$$$$$$$| $$  \\ $$| $$$$$$/ \n" +
                "| $$  | $$ /$$__  $$  \\  $$$/   /$$__  $$| $$  \\ $$ /$$__  $$| $$  | $$| $$_  $$ \n" +
                "|  $$$$$$/|  $$$$$$$   \\  $/   |  $$$$$$$| $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$\n" +
                " \\______/  \\_______/    \\_/     \\_______/|_______/  \\_______/|__/  |__/|__/  \\__/\n" +
                "                                                                                 \n" +
                "                                                                                 \n" +
                "                                                                                 \n" +
                "\n");
    }
}

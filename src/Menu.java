import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Scanner;

// this menu class is used for all big menus in JavaBank

public class Menu {
    public static void mainMenu(Database newdb) throws IOException {

        while(true) {

            System.out.println(Message.MAINMENU);
            System.out.println(Message.CURRENTMONEY);

            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();

            if (userInput.toLowerCase().trim().startsWith("acc")) {
                Account acc = new Account();
                acc.setupAccount(newdb);
            } else if (userInput.startsWith("add mo")) { ATM.addMoney(); }

            else if (userInput.toLowerCase().trim().startsWith("exc")) { ATM.exchangeMoney(); }

            else if (userInput.startsWith("exit")) { break; }
        }
    }

    public static void adminMenu() throws IOException {
        System.out.println("hello admin " + Extranet.getCurrentUserName());
        System.out.println("'block' or 'delete' an account:");
//        String username = Database.chooseUsername();
//         int fileCount = acc.getFileCount();
        Scanner scan = new Scanner(System.in);
        String adminInput = scan.nextLine().toLowerCase().trim();
         if (adminInput.startsWith("bl")) {
               Admin.blockAccount();
           } else if (adminInput.startsWith("del")) {
             Admin.deleteAccount();
               //Admin.delAccount(username);
           }


    }
}

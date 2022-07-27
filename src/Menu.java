import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

// this menu class is used for all big menus in JavaBank

public class Menu {
    public static void mainMenu(Database newdb) throws IOException {


        while(true) {
            System.out.println(Message.MAINMENU + " " + Extranet.getMoneyATM() + " " + Extranet.getCurrencyInATM());

            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            long moneyInput;


            if (userInput.startsWith("new acc")) {
                Account acc = new Account();
                acc.setupAccount(newdb);
            } else if (userInput.startsWith("add mo")) {
                Scanner scan1 = new Scanner(System.in);
                System.out.println("Enter currency: BGN or RSD?");
                String chosenCurrency;
                chosenCurrency = scan.nextLine().toUpperCase().trim();
                if (chosenCurrency.equals("BGN") || chosenCurrency.equals("RSD")) {

                    System.out.println(Message.HOWMUCH);

                    moneyInput = scan1.nextLong();
                    Extranet.setMoneyATM(BigInteger.valueOf(moneyInput));
                    Extranet.setCurrencyInATM(chosenCurrency);
                    System.out.println(Message.CURRENTMONEY + "" + Extranet.getMoneyATM() + " " + Extranet.getCurrencyInATM());
                }
                else
                {
                    System.out.println("Please try again");
                }
            }
            else if (userInput.startsWith("exit"))
            {
                break;
            }
        }
    }
}

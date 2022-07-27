import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
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
                    Extranet.setMoneyATM(BigDecimal.valueOf(moneyInput));
                    Extranet.setCurrencyInATM(chosenCurrency);
                    System.out.println(Message.CURRENTMONEY + "" + Extranet.getMoneyATM() + " " + Extranet.getCurrencyInATM());
                }
                else
                {
                    System.out.println("Please try again");
                }
            }

            else if (userInput.startsWith("change"))
            {
                if (Extranet.getCurrencyInATM().equalsIgnoreCase("BGN"))
                {
                    System.out.println("You currency in ATM is set to BGN. You have " + Extranet.getMoneyATM() + " BGN money. Would you like to exchange them to RSD? (1bgn = 60.11 RSD), (yes | no)");
                    String userInputForYes = scan.nextLine();
                    if (userInputForYes.toLowerCase().startsWith("y"))
                    {
                        BigDecimal newMoney = Extranet.getMoneyATM().multiply(BigDecimal.valueOf(60.11));
                        Extranet.setMoneyATM(newMoney);
                        Extranet.setCurrencyInATM("RSD");
                    }
                }
                else if (Extranet.getCurrencyInATM().equalsIgnoreCase("RSD"))
                {
                    System.out.println("You currency in ATM is set to RSD. You have " + Extranet.getMoneyATM() + " RSD money. Would you like to exchange them to BGN? (1rsd = 0.017 bgn), (yes | no)");
                    String userInputForYes = scan.nextLine();
                    if (userInputForYes.toLowerCase().startsWith("y"))
                    {
                        BigDecimal newMoney = Extranet.getMoneyATM().multiply(BigDecimal.valueOf(0.017));
                        Extranet.setMoneyATM(newMoney);
                        Extranet.setCurrencyInATM("BGN");
                    }
                }
            }

            else if (userInput.startsWith("exit"))
            {
                break;
            }
        }
    }
}

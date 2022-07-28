import java.math.BigDecimal;
import java.util.Scanner;

public class ATM {

    private static BigDecimal moneyATM = BigDecimal.valueOf(0);
    private static String currencyInATM = "";

    public static BigDecimal getMoneyATM() {
        return moneyATM;
    }

    public static void setMoneyATM(BigDecimal moneyATM) {
        ATM.moneyATM = moneyATM;
    }

    public static String getCurrencyInATM() {
        return currencyInATM;
    }

    public static void setCurrencyInATM(String currencyInATM) {
        ATM.currencyInATM = currencyInATM;
    }

    public static void addMoney()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter currency: BGN or RSD?");
        String chosenCurrency;
        chosenCurrency = scan.nextLine().toUpperCase().trim();
        if (chosenCurrency.equals("BGN") || chosenCurrency.equals("RSD")) {

            while (true) {

                System.out.println(Message.HOWMUCH);
                String moneyInput1 = scan.nextLine();
                try {
                    double moneyInput = Double.parseDouble(moneyInput1);
                    ATM.setMoneyATM(BigDecimal.valueOf(moneyInput));
                    ATM.setCurrencyInATM(chosenCurrency);
                    System.out.println(Message.CURRENTMONEY + "" + ATM.getMoneyATM() + " " + ATM.getCurrencyInATM());
                    break;
                } catch (Exception e) {
                    System.out.println("Please, enter only numbers");
                }
            }
        }
        else
        {
            System.out.println("Please try again");
        }
    }

    public static void exchangeMoney()
    {
        Scanner scan = new Scanner(System.in);

        if (ATM.getCurrencyInATM().equalsIgnoreCase("BGN"))
        {
            System.out.println("You currency in ATM is set to BGN. You have " + ATM.getMoneyATM() + " BGN money. Would you like to exchange them to RSD? (1bgn = 60.11 RSD), (yes | no)");
            String userInputForYes = scan.nextLine();
            if (userInputForYes.toLowerCase().startsWith("y"))
            {
                BigDecimal newMoney = ATM.getMoneyATM().multiply(BigDecimal.valueOf(60.11));
                ATM.setMoneyATM(newMoney);
                ATM.setCurrencyInATM("RSD");
            }
        }
        else if (ATM.getCurrencyInATM().equalsIgnoreCase("RSD"))
        {
            System.out.println("You currency in ATM is set to RSD. You have " + ATM.getMoneyATM() + " RSD money. Would you like to exchange them to BGN? (1rsd = 0.017 bgn), (yes | no)");
            String userInputForYes = scan.nextLine();
            if (userInputForYes.toLowerCase().startsWith("y"))
            {
                BigDecimal newMoney = ATM.getMoneyATM().multiply(BigDecimal.valueOf(0.017));
                ATM.setMoneyATM(newMoney);
                ATM.setCurrencyInATM("BGN");
            }
        }
        else
        {
            System.out.println("you don't have money in ATM");
        }
    }
}

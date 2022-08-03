import java.math.BigDecimal;
import java.util.Scanner;

public class ATM {

    private static final Scanner scan = new Scanner(System.in);
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
        System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-enterCurrency"));
        String chosenCurrency;
        chosenCurrency = scan.nextLine().toUpperCase().trim();
        if (chosenCurrency.toLowerCase().trim().startsWith("bg") || chosenCurrency.toLowerCase().trim().startsWith("rs")) {

            while (true) {

                System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-howMuchToInsert"));
                String moneyInput1 = scan.nextLine();
                try {
                    double moneyInput = Double.parseDouble(moneyInput1);
                    ATM.setMoneyATM(BigDecimal.valueOf(moneyInput));
                    ATM.setCurrencyInATM(chosenCurrency);
                    System.out.println("You currently have " + ATM.getMoneyATM() + " " + ATM.getCurrencyInATM() + " money in ATM. ");
                    break;
                } catch (Exception e) {
                    System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-onlyNumbers"));
                }
            }
        }
        else
        {
            System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-tryAgain"));
        }
    }

    public static void exchangeMoney()
    {

        if (ATM.getCurrencyInATM().equalsIgnoreCase("BGN"))
        {
            System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-currencyInBgn"));
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
            System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-currencyInRsd"));
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
            System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-noMoneyInAtm"));
        }
    }
}

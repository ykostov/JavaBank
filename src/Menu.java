import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Menu {
    public static void mainMenu() throws IOException {
        System.out.println("type 'new account' to create an account or 'add money' to add money in ATM (obviously)");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        long moneyInput;


        if (userInput.startsWith("new acc"))
        {
            Account acc = new Account();
            acc.setupAccount();
        }
        else if (userInput.startsWith("add mo"))
        {
            System.out.println("how much?");
            Scanner scan1 = new Scanner(System.in);
            moneyInput = scan1.nextLong();
            Extranet.setMoneyATM(BigInteger.valueOf(moneyInput));
            System.out.println("current money in ATM: " + Extranet.getMoneyATM());
        }
    }
}

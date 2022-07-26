import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Menu {
    public static void mainMenu() throws IOException {


        while(true) {
            System.out.println(Message.MAINMENU + " " + Extranet.getMoneyATM());

            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            long moneyInput;


            if (userInput.startsWith("new acc")) {
                Account acc = new Account();
                acc.setupAccount();
            } else if (userInput.startsWith("add mo")) {
                System.out.println(Message.HOWMUCH);
                Scanner scan1 = new Scanner(System.in);
                moneyInput = scan1.nextLong();
                Extranet.setMoneyATM(BigInteger.valueOf(moneyInput));
                System.out.println(Message.CURRENTMONEY + "" + Extranet.getMoneyATM());
            }
            else if (userInput.startsWith("exit"))
            {
                break;
            }
        }
    }
}

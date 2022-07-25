import java.util.Scanner;

public class Menu {
    public static void mainMenu()
    {
        System.out.println("type 'new account' to create an account");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        if (userInput.startsWith("new acc"))
        {
            Account acc = new Account();
            acc.setupAccount();
        }
    }
}

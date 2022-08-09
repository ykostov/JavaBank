import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Admin {

    private static final boolean isAdmin = true;
    private static final Scanner scan = new Scanner(System.in);


    public static void main(Filedb newdb) throws IOException {
        Filedb adminDb = new Filedb("admins");
        adminDb.createDb();
        while (true)
        {
            System.out.println("Login | Register | Exit");
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            if (userInput.toLowerCase().trim().startsWith("log"))
            {
                Extranet.login(adminDb, isAdmin);
                break;
            } else if (userInput.toLowerCase().trim().startsWith("reg")) {
                Extranet.register(adminDb, isAdmin);
                break;
            } else if (userInput.toLowerCase().trim().startsWith("exit")) {
                break;
            }
            else {
                System.out.println("try again..");
            }
        }

    }

    public static void blockAccount() throws IOException {
        Account acc = new Account();
        while (true) {

        System.out.println("enter username:");
        String usernameInput = scan.nextLine();
        Extranet.setCurrentUserName(usernameInput);
        acc.countAccountsCreatedForUser();
        System.out.println("choose an account to block (1, 2 or 3)");
        acc.listAccounts();
        if (acc.DoesUserHaveAnAccount) {
            System.out.println("choose account to block");
            String accountNumber = scan.nextLine();
            try {
                acc.writeDataInAccount("block", "blocked", usernameInput, accountNumber);
                System.out.println("Account successfully blocked");
                break;
            } catch (Exception e) {
                System.out.println("bad input! Try again:");
            }

        }
    }

    }

    public static void deleteAccount() throws IOException {
        System.out.println("enter username:");
        String usernameInput = scan.nextLine();
        Extranet.setCurrentUserName(usernameInput);
        Account acc = new Account();
        acc.countAccountsCreatedForUser();
        while(true)
        {
            System.out.println("choose an account to delete (1, 2 or 3) (or exit). The account must have 0 money of any type");
            acc.listAccounts();
            String accountNumber = scan.nextLine();
            try
            {
                if (acc.ifAccountHasNoMoney(usernameInput, accountNumber))
                {
                    System.out.println("go ahead");
                    acc.deleteAccount(usernameInput, accountNumber);
                    System.out.println("Account successfully deleted");
                    break;

                }
                else if(accountNumber.startsWith("exit"))
                {
                    break;
                }
                else
                {
                    System.out.println("money in account!");
                }
            } catch (Exception e)
            {
                System.out.println("bad input!! Try again");
            }
        }
    }

    public static void choosePercent() {
        while(true)
        {
            System.out.println("current percent: " + Extranet.getCurrentPercent() + "%");
            System.out.println("Enter new percent - (only number!)");
            String percentInput = scan.nextLine();
            String percentRegex = "[0-9]";
            Pattern pattern = Pattern.compile(percentRegex);
            Matcher matcher = pattern.matcher(percentInput);
            if (matcher.matches())
            {
                Extranet.setCurrentPercent(Integer.parseInt(percentInput));
                break;
            }
            else if(percentInput.startsWith("exit"))
            {
                break;
            }
            else
            {
                System.out.println("bad input. try again");
            }
        }

    }

}


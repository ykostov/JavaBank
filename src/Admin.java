import java.io.*;
import java.util.Scanner;

public class Admin {

    private static final boolean isAdmin = true;


    public static void main(Database newdb) throws IOException {
        Database adminDb = new Database("admins");
        adminDb.createDb();
        while (true)
        {
            System.out.println("Login/Register/Exit");
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
        acc.countAccountsCreatedForUser();
//        int fileCount = acc.getFileCount();
//
//        System.out.println("choose an account:");
//
//        for (int i = 0; i < fileCount; i++) {
//            System.out.print("acc" + i + " ");
//        }
//        Scanner scan = new Scanner(System.in);
//        String userInput = scan.nextLine();
        System.out.println("got here1");
        acc.writeDataInAccount("block", "blocked", "mitko", "1");
    }

    public static void deleteAccount() throws IOException {
        String username = "mitko";
        Extranet.setCurrentUserName(username);
        Account acc = new Account();
        acc.countAccountsCreatedForUser();
        while(true)
        {
            System.out.println("choose an account to delete (1, 2 or 3). The account must have 0 money of any type");
            acc.listAccounts();
            Scanner scan = new Scanner(System.in);
            String accountNumber = scan.nextLine();
            if (acc.ifAccountHasNoMoney(username, accountNumber))
            {
                System.out.println("go ahead");
                acc.deleteAccount(username, accountNumber);
                System.out.println("ended?");

            }
            else
            {
                System.out.println("money in account!");
            }
        }




    }


}


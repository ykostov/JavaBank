import java.util.Scanner;

public class Account {

    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Account.username = username;
    }

    public static void login(Database newdb)
    {
        String usernameInput;
        String passwdInput;
        Scanner scan = new Scanner(System.in);

        while(true) {
            System.out.println("Enter username, then password");
            usernameInput = scan.nextLine();
            if (usernameInput.startsWith("stop"))
            {
                break;
            }
            passwdInput = scan.nextLine();

            if (newdb.checkUsernameAndPassword(usernameInput, passwdInput)) {
                System.out.println("You have logged in successfully");
                break;
            }
            else {
                System.out.println("Incorrect password, try again or enter 'stop'");
            }
        }
    }

    public static void register(Database newdb) {


        Scanner scan = new Scanner(System.in);
        while(true)
        {
            System.out.print("Enter username: ");
            String usernameInput = scan.nextLine();
            System.out.print("Enter password: ");
            String passwdInput = scan.nextLine();
            System.out.print("Enter email: ");
            String emailInput = scan.nextLine();

            if ((newdb.checkUsername(usernameInput))) {
                System.out.println("Username is already taken, try again");
            } else {
                newdb.addData(usernameInput, passwdInput, emailInput);
                break;
            }
        }
    }
}



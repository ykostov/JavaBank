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

            if (newdb.checkData(usernameInput, passwdInput)) {
                System.out.println("You have logged in successfully");
                break;
            }
            else {
                System.out.println("Incorrect password, try again or enter 'stop'");
            }
        }
    }
}

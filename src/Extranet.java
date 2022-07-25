import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Extranet {

    private static String currentUserName;
    private static BigInteger moneyATM;
    private static boolean isAdmin = false;

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static BigInteger getMoneyATM() {
        return moneyATM;
    }

    public static void setMoneyATM(BigInteger moneyATM) {
        Extranet.moneyATM = moneyATM;
    }

    public static boolean getIsAdmin() {
        return isAdmin;
    }



    public static void login(Database newdb) throws IOException {
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
                // creation of new client dir.
                try {
                    Path path = Paths.get(System.getProperty("user.dir") + "/" + usernameInput);
                    Files.createDirectories(path);
                    currentUserName = usernameInput;

                } catch (Exception e)
                {
                    System.err.println("Failed to create directory!" + e.getMessage());
                }
                Menu.mainMenu();
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

                try
                {
                    newdb.addData(usernameInput, passwdInput, emailInput);
                    // creation of new client dir.
                    Path path = Paths.get(System.getProperty("user.dir") + "/" + usernameInput);
                    Files.createDirectories(path);
                    currentUserName = usernameInput;
                    Menu.mainMenu();
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }

            }
        }
    }
}



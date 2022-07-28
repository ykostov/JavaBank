import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

// Extranet class with its static method is used for login/registration and for storing temp variables.


public class Extranet {

    private static String currentUserName;
    private static BigDecimal moneyATM = BigDecimal.valueOf(0);
    private static String currencyInATM = "";
    private static boolean isAdmin = false;

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static BigDecimal getMoneyATM() {
        return moneyATM;
    }

    public static void setMoneyATM(BigDecimal moneyATM) {
        Extranet.moneyATM = moneyATM;
    }

    public static String getCurrencyInATM() {
        return currencyInATM;
    }

    public static void setCurrencyInATM(String currencyInATM) {
        Extranet.currencyInATM = currencyInATM;
    }

    public static boolean getIsAdmin() {
        return isAdmin;
    }


// login method used for.... login. If the given username and password exists together
// the java.nio.file.File creates a dir having the username as name.
// System.getProperty("user.dir") is used to get the current pwd of the project.
// Then, the user is forwarded to Menu.mainMenu(newdb) and currentUserName is assigned with user's username.

    public static void login(Database newdb) throws IOException {
        String usernameInput;
        String passwdInput;
        Scanner scan = new Scanner(System.in);

        while(true) {

            System.out.print(Message.USERNAME);
            usernameInput = scan.nextLine();
            if (usernameInput.startsWith("stop"))
            {
                break;
            }
            System.out.print(Message.PASSWD);
            passwdInput = scan.nextLine();

            if (newdb.checkUsernameAndPassword(usernameInput, passwdInput)) {
                System.out.println(Message.SUCCESSLOGIN);
                // creation of new client dir.
                try {
                    Path path = Paths.get(System.getProperty("user.dir") + "/" + usernameInput);
                    Files.createDirectories(path);
                    currentUserName = usernameInput;

                } catch (Exception e)
                {
                    System.err.println("Failed to create directory!" + e.getMessage());
                }
                Menu.mainMenu(newdb);
                break;
            }
            else {
                System.out.println(Message.NOSUCCESSLOGIN);
            }
        }
    }

    public static void register(Database newdb) {


        Scanner scan = new Scanner(System.in);
        while(true)
        {
            System.out.print(Message.USERNAME);
            String usernameInput = scan.nextLine();
            System.out.print(Message.PASSWD);
            String passwdInput = scan.nextLine();
            System.out.print(Message.EMAIL);
            String emailInput = scan.nextLine();

            if ((newdb.checkUsername(usernameInput))) {
                System.out.println(Message.TAKEN);
            } else {

                try
                {
                    newdb.addData(usernameInput, passwdInput, emailInput);
                    // creation of new client dir.
                    Path path = Paths.get(System.getProperty("user.dir") + "/" + usernameInput);
                    Files.createDirectories(path);
                    currentUserName = usernameInput;
                    System.out.println(Message.SUCCESSREGISTER);
                    Menu.mainMenu(newdb);
                    break;
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }

            }

        }
    }
}



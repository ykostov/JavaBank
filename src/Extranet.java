import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Extranet class with its static method is used for login/registration and for storing temp variables.


public class Extranet {

    private static String currentUserName;

//    private static BigDecimal moneyATM = BigDecimal.valueOf(0);
//    private static String currencyInATM = "";
    private static boolean isAdmin = false;

    public static String getCurrentUserName() {
        return currentUserName;
    }
    public static void setCurrentUserName(String username) {
        Extranet.currentUserName = username;
    }


//    public static BigDecimal getMoneyATM() {
//        return moneyATM;
//    }

//    public static void setMoneyATM(BigDecimal moneyATM) {
//        Extranet.moneyATM = moneyATM;
//    }

//    public static String getCurrencyInATM() {
//        return currencyInATM;
//    }

//    public static void setCurrencyInATM(String currencyInATM) {
//        Extranet.currencyInATM = currencyInATM;
//    }

    public static boolean getIsAdmin() {
        return isAdmin;
    }

    private static final String passwordPattern =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final String usernamePattern = ".{5,20}";
    private static final Pattern pattern = Pattern.compile(passwordPattern);
    private static final Pattern patternUsername = Pattern.compile(usernamePattern);

    private static boolean isPasswordValid(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private static boolean isUsernameValid(String username) {
        Matcher matcher = patternUsername.matcher(username);
        return matcher.matches();
    }

    private static boolean createDirForUserAndPassToMainMenu(Database newdb, String usernameInput, boolean isAdmin) {
        try
        {
            // creation of new client dir.
            Path path = Paths.get(System.getProperty("user.dir") + "/" + usernameInput);
            Files.createDirectories(path);
            currentUserName = usernameInput;
            if (isAdmin)
            {
                Menu.adminMenu();
            }
            else
            {
                Menu.mainMenu(newdb);
            }

            return true;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }


// login method used for.... login. If the given username and password exists together
// the java.nio.file.File creates a dir having the username as name.
// System.getProperty("user.dir") is used to get the current pwd of the project.
// Then, the user is forwarded to Menu.mainMenu(newdb) and currentUserName is assigned with user's username.

    public static void login(Database newdb, boolean isAdmin) throws IOException {
        String usernameInput;
        String passwdInput;
        Scanner scan = new Scanner(System.in);

        while(true) {

            System.out.print(Message.USERNAME);
            usernameInput = scan.nextLine();
            if (usernameInput.toLowerCase().trim().startsWith("exi"))
            {
                break;
            }
            System.out.print(Message.PASSWD);
            passwdInput = scan.nextLine();

            if (newdb.checkUsernameAndPassword(usernameInput, passwdInput)) {
                System.out.println(Message.SUCCESSLOGIN);

                if (createDirForUserAndPassToMainMenu(newdb, usernameInput, isAdmin)) break;
            }
            else {
                System.out.println(Message.NOSUCCESSLOGIN);
            }
        }
    }

    public static void register(Database newdb, boolean isAdmin) {


        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.print(Message.USERNAME);
            String usernameInput = scan.nextLine();
            System.out.print(Message.PASSWD);
            String passwdInput = scan.nextLine();
            System.out.print(Message.EMAIL);
            String emailInput = scan.nextLine();

            if (isPasswordValid(passwdInput)) {
                if (isUsernameValid(usernameInput)) {


                    if ((newdb.checkUsername(usernameInput))) {
                        System.out.println(Message.TAKEN);
                    } else {
                        newdb.addData(usernameInput, passwdInput, emailInput);
                        System.out.println(Message.SUCCESSREGISTER);
                        if (createDirForUserAndPassToMainMenu(newdb, usernameInput, isAdmin)) break;

                    }

                }
                else
                    System.out.println("username must be 5-20 symbols");
            }
            else
            {
                System.out.println("password must contain at least one lowercase character, one uppercase character, one digit, one special character, and a length between 8 to 20");
            }
        }
    }



}



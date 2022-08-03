import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Extranet class with its static method is used for login/registration and for storing temp variables.


public class Extranet {

    private static String currentUserName;
    private static String currentLanguage = "en";

    private static int currentPercent = 2;

    public static String getCurrentUserName() {
        return currentUserName;
    }
    public static void setCurrentUserName(String username) {
        Extranet.currentUserName = username;
    }

    public static String getCurrentLanguage() {
        return currentLanguage;
    }

    public static void setCurrentLanguage(String currentLanguage) {
        Extranet.currentLanguage = currentLanguage;
        debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "Language changed to " + currentLanguage);
    }

    public static int getCurrentPercent() {
        return currentPercent;
    }

    public static void setCurrentPercent(int currentPercent) {
        Extranet.currentPercent = currentPercent;
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
            Path path = Paths.get(System.getProperty("user.dir") + File.separator + usernameInput);
            Files.createDirectories(path);
            currentUserName = usernameInput;
            if (isAdmin)
            {
                debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "Admin logged - " + usernameInput);
                Menu.adminMenu();
            }
            else
            {
                debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "User logged - " + usernameInput);
                Menu.mainMenu(newdb);
            }

            return true;
        }
        catch (Exception e)
        {
            debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "ERROR: Very bad at createDirForUserAndPassToMainMenu: " + e.getMessage());
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

            System.out.print(Messages.getMessage(Extranet.getCurrentLanguage() + "-username"));
            usernameInput = scan.nextLine();
            if (usernameInput.toLowerCase().trim().startsWith("exi"))
            {
                break;
            }
            System.out.print(Messages.getMessage(Extranet.getCurrentLanguage() + "-password"));
            passwdInput = scan.nextLine();

            if (newdb.checkUsernameAndPassword(usernameInput, passwdInput)) {
                System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-successLogin"));
                Extranet.setCurrentUserName(usernameInput);

                if (createDirForUserAndPassToMainMenu(newdb, usernameInput, isAdmin)) break;
            }
            else {
                System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-noSuccessLogin"));
                debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "ERROR: Username with password are not found in DB");
            }
        }
    }

    public static void register(Database newdb, boolean isAdmin) {


        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.print(Messages.getMessage(Extranet.getCurrentLanguage() + "-username"));
            String usernameInput = scan.nextLine();
            System.out.print(Messages.getMessage(Extranet.getCurrentLanguage() + "-password"));
            String passwdInput = scan.nextLine();
            System.out.print(Messages.getMessage(Extranet.getCurrentLanguage() + "-email"));
            String emailInput = scan.nextLine();

            if (isPasswordValid(passwdInput)) {
                if (isUsernameValid(usernameInput)) {


                    if ((newdb.checkUsername(usernameInput))) {
                        System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-taken"));
                    } else {
                        newdb.addData(usernameInput, passwdInput, emailInput);
                        System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-successRegister"));
                        Extranet.setCurrentUserName(usernameInput);
                        if (createDirForUserAndPassToMainMenu(newdb, usernameInput, isAdmin)) break;

                    }

                }
                else
                    System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-usernameReq"));
            }
            else
            {
                System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-passReq"));
            }
        }
    }



}



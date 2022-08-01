import java.util.HashMap;

public class Messages {

    public static void createMap()
    {
        HashMap<String, String> languages = new HashMap<>();
        HashMap<String, HashMap<String, String>> languages2 = new HashMap<>();
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_RESET = "\u001B[0m";

        languages.put("en-welcome", "Welcome to JavaBank! You can login or register or exit");
        languages.put("en-badinput", "Got bad input. Please try again or enter 'exit'");
        languages.put("en-username", "Enter username: ");
        languages.put("en-password", "Enter password: ");
        languages.put("en-email", "Enter email: ");
        languages.put("en-successlogin", "You have logged in successfully!");
        languages.put("en-nosuccesslogin", "Incorrect password, try again or enter 'exit' to exit");
        languages.put("en-taken", "Username is already taken, try again");
        languages.put("en-successregister", "You have registered successfully!");
        languages.put("en-mainmenu", "Welcome to our Main Menu, \" + Extranet.getCurrentUserName() + \"!.\" + ANSI_RESET + \"\\r\\n\" +  \"Options you have: \" + ANSI_GREEN + \"account\" + ANSI_RESET + \" ; \" + ANSI_GREEN + \"Add Money\" + ANSI_RESET + \" in ATM ; \" + ANSI_GREEN + \"Exchange\" + ANSI_RESET + \" money in ATM, or \" + ANSI_GREEN + \"exit\" + ANSI_RESET");
        languages.put("en-howmuch", "how much money would you like to insert into the ATM?");
        languages.put("en-currentmoneyinatm", ANSI_BLUE + "You currently have " + ATM.getMoneyATM() + " " + ATM.getCurrencyInATM() + " money in ATM. " + ANSI_RESET);
        languages.put("en-maxacc", "you have maximum accounts allowed for a basic user (3). Become VIP to get more");
        languages.put("en-accmenu", "- that is your number of accounts. Would you like to 'list' them, 'create' a new one, 'import' money, 'withdraw' money or 'exit'?");
        languages.put("en-seccessacc", "Account created successfully!");
        languages.put("en-usernamereq", "Username must be 5-20 symbols");
        languages.put("en-passreq", "password must contain at least one lowercase character, one uppercase character, one digit, one special character, and a length between 8 to 20");
        languages.put("en-entercurr", "Enter currency - BGN or  RSD");
        languages.put("en-noaccount", "You have selected an account you do not have!");
        languages.put("en-wheretodooperation", "In which account do you want to do this operation? (1 for acc1, 2 for acc2 or 3 for acc3");
        languages.put("en-thechosenacchave", "The chosen account have: ");
        languages.put("en-entermoney", "Please, enter how much money do you want");
        languages.put("en-moremoney", "You have selected more money that you have in ATM/Account");
        languages.put("en-successoperation", "Operation was successful");
        languages.put("en-wrong", "Something went wrong");
        languages.put("en-bigerror", "You either don't have money in your ATM, or the currency of it and of the account does not matches. You can choose different account, import money in ATM with the right currency or change currency of account");
        languages.put("en-blockedacc", "your account is BLOCKED! Please, contact with admin to fix this. :(");
        languages.put("en-whowmuchtowithdraw", "how much do you want to withdraw?");
        languages.put("en-separator", "---------------------------------------------------");
//


        System.out.println( languages.get("en-currentmoneyinatm"));

        // en-welcome -> welcome
        // bg-welcome -> zdrasteee
    }
}

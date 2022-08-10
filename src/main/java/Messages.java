import java.util.HashMap;

public class Messages {

    private static final HashMap<String, String> languages = new HashMap<>();

    protected static void setupMap()
    {

        String ANSI_BLUE = "\u001B[34m";
        String ANSI_RESET = "\u001B[0m";
        String ANSI_GREEN = "\u001B[32m";

        languages.put("en-welcome", "Welcome to JavaBank! You can login or register or exit. You can also 'db' to change the db to file one. Натиснете lang за смяна на език");
        languages.put("en-badInput", "Got bad input. Please try again or enter 'exit'");
        languages.put("en-username", "Enter username: ");
        languages.put("en-password", "Enter password: ");
        languages.put("en-email", "Enter email: ");
        languages.put("en-successLogin", "You have logged in successfully!");
        languages.put("en-noSuccessLogin", "Incorrect password, try again or enter 'exit' to exit");
        languages.put("en-taken", "Username is already taken, try again");
        languages.put("en-successRegister", "You have registered successfully!");
        languages.put("en-mainMenu", "Welcome to our Main Menu, " + Extranet.getCurrentUserName() + "!." + ANSI_RESET + "\r\n" +  "Options you have: " + ANSI_GREEN + "account" + ANSI_RESET + " ; " + ANSI_GREEN + "Add Money" + ANSI_RESET + " in ATM ; " + ANSI_GREEN + "Exchange" + ANSI_RESET + " money in ATM, or " + ANSI_GREEN + "exit" + ANSI_RESET);
        languages.put("en-howMuchToInsert", "how much money would you like to insert into the ATM?");
        languages.put("en-currentMoneyInAtm", ANSI_BLUE + "You currently have " + ATM.getMoneyATM() + " " + ATM.getCurrencyInATM() + " money in ATM. " + ANSI_RESET);
        languages.put("en-maxAcc", "you have maximum accounts allowed for a basic user (3). Become VIP to get more");
        languages.put("en-accMenu", "- that is your number of accounts. Would you like to 'list' them, 'create' a new one, 'import' money, 'withdraw' money or 'exit'?");
        languages.put("en-successAcc", "Account created successfully!");
        languages.put("en-usernameReq", "Username must be 5-20 symbols");
        languages.put("en-passReq", "password must contain at least one lowercase character, one uppercase character, one digit, one special character, and a length between 8 to 20");
        languages.put("en-enterCurrency", "Enter currency - BGN or  RSD");
        languages.put("en-noAccount", "You have selected an account you do not have!");
        languages.put("en-whereToDoOperation", "In which account do you want to do this operation? (1 for acc1, 2 for acc2 or 3 for acc3");
        languages.put("en-theChosenAccHave", "The chosen account have: ");
        languages.put("en-enterMoney", "Please, enter how much money do you want");
        languages.put("en-moreMoney", "You have selected more money that you have in ATM/Account");
        languages.put("en-successOperation", "Operation was successful");
        languages.put("en-wrong", "Something went wrong");
        languages.put("en-bigError", "You either don't have money in your ATM, or the currency of it and of the account does not matches. You can choose different account, import money in ATM with the right currency or change currency of account");
        languages.put("en-blockedAcc", "your account is BLOCKED! Please, contact with admin to fix this. :(");
        languages.put("en-howMuchToWithdraw", "how much do you want to withdraw?");
        languages.put("en-onlyNumbers", "Please, enter only numbers");
        languages.put("en-tryAgain", "Please, try again");
        languages.put("en-currencyInBgn", "You currency in ATM is set to BGN. You have " + ATM.getMoneyATM() + " BGN money. Would you like to exchange them to RSD? (1bgn = 60.11 RSD), (yes | no)");
        languages.put("en-currencyInRsd", "You currency in ATM is set to RSD. You have " + ATM.getMoneyATM() + " RSD money. Would you like to exchange them to BGN? (1rsd = 0.017 bgn), (yes | no)");
        languages.put("en-noMoneyInAtm", "You do not have money in ATM!");
        languages.put("separator", "---------------------------------------------------");

        languages.put("bg-welcome", "Добре дошли в Java Bank. Напишете login или register или lang for language setup");
        languages.put("bg-badInput", "Got bad input. Please try again or enter 'exit'");
        languages.put("bg-username", "Въведи потребителско име: ");
        languages.put("bg-password", "Въведи парола: ");
        languages.put("bg-email", "Въведи мейлче: ");
        languages.put("bg-successLogin", "Логнахте се успешно!");
        languages.put("bg-noSuccessLogin", "Грешна парола, опитайте отново или натиснете exit");
        languages.put("bg-taken", "Потребителското име вече се използва, опитайте отново");
        languages.put("bg-successRegister", "Направихте регистрация успешно!");
        languages.put("bg-mainMenu", "Добре дошли в главното меню, " + Extranet.getCurrentUserName() + "!" + ANSI_RESET + "\r\n\" +  \"Опции: " + ANSI_GREEN + "account" + ANSI_RESET + " ; " + ANSI_GREEN + "Add Money" + ANSI_RESET + " в ATM ; " + ANSI_GREEN + "Exchange" + ANSI_RESET + " money in ATM, or " + ANSI_GREEN + "exit" + ANSI_RESET);
        languages.put("bg-howMuchToInsert", "how much money would you like to insert into the ATM?");
        languages.put("bg-currentMoneyInAtm", ANSI_BLUE + "You currently have " + ATM.getMoneyATM() + " " + ATM.getCurrencyInATM() + " money in ATM. " + ANSI_RESET);
        languages.put("bg-maxAcc", "Имате максимум акаунти за нормален потребител (3)!");
        languages.put("bg-accMenu", "- Това е вашият брой акаунти. Искате ли да ги 'list' , 'create' нов, 'import' пари, 'withdraw' пари or 'exit'?");
        languages.put("bg-successAcc", "Успешно създадохте акаунт");
        languages.put("bg-usernameReq", "Потребителското име трябва да бъде от 5 до 20 символа!");
        languages.put("bg-passReq", "Паролата трябва да съдържа поне един малък символ, един голям, една цифра, един специален символ и дължина от 8 до 20");
        languages.put("bg-enterCurrency", "Въведи валута - BGN или RSD");
        languages.put("bg-noAccount", "You have selected an account you do not have!");
        languages.put("bg-whereToDoOperation", "In which account do you want to do this operation? (1 for acc1, 2 for acc2 or 3 for acc3");
        languages.put("bg-theChosenAccHave", "The chosen account have: ");
        languages.put("bg-enterMoney", "Please, enter how much money do you want");
        languages.put("bg-moreMoney", "You have selected more money that you have in ATM/Account");
        languages.put("bg-successOperation", "Operation was successful");
        languages.put("bg-wrong", "Something went wrong");
        languages.put("bg-bigError", "You either don't have money in your ATM, or the currency of it and of the account does not matches. You can choose different account, import money in ATM with the right currency or change currency of account");
        languages.put("bg-blockedAcc", "your account is BLOCKED! Please, contact with admin to fix this. :(");
        languages.put("bg-howMuchToWithdraw", "how much do you want to withdraw?");
        languages.put("bg-onlyNumbers", "Please, enter only numbers");
        languages.put("bg-tryAgain", "Please, try again");
        languages.put("bg-currencyInBgn", "You currency in ATM is set to BGN. You have " + ATM.getMoneyATM() + " BGN money. Would you like to exchange them to RSD? (1bgn = 60.11 RSD), (yes | no)");
        languages.put("bg-currencyInRsd", "You currency in ATM is set to RSD. You have " + ATM.getMoneyATM() + " RSD money. Would you like to exchange them to BGN? (1rsd = 0.017 bgn), (yes | no)");
        languages.put("bg-noMoneyInAtm", "You do not have money in ATM!");

    }

    protected static String getMessage(String key)
    {
        return languages.get(key);
    }
}

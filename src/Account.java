import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

// This class is used for managing accounts of user

public class Account {

    private int fileCount;


    // this method checks the amount files in user's dir, viz. accounts
    private void countAccountsCreatedForUser()
    {
        try
        {
            File directory=new File(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName());
            fileCount= directory.list().length;
        } catch (NullPointerException e)
        {
            fileCount = 0;
        }

    }

    // this is the main Account method with its menu

    public void setupAccount(Database newdb) throws IOException {
      countAccountsCreatedForUser();
      String userInput;
      if (fileCount > 3)
      {
          System.out.println(Message.MAXACC);
      }
      else {
          while(true) {

              System.out.println(fileCount + " " + Message.ACCMENU);


              Scanner scan = new Scanner(System.in);
              userInput = scan.nextLine();

              if (userInput.toLowerCase().trim().startsWith("list")) { listAccounts(); }
              else if (userInput.toLowerCase().trim().startsWith("create")) { createAccount(); }
              else if (userInput.toLowerCase().trim().startsWith("import"))
              {
                  if (ATM.getMoneyATM() != null)
                  {
                      importMoneyInAccount();
                  } else
                  {
                      System.out.println("you do not have money in ATM");
                  }

              }
              else if (userInput.toLowerCase().trim().startsWith("with")) { WithdrawMoneyFromAccount(); }
              else if (userInput.toLowerCase().trim().startsWith("transfer")) { transferMoneyToAnotherUser(newdb); }
              else if (userInput.toLowerCase().trim().startsWith("exch")) { changeCurrencyOfAccount(); }
              else if (userInput.toLowerCase().trim().startsWith("exit")) { break; }
            }
        }

    }

    // this method lists all accounts in user's dir and reads them

    private void listAccounts() throws IOException {
        for (int i = 1; i<=fileCount; i++)
        {
            System.out.print("Account"+i + "=");
            List<String> accounts = Files.readAllLines(Path.of(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc"+ i + ".txt"));
            System.out.println(accounts);
        }
    }

    // this method creates an Account /file/ in user's directory
    private void createAccount() throws IOException {
        if (fileCount >= 3)
        {
            System.out.println(Message.MAXACC);
        }
        else
        {

            try
            {
                String chosenCurrency = "";
                while(true) {


                    System.out.println("Enter currency - BGN or  RSD");
                    Scanner scan = new Scanner(System.in);
                    chosenCurrency = scan.nextLine().toUpperCase().trim();
                    if (chosenCurrency.equals("BGN") || chosenCurrency.equals("RSD")) {
                        break;
                    } else {
                        System.out.println("You have entered something I do not want. Try again..");
                    }
                }

            FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc"+ ++fileCount + ".txt");
            Writer output = new FileWriter(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc"+ fileCount + ".txt", true);
            output.write("0" + "\r\n" + chosenCurrency);
            output.close();
            System.out.println(Message.SUCCESSACC);
            }catch (Exception ex)
            {
                // write data to logger
            }


        }
    }

    private void importMoneyInAccount() throws IOException {

        if (fileCount == 0) {
            System.out.println("You do not have an account.");
        }
        else {
            System.out.println("In which account do you want to import money? (1 for acc1, 2 for acc2 or 3 for acc3");
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            if (Integer.parseInt(userInput) > fileCount) {
                System.out.println("you have selected an account that you do not have");
            } else {



            List<String> lines = Files.readAllLines(Path.of(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc" + userInput + ".txt"));
            BigDecimal moneyInAccount = BigDecimal.valueOf(Double.parseDouble(lines.get(0)));
            String currencyInAccount = lines.get(1);
            System.out.println("You have " + ATM.getMoneyATM() + " " + ATM.getCurrencyInATM() + " money in ATM. The chosen account have " + moneyInAccount + " " + currencyInAccount + ". Please, enter how much money do you want");
            if (ATM.getCurrencyInATM().equals(currencyInAccount)) {

                String moneyToImport = scan.nextLine();

                if (Double.parseDouble(moneyToImport) > ATM.getMoneyATM().doubleValue()) {
                    System.out.println("You want to import more money that you have in ATM");
                } else {
                    try {
                        BigDecimal newMoney = moneyInAccount.add(BigDecimal.valueOf(Double.parseDouble(moneyToImport)));
                        Writer output = new FileWriter(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc" + userInput + ".txt", false);
                        output.write(String.valueOf(newMoney) + "\r\n" + currencyInAccount);
                        output.close();
                        ATM.setMoneyATM(ATM.getMoneyATM().subtract(BigDecimal.valueOf(Double.parseDouble(moneyToImport))));
                        System.out.println("Money successfully imported!");
                    } catch (Exception e) {
                        System.out.println("Something went wrong - " + e.toString());
                    }

                }
            } else {
                System.out.println("You either don't have money in your ATM, or the currency of it and of the account does not matches. You can choose different account, import money in ATM with the right currency or change currency of account");
            }

        }

        }

    }

    private void WithdrawMoneyFromAccount() throws IOException {
        if (fileCount == 0) {
            System.out.println("You do not have an account.");
        }
        else {
            System.out.println("From which account do you want to withdraw money? (1 for acc1, 2 for acc2 or 3 for acc3");
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            if (Integer.parseInt(userInput) > fileCount) {
                System.out.println("you have selected an account that you do not have");
            }
            else {

            List<String> lines = Files.readAllLines(Path.of(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc" + userInput + ".txt"));
            BigDecimal moneyInAccount = BigDecimal.valueOf(Double.parseDouble(lines.get(0)));
            String currencyInAccount = lines.get(1);
            while (true) {
                System.out.println("In this account you have " + moneyInAccount + " " + currencyInAccount);

                if (ATM.getCurrencyInATM().equals(currencyInAccount) || ATM.getCurrencyInATM().equals("")) {

                    System.out.println("how much do you want to withdraw?");
                    String moneyToWithdraw = scan.nextLine();
                    if (Double.parseDouble(moneyToWithdraw) > Double.parseDouble(lines.get(0))) {
                        System.out.println("you have entered an amount that is higher than your account. Please, try again");
                        continue;
                    }
                    try {
                        BigDecimal newMoneyInAccount = moneyInAccount.subtract(BigDecimal.valueOf(Double.parseDouble(moneyToWithdraw)));
                        Writer output = new FileWriter(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc" + userInput + ".txt", false);
                        output.write(String.valueOf(newMoneyInAccount) + "\r\n" + currencyInAccount.toUpperCase());
                        output.close();
                        if (ATM.getMoneyATM() == null) {
                            ATM.setMoneyATM(BigDecimal.valueOf(Double.parseDouble(moneyToWithdraw)));
                        } else {
                            ATM.setMoneyATM(ATM.getMoneyATM().add(BigDecimal.valueOf(Double.parseDouble(moneyToWithdraw))));
                        }
                        ATM.setCurrencyInATM(currencyInAccount.toUpperCase());
                        System.out.println("Money successfully withdrew");
                        break;
                    } catch (Exception e) {
                        System.out.println("Something went wrong - " + e.toString());
                    }
                } else {
                    System.out.println("You have different currency in your account. You can go back at main menu and take out that money from ATM");
                    break;
                }
            }
        }

        }
    }

    private void transferMoneyToAnotherUser(Database newdb) throws IOException {
        System.out.println("money in ATM: " + ATM.getMoneyATM() + " " + ATM.getCurrencyInATM() + ". Enter how much would you want to transfer");
        Scanner scan = new Scanner(System.in);
        String moneyToTransfer = scan.nextLine();

        if (Double.parseDouble(moneyToTransfer) > ATM.getMoneyATM().doubleValue())
        {
            System.out.println("You want to transfer more money that you have in ATM");
        }
        else {

            System.out.println("write username to transfer money from ATM");

            String usernameToTransferMoney = scan.nextLine();
            if (newdb.checkUsername(usernameToTransferMoney)) {
                List<String> lines = Files.readAllLines(Path.of(System.getProperty("user.dir") + "/" + usernameToTransferMoney + "/" + "acc1.txt"));
                BigDecimal moneyInAccount = BigDecimal.valueOf(Double.parseDouble(lines.get(0)));
                String currencyInAccount = lines.get(1);
                if (currencyInAccount.equals(ATM.getCurrencyInATM())) {
                    BigDecimal newMoney = moneyInAccount.add(BigDecimal.valueOf(Double.parseDouble(moneyToTransfer)));
                    Writer output = new FileWriter(System.getProperty("user.dir") + "/" + usernameToTransferMoney + "/" + "acc1.txt", false);
                    output.write(String.valueOf(newMoney) + "\r\n" + currencyInAccount.toUpperCase());
                    output.close();
                    System.out.println("Money transferred successfully!");
                    ATM.setMoneyATM(ATM.getMoneyATM().subtract(newMoney));
                } else {
                    System.out.println("Your currency does not matches with the currency in the account of the other user. Please, change your currency in your ATM before proceeding. ");
                }

            }
            else
            {
                System.out.println("Username not found!");
            }

        }

    }

    public void changeCurrencyOfAccount() throws IOException {

        if (fileCount == 0) {
            System.out.println("You do not have an account.");
        }
        else {
            System.out.println("In which account do you want to change money currency? (1 for acc1, 2 for acc2 or 3 for acc3");
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            if (Integer.parseInt(userInput) > fileCount) {
                System.out.println("you have selected an account that you do not have");
            }

            List<String> lines = Files.readAllLines(Path.of(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc" + userInput + ".txt"));
            BigDecimal moneyInAccount = BigDecimal.valueOf(Double.parseDouble(lines.get(0)));
            String currencyInAccount = lines.get(1);

            if (currencyInAccount.equals("BGN"))
            {
                System.out.println("Your money in account are in BGN. Would you like to exchange them to RSD? (1bgn = 60.11 RSD), (yes | no)");
                if (scan.nextLine().startsWith("y"))
                {
                    try {
                        BigDecimal newMoney = moneyInAccount.multiply(BigDecimal.valueOf(60.11));
                        String newCurrency = "RSD";
                        Writer output = new FileWriter(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc" + userInput + ".txt", false);
                        output.write(String.valueOf(newMoney) + "\r\n" + newCurrency);
                        output.close();
                        System.out.println("Money successfully exchanged!");
                    } catch (Exception e) {
                        System.out.println("Something went wrong - " + e.toString());
                    }
                }
            }
            else if (currencyInAccount.equalsIgnoreCase("RSD"))
            {
                System.out.println("Your money in account are in RSD. Would you like to exchange them to BGN? (1rsd = 0.017 bgn), (yes | no)");
                if (scan.nextLine().startsWith("y"))
                {
                    try {
                        BigDecimal newMoney = moneyInAccount.multiply(BigDecimal.valueOf(0.017));
                        String newCurrency = "BGN";
                        Writer output = new FileWriter(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc" + userInput + ".txt", false);
                        output.write(String.valueOf(newMoney) + "\r\n" + newCurrency);
                        output.close();
                        System.out.println("Money successfully exchanged!");
                    } catch (Exception e) {
                        System.out.println("Something went wrong - " + e.toString());
                    }
                }
            }
        }
    }
}

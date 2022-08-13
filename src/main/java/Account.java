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
    public boolean DoesUserHaveAnAccount = true; //glupost

    public int getFileCount() {
        return fileCount;
    }
    Scanner scan = new Scanner(System.in);

    // this method checks the amount files in user's dir, viz. accounts
    protected void countAccountsCreatedForUser()
    {
        try
        {
            File directory=new File(System.getProperty("user.dir") + File.separator + Extranet.getCurrentUserName());
            fileCount= directory.list().length;
        } catch (NullPointerException e)
        {
            fileCount = 0;
        }

    }

    // this is the main Account method with its menu

    public void openAccount(Database newdb) throws IOException {
      countAccountsCreatedForUser();
      String userInput;
      boolean tempVar = true;
          while(tempVar) {
              System.out.println(Messages.getMessage("separator"));
              System.out.println(getFileCount() + Messages.getMessage(Extranet.getCurrentLanguage() + "-accMenu"));
              userInput = scan.nextLine();

              switch (userInput.toLowerCase().trim()) {
                  case "list" -> listAccounts();
                  case "create" -> createAccount();
                  case "import" -> importMoneyInAccount();
                  case "exchange" -> changeCurrencyOfAccount();
                  case "withdraw" -> withdrawMoneyFromAccount();
                  case "transfer" -> transferMoneyToAnotherUser(newdb);
                  case "exit" -> tempVar = false;
              }
            }
    }

    // this method lists all accounts in user's dir and reads them

    protected void listAccounts() throws IOException {
        for (int i = 1; i<=fileCount; i++)
        {
            DoesUserHaveAnAccount = true; //glupost
            System.out.print("Account"+i + "=");
            List<String> accounts = Files.readAllLines(Path.of(System.getProperty("user.dir") + File.separator + Extranet.getCurrentUserName() + File.separator + "acc"+ i + ".txt"));
            System.out.println(accounts);
        }
        debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "Accounts listed by user - " + Extranet.getCurrentUserName());
        if (!DoesUserHaveAnAccount) {
            System.err.println("no accounts found");
            debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "ERROR: Accounts not found, possible number = 0, by user - " + Extranet.getCurrentUserName());
        }
    }

    // this method creates an Account /file/ in user's directory
    private void createAccount() throws IOException {
        if (fileCount >= 3) { System.err.println(Message.MAXACC);} else {
        String chosenCurrency;
        while(true) {
            System.out.println(Message.ENTERCURR);
            chosenCurrency = scan.nextLine().toUpperCase().trim();
            if (chosenCurrency.equals("BGN") || chosenCurrency.equals("RSD")) {
                break;}
            else {
                System.err.println(Message.BADINPUT);
            }
        }
            FileWriter fw = new FileWriter(System.getProperty("user.dir") + File.separator + Extranet.getCurrentUserName() + "/" + "acc"+ ++fileCount + ".txt");
            fw.write("0" + "\r\n" + chosenCurrency + "\r\n" + "free");
            fw.close();
            System.out.println(Message.SUCCESSACC);
            debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "Account created by user - " + Extranet.getCurrentUserName());
        }
    }

    private void importMoneyInAccount() throws IOException {
        if (fileCount == 0) { System.err.println(Message.NOACCOUNT); } else {
            System.out.println(Message.WHERETODOOPERATION);
            String accountNumber = scan.nextLine();
            if (Integer.parseInt(accountNumber) > fileCount) { System.err.println(Message.NOACCOUNT);}
            else {
                BigDecimal moneyInAccount = getAccountMoney(Extranet.getCurrentUserName(), accountNumber);
                String currencyInAccount = getAccountCurrency(Extranet.getCurrentUserName(), accountNumber);
                String blockedStatus = getAccountBlockedStatus(Extranet.getCurrentUserName(), accountNumber);
                if (blockedStatus.startsWith("free")) {
                    System.out.println(Message.CURRENTMONEYINATM + "" + Message.THECHOSENACCHAVE + "" + moneyInAccount + currencyInAccount + Message.ENTERMONEY);

                if (ATM.getCurrencyInATM().equals(currencyInAccount)) {
                    String moneyToImport = scan.nextLine();
                    if (Double.parseDouble(moneyToImport) > ATM.getMoneyATM().doubleValue()) {
                        System.err.println(Message.MOREMONEY);
                    } else {
                            BigDecimal percent1 = BigDecimal.valueOf(100).subtract(BigDecimal.valueOf(Extranet.getCurrentPercent()));
                            BigDecimal percent2 = percent1.divide(BigDecimal.valueOf(100));
                            BigDecimal finalmoney = BigDecimal.valueOf(Double.parseDouble(moneyToImport)).multiply(percent2);
                            BigDecimal newMoney = moneyInAccount.add(finalmoney);
                            writeDataInAccount("money", String.valueOf(newMoney), Extranet.getCurrentUserName(), accountNumber);
                            ATM.setMoneyATM(ATM.getMoneyATM().subtract(BigDecimal.valueOf(Double.parseDouble(moneyToImport))));
                            System.out.println(Message.SUCCESSOPERATION);
                        debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "Imported money (" + finalmoney + currencyInAccount + ") in Account by user - " + Extranet.getCurrentUserName());
                  }
                } else { System.err.println(Message.BIGERROR); }
            } else { System.err.println(Message.BLOCKEDACC); }

        }
      }
    }

    private void withdrawMoneyFromAccount() throws IOException {
        if (fileCount == 0) { System.err.println(Message.NOACCOUNT); }  else {
            System.out.println(Message.WHERETODOOPERATION);
            String accountNumber = scan.nextLine();
            if (Integer.parseInt(accountNumber) > fileCount) { System.out.println(Message.NOACCOUNT); }
            else {
                    BigDecimal moneyInAccount = getAccountMoney(Extranet.getCurrentUserName(), accountNumber);
                    String currencyInAccount = getAccountCurrency(Extranet.getCurrentUserName(), accountNumber);
                    String blockedStatus = getAccountBlockedStatus(Extranet.getCurrentUserName(), accountNumber);
                if (blockedStatus.startsWith("free")) {
                  while (true) {
                    System.out.println(Message.THECHOSENACCHAVE + " " + moneyInAccount + " " + currencyInAccount);
                    if (ATM.getCurrencyInATM().equals(currencyInAccount) || ATM.getCurrencyInATM().equals("")) {
                        System.out.println(Message.HOWMUCHTOWITHDRAW);
                        String moneyToWithdraw = scan.nextLine();
                        if (Double.parseDouble(moneyToWithdraw) > moneyInAccount.doubleValue()) {
                            System.err.println(Message.MOREMONEY);
                            continue;
                        }
                        try {
                            BigDecimal newMoneyInAccount = moneyInAccount.subtract(BigDecimal.valueOf(Double.parseDouble(moneyToWithdraw)));

                            writeDataInAccount("money", String.valueOf(newMoneyInAccount), Extranet.getCurrentUserName(), accountNumber);

                            if (ATM.getMoneyATM() == null) {
                                ATM.setMoneyATM(BigDecimal.valueOf(Double.parseDouble(moneyToWithdraw)));
                            } else {
                                ATM.setMoneyATM(ATM.getMoneyATM().add(BigDecimal.valueOf(Double.parseDouble(moneyToWithdraw))));
                            }
                            ATM.setCurrencyInATM(currencyInAccount.toUpperCase());
                            System.out.println(Message.SUCCESSOPERATION);
                            debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "Withdrew money (" + moneyToWithdraw + ") in Account by user - " + Extranet.getCurrentUserName());
                            break;
                        } catch (Exception e) {
                            System.err.println(Message.WRONG + e.toString());
                        }
                    } else {
                        System.err.println(Message.BIGERROR);
                        break;
                    }
                }

            }
            else
            {
                System.err.println(Message.BLOCKEDACC);
            }
      }
    }
    }


    private void transferMoneyToAnotherUser(Database newdb) throws IOException {
        System.out.println("money in ATM: " + ATM.getMoneyATM() + " " + ATM.getCurrencyInATM() + ". Enter how much would you want to transfer");
        String moneyToTransfer = scan.nextLine();

        if (Double.parseDouble(moneyToTransfer) > ATM.getMoneyATM().doubleValue())
        {
            System.out.println("You want to transfer more money that you have in ATM");
        }
        else {

            System.out.println("write username to transfer money from ATM");

            String usernameToTransferMoney = scan.nextLine();
            if (newdb.checkUsername(usernameToTransferMoney, false)) {
                List<String> lines = Files.readAllLines(Path.of(System.getProperty("user.dir") + "/" + usernameToTransferMoney + "/" + "acc1.txt"));
                BigDecimal moneyInAccount = BigDecimal.valueOf(Double.parseDouble(lines.get(0)));
                String currencyInAccount = lines.get(1);
                String blockedStatus = lines.get(2);
                if (currencyInAccount.equals(ATM.getCurrencyInATM())) {
                    if (blockedStatus.startsWith("free")) {


                        BigDecimal newMoney = moneyInAccount.add(BigDecimal.valueOf(Double.parseDouble(moneyToTransfer)));
                        Writer output = new FileWriter(System.getProperty("user.dir") + "/" + usernameToTransferMoney + "/" + "acc1.txt", false);
                        output.write(newMoney + "\r\n" + currencyInAccount.toUpperCase());
                        output.close();
                        System.out.println("Money transferred successfully!");
                        debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "Money transferred to user " + usernameToTransferMoney + " in Account by user - " + Extranet.getCurrentUserName());
                        ATM.setMoneyATM(ATM.getMoneyATM().subtract(newMoney));
                    }
                    else
                    {
                        System.err.println(Message.BLOCKEDACC);
                    }
                } else {
                    System.err.println("Your currency does not matches with the currency in the account of the other user. Please, change your currency in your ATM before proceeding. ");
                }

            }
            else
            {
                System.err.println("Username not found!");
            }

        }

    }

    public void changeCurrencyOfAccount() throws IOException {

        if (fileCount == 0) {
            System.out.println("You do not have an account.");
        }
        else {
            System.out.println("In which account do you want to change money currency? (1 for acc1, 2 for acc2 or 3 for acc3");
            String accountNumber = scan.nextLine();
            if (Integer.parseInt(accountNumber) > fileCount) {
                System.out.println("you have selected an account that you do not have");
                return;
            }

            BigDecimal moneyInAccount = getAccountMoney(Extranet.getCurrentUserName(), accountNumber);
            String currencyInAccount = getAccountCurrency(Extranet.getCurrentUserName(), accountNumber);
            String blockedStatus = getAccountBlockedStatus(Extranet.getCurrentUserName(), accountNumber);

            if (blockedStatus.startsWith("free")) {


                if (currencyInAccount.equals("BGN")) {
                    System.out.println("Your money in account are in BGN. Would you like to exchange them to RSD? (1 BGN =" + ATM.fromBgnToRsd() + "RSD), (yes | no)");
                    if (scan.nextLine().startsWith("y")) {
                        try {
                            BigDecimal newMoney = moneyInAccount.multiply(BigDecimal.valueOf(ATM.fromBgnToRsd()));
                            String newCurrency = "RSD";
                            Writer output = new FileWriter(System.getProperty("user.dir") + File.separator + Extranet.getCurrentUserName() + File.separator + "acc" + accountNumber + ".txt", false);
                            output.write(newMoney + "\r\n" + newCurrency);
                            output.close();
                            System.out.println("Money successfully exchanged!");
                        } catch (Exception e) {
                            System.out.println("Something went wrong - " + e);
                        }
                    }
                } else if (currencyInAccount.equalsIgnoreCase("RSD")) {
                    System.out.println("Your money in account are in RSD. Would you like to exchange them to BGN? (1 RSD = " + ATM.fromRsdToBgn() + "BGN), (yes | no)");
                    if (scan.nextLine().startsWith("y")) {
                        try {
                            BigDecimal newMoney = moneyInAccount.multiply(BigDecimal.valueOf(ATM.fromRsdToBgn()));
                            String newCurrency = "BGN";
                            Writer output = new FileWriter(System.getProperty("user.dir") + File.separator + Extranet.getCurrentUserName() + File.separator + "acc" + accountNumber + ".txt", false);
                            output.write(newMoney + "\r\n" + newCurrency);
                            output.close();

                            System.out.println("Money successfully exchanged!");
                        } catch (Exception e) {
                            System.out.println("Something went wrong - " + e);
                        }
                    }
                }
            }
            else
            {
                System.out.println("the account is blocked");
            }
        }
    }


    public void writeDataInAccount(String typeOfChange, String changer, String username, String accountNumber) throws IOException {

        BigDecimal moneyInAccount = getAccountMoney(username, accountNumber);
        String currencyInAccount = getAccountCurrency(username, accountNumber);
        //String blockedStatus = getAccountBlockedStatus(username, accountNumber);


        Writer output = new FileWriter(System.getProperty("user.dir") + File.separator + username + File.separator + "acc" + accountNumber + ".txt", false);


        switch (typeOfChange) {
            case "money" -> {
                output.write(changer + "\r\n" + currencyInAccount + "\r\n" + "free");
                output.close();
            }
            case "currency" -> {
                output.write(moneyInAccount + "\r\n" + changer + "\r\n" + "free");
                output.close();
            }
            case "block" -> {
                output.write(moneyInAccount + "\r\n" + currencyInAccount + "\r\n" + changer);
                output.close();
            }
        }



    }

    protected boolean ifAccountHasNoMoney(String username, String accountNumber) throws IOException {
        BigDecimal moneyInAccount = getAccountMoney(username, accountNumber);
        return (moneyInAccount.compareTo(BigDecimal.ZERO) == 0);
    }

    protected void deleteAccount(String username, String accountNumber) {

        File account1 = new File(System.getProperty("user.dir") + File.separator + username + File.separator + "acc1.txt");
        File account2 = new File(System.getProperty("user.dir") + File.separator + username + File.separator + "acc2.txt");
        File account3 = new File(System.getProperty("user.dir") + File.separator + username + File.separator + "acc3.txt");

        switch (accountNumber) {
            case "1" -> {
                System.out.println(account1.delete());
                System.out.println(account2.renameTo(new File(account1.getPath())));
                System.out.println(account3.renameTo(new File(account2.getPath())));
            }
            case "2" -> {
                System.out.println(account2.delete());
                System.out.println(account3.renameTo(new File(account2.getPath())));
            }
            case "3" -> System.out.println(account3.delete());
        }
    }













    private String getAccountBlockedStatus(String username, String accountNumber) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(System.getProperty("user.dir") + "/" + username + "/" + "acc" + accountNumber + ".txt"));
        return lines.get(2);
    }

    private String getAccountCurrency(String username, String accountNumber) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(System.getProperty("user.dir") + "/" + username + "/" + "acc" + accountNumber + ".txt"));
        return lines.get(1);
    }

    private BigDecimal getAccountMoney(String username, String accountNumber) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(System.getProperty("user.dir") + File.separator + username + File.separator + "acc" + accountNumber + ".txt"));
        return BigDecimal.valueOf(Double.parseDouble(lines.get(0)));
    }


}

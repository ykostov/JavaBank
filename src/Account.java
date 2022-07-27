import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

              if (userInput.startsWith("list")) { listAccounts(); }
              else if (userInput.startsWith("create")) { createAccount(); }
              else if (userInput.startsWith("import"))
              {
                  if (Extranet.getMoneyATM() != null)
                  {
                      importMoneyInAccount();
                  } else
                  {
                      System.out.println("you do not have money in ATM");
                  }

              }
              else if (userInput.startsWith("with")) { WithdrawMoneyFromAccount(); }
              else if (userInput.startsWith("transfer")) { transferMoneyToAnotherUser(newdb); }
              else if (userInput.startsWith("exit")) { break; }
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
        else
        {
            System.out.println("In which account do you want to import money? (1 for acc1, 2 for acc2 or 3 for acc3");
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            if (Integer.parseInt(userInput) > fileCount)
            {
                System.out.println("you have selected an account that you do not have");
            }

                List<String> lines = Files.readAllLines(Path.of(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc"+ userInput + ".txt"));
                BigInteger moneyInAccount = BigInteger.valueOf(Integer.parseInt(lines.get(0)));
                String currencyInAccount = lines.get(1);
                System.out.println("You have " + Extranet.getMoneyATM() + " " + Extranet.getCurrencyInATM() + " money in ATM. The chosen account have " + moneyInAccount + " " + currencyInAccount + ". Please, enter how much money do you want");
                if (Extranet.getCurrencyInATM().equals(currencyInAccount)) {

                    String moneyToImport = scan.nextLine();

                    if (Integer.parseInt(moneyToImport) > Extranet.getMoneyATM().intValue()) {
                        System.out.println("You want to import more money that you have in ATM");
                    } else {
                        try {
                            BigInteger newMoney = moneyInAccount.add(BigInteger.valueOf(Integer.parseInt(moneyToImport)));
                            Writer output = new FileWriter(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc" + userInput + ".txt", false);
                            output.write(String.valueOf(newMoney) + "\r\n" + currencyInAccount);
                            output.close();
                            Extranet.setMoneyATM(Extranet.getMoneyATM().subtract(BigInteger.valueOf(Integer.parseInt(moneyToImport))));
                            System.out.println("Money successfully imported!");
                        } catch (Exception e) {
                            System.out.println("Something went wrong - " + e.toString());
                        }

                    }
                }
                else
                {
                    System.out.println("You either don't have money in your ATM, or the currency of it and of the account does not matches. You can choose different account, import money in ATM with the right currency or change currency of account");
                }



        }

    }

    private void WithdrawMoneyFromAccount() throws IOException {
        if (fileCount == 0) {
            System.out.println("You do not have an account.");
        }
        else
        {
            System.out.println("From which account do you want to withdraw money? (1 for acc1, 2 for acc2 or 3 for acc3");
            Scanner scan = new Scanner(System.in);
            String userInput = scan.nextLine();
            if (Integer.parseInt(userInput) > fileCount)
            {
                System.out.println("you have selected an account that you do not have");
            }

            List<String> lines = Files.readAllLines(Path.of(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc"+ userInput + ".txt"));
            BigInteger moneyInAccount = BigInteger.valueOf(Integer.parseInt(lines.get(0)));
            while(true)
            {
                System.out.println("In this account you have " + moneyInAccount);
                System.out.println("how much do you want to withdraw?");
                String moneyToWithdraw = scan.nextLine();
                if(Integer.parseInt(moneyToWithdraw) > Integer.parseInt(lines.get(0)))
                {
                    System.out.println("you have entered an amount that is higher than your account. Please, try again");
                    continue;
                }
                try
                {
                    BigInteger newMoney = moneyInAccount.subtract(BigInteger.valueOf(Integer.parseInt(moneyToWithdraw)));
                    Writer output = new FileWriter(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc"+ userInput + ".txt", false);
                    output.write(String.valueOf(newMoney));
                    output.close();
                    if (Extranet.getMoneyATM() == null)
                    {
                        Extranet.setMoneyATM(BigInteger.valueOf(Integer.parseInt(moneyToWithdraw)));
                    }
                    else
                    {
                        Extranet.setMoneyATM(Extranet.getMoneyATM().add(BigInteger.valueOf(Integer.parseInt(moneyToWithdraw))));
                    }
                    System.out.println("Money successfully withdrew");
                    break;
                } catch (Exception e)
                {
                    System.out.println("Something went wrong - " + e.toString());
                }

            }

        }
    }

    private void transferMoneyToAnotherUser(Database newdb) throws IOException {
        System.out.println("money in ATM: " + Extranet.getMoneyATM() + ". Enter how much would you want to transfer");
        Scanner scan = new Scanner(System.in);
        String moneyToTransfer = scan.nextLine();

        if (Integer.parseInt(moneyToTransfer) > Extranet.getMoneyATM().intValue())
        {
            System.out.println("You want to transfer more money that you have in ATM");
        }
        System.out.println("write username to transfer money from ATM");

        String usernameToTransferMoney = scan.nextLine();
        if (newdb.checkUsername(usernameToTransferMoney))
        {
            List<String> lines = Files.readAllLines(Path.of(System.getProperty("user.dir") + "/" + usernameToTransferMoney + "/" + "acc1.txt"));
            BigInteger moneyInAccount = BigInteger.valueOf(Integer.parseInt(lines.get(0)));
            BigInteger newMoney = moneyInAccount.add(BigInteger.valueOf(Integer.parseInt(moneyToTransfer)));
            Writer output = new FileWriter(System.getProperty("user.dir") + "/" + usernameToTransferMoney + "/" + "acc1.txt", false);
            output.write(String.valueOf(newMoney));
            output.close();
        }

    }


}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Account {

    private boolean moreThanThreeAccounts = false;
    private int fileCount = 0;

    private void countAccountsCreatedForUser()
    {
        try
        {
            File directory=new File(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName());
            fileCount= directory.list().length;
        } catch (NullPointerException e)
        {
            moreThanThreeAccounts = false;
        }

        if (fileCount > 3)
        {
            moreThanThreeAccounts = true;
        }

    }

    public void setupAccount() throws IOException {
      countAccountsCreatedForUser();
      String userInput;
      if (moreThanThreeAccounts)
      {
          System.out.println(Message.MAXACC);
      }
      else {
          while(true) {
              if (fileCount == 0)
              {
                  System.out.println("You have 0 accounts. Would you like to 'create' a new one or 'exit'?");
              }
              else
              {
                  System.out.println(fileCount + " " + Message.ACCMENU);
              }

              Scanner scan = new Scanner(System.in);
              userInput = scan.nextLine();

              if (userInput.startsWith("list")) { listAccounts(); }
              else if (userInput.startsWith("create")) { createAccount(); }
              else if (userInput.startsWith("exit")) { break; }
            }
        }

    }
    private void listAccounts() throws IOException {
        for (int i = 1; i<=fileCount; i++)
        {
            System.out.print("Account"+i + "=");
            List<String> accounts = Files.readAllLines(Path.of(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc"+ i + ".txt"));
            System.out.println(accounts);
        }
    }
    private void createAccount() throws IOException {
        if (fileCount >= 3)
        {
            System.out.println(Message.MAXACC);
        }
        else
        {

            try
            {
            FileWriter fw = new FileWriter(System.getProperty("user.dir") + "/" + Extranet.getCurrentUserName() + "/" + "acc"+ ++fileCount + ".txt");

            System.out.println(Message.SUCCESSACC);
            }catch (Exception ex)
            {
                // write data to logger
            }


        }

    }


}

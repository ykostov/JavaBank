import java.io.File;
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

    public void setupAccount()
    {
      countAccountsCreatedForUser();
      String userInput;
      if (moreThanThreeAccounts)
      {
          System.out.println("you have maximum accounts allowed for a basic user (3)");
      }
      else {
          while(true) {
              System.out.println("You have " + fileCount + "accounts. Would you like to 'list' them, 'create' a new one or 'exit'?");
              Scanner scan = new Scanner(System.in);
              userInput = scan.nextLine();

              if (userInput.startsWith("list")) {

              } else if (userInput.startsWith("create")) {

              } else if (userInput.startsWith("exit")) {
                  break;
              }
      }
      }

    }

}

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final boolean isAdmin = false;
    private static final Scanner scan = new Scanner(System.in);
    static Database newdb = new Database(false);

    public Main() throws SQLException {
    }

    public static void main(String[] args) throws IOException, SQLException {




        // newdb.switcher();



        Menu.ASCII();

        Messages.setupMap();
        openMainMenu(newdb);



    }

   private static void tester() throws SQLException
   {

    try {
        Connection con = DriverManager.getConnection("jdbc:sqlite:/home/spooky/Videos/sqlite/usersdb.db");

        Statement statement = con.createStatement();

        String sql = "SELECT * from users";

        ResultSet result = statement.executeQuery(sql);




        while(result.next())
        {

            result.getString(1);


        }
        System.out.println("not created");




    } catch (SQLException e)
    {
        e.printStackTrace();
    }

   }

    private static void chooseLanguage()
    {
        while(true)
        {
            System.out.println("Choose language - en for english or bg for bulgarian");
            String languageInput = scan.nextLine();
            if (languageInput.startsWith("e"))
            {
                Extranet.setCurrentLanguage("en");
                break;
            }
            else if (languageInput.startsWith("b"))
            {
                Extranet.setCurrentLanguage("bg");
                break;
            }
            else
            {
                System.out.println("I did not understand you. Plz try again");
            }
        }

    }

    private static void openMainMenu(Database newdb) throws IOException {
        while (true) {

            System.out.println(Messages.getMessage(Extranet.getCurrentLanguage() + "-welcome"));  // Hello World
            String userInput = scan.nextLine();
            try {


                if (userInput.toLowerCase().trim().startsWith("log")) {
                    Extranet.login(newdb, isAdmin);

                } else if (userInput.toLowerCase().trim().startsWith("reg")) {
                    Extranet.register(newdb, isAdmin);

                } else if (userInput.toLowerCase().trim().startsWith("admin")) {
                    Admin.main(newdb);

                } else if (userInput.toLowerCase().trim().startsWith("lang")) {
                    chooseLanguage();
                }
                else if (userInput.toLowerCase().trim().startsWith("db")) {
                    changeDb();
                }else if (userInput.toLowerCase().trim().startsWith("exit")) {
                    break;
                } else {
                    System.out.println(Message.BADINPUT);
                    debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "ERROR: Bad input at openMainMenuBad");
                }
            } catch (Exception e)
            {
                debugMode.addDataToLogger(String.valueOf(java.time.LocalTime.now()), "ERROR: Very very bad: " + e.getMessage() + Arrays.toString(e.getStackTrace()));
            }
        }
    }

    private static void changeDb() {
        System.out.println(newdb.switcher());
    }


}

/*
 //    String sql1 = "INSERT INTO users VALUES ('baiivan', 'vanku')";
//        String sql1 = "DELETE FROM users WHERE name = 'baiivan'";
        String sql = "SELECT * FROM users";
        Statement statement = con.createStatement();
        String checker = ("SELECT 1 FROM users WHERE name = 'gosho'");

//        statement.executeUpdate(sql1);
     //   statement.executeUpdate(sql1);
        ResultSet result = statement.executeQuery(sql);
        ResultSet result2 = statement.executeQuery(checker);



//        while(result.next())
//        {
//            String passwd = result2.getString("password");
//            System.out.println("password: " + passwd);
//        }

        while(result.next())
        {
            String name = result.getString("name");
            String passwd = result.getString("password");
            System.out.println(" | " + name + " | " + passwd);
        }
 */

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        /* TODO:

            ## Функционалности
            * Логин система за клиенти и служители
            * Клиенти
                * Информация за клиента
                * VIP клиенти
                * Теглене на пари
                * Внасяне на пари
                * Прехвърляне между вътрешни сметки
                * Прехвърляне към сметка на друг потребител
                * Кредитни карти
                * Сметки (2+)
                * Валути (2+)
                * Езици (2+)
            * Служители
                * Информация за служителят
                * Администриране на потребителите
                * Изтриване на сметка
                * Блокиране на сметка
                * Конфигурация на лихвеният процент
            * База (памет / файл)
            * Конфигурационнен файл
                * Дебъг режим (показва повече съобщения) **записва в отделен файл**
                * Смяна на базата
                * Локация на файлът за базата

         */

        Database newdb = new Database("users");
        newdb.createDb();

        System.out.println(Message.WELCOME);  // Hello World
        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();

        while (true)
        {
            if (userInput.startsWith("log"))
            {
                Extranet.login(newdb);
                break;
            }
            else if (userInput.startsWith("reg"))
            {
                Extranet.register(newdb);
                break;
            }
            else if (userInput.startsWith("exit"))
            {
                break;
            }
            else
            {
                System.out.println("I do not understand you. Please, try again. (login or register)");
            }
        }


    }
}

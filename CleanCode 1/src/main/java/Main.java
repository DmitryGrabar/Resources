import JSONFileWorkers.Reader;
import JSONFileWorkers.Writer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Chat chat = new Chat();
        Reader read = new Reader();
        Writer write = new Writer();
        Scanner input = new Scanner(System.in);
        int added = 0;
        int finded = 0;
        int deleted = 0;
        StringBuilder sb = new StringBuilder();
        try {
            while (true) {
                System.out.println("\nДобро пожаловать в меню чата.");
                System.out.println("Вы можете выполнить следующие действия: ");
                System.out.println("Добавление сообщения - 1");
                System.out.println("Вывести историю сообщений - 2");
                System.out.println("Поиск сообщения используя регулярное выражение - 3");
                System.out.println("Поиск сообщения по автору - 4");
                System.out.println("Поиск сообщения по лексеме - 5");
                System.out.println("Поиск сообщения по времени его создания - 6");
                System.out.println("Удаление сообщения по ID-номеру - 7");
                System.out.println("Запись в файл в JSON-формате - 8");
                System.out.println("Чтение из файла в JSON-формате - 9");
                System.out.println("Выход из чата и запись лога - 10");
                System.out.println("\n" + "Что вы хотите сделать ?");
                switch (input.nextInt()) {
                    case 1:
                        try {
                            chat.addMessage(chat.readMessage());
                            sb.append("Сообщение добавлено.\n");
                            added++;
                        } catch (ArrayIndexOutOfBoundsException ex) {
                            System.out.println("Ваше сообщение не удовлетворяет ГОСТ-у." + "\n");
                            sb.append("Ваше сообщение не удовлетворяет ГОСТ-у." + "\n");
                        }
                        break;
                    case 2:
                        chat.showMessageList();
                        sb.append("Вывод истории сообщений.\n");
                        break;
                    case 3:
                        chat.regularExpressionSearch();
                        sb.append("Поиск по регулярному выражению.\n");
                        finded++;
                        break;
                    case 4:
                        chat.authorSearch();
                        sb.append("Поиск по автору.\n");
                        finded++;
                        break;
                    case 5:
                        chat.lexemeSearch();
                        sb.append("Поиск по лексеме.\n");
                        finded++;
                        break;
                    case 6:
                        chat.timeSearch();
                        sb.append("Поиск  по времени.\n");
                        finded++;
                        break;
                    case 7:
                        try {
                            chat.idDeleteMessage();
                            sb.append("Удаление по ID - номеру.\n");
                            deleted++;
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Вы ввели неверный ID-номер." + "\n");
                            sb.append("Вы ввели неверный ID-номер." + "\n");
                        }
                        break;
                    case 8:
                        write.writeFile(chat.transferMessageToJson());
                        sb.append("Запись в файл.\n");

                        break;
                    case 9:
                        read.readFile();
                        added = chat.transferJsonToMessage(read.getList(), added);
                        sb.append("Чтение из файла.\n");
                        break;
                    case 10:
                        System.out.println("Работа чата завершена.");
                        sb.append("Завершение работы чата.\n");
                        write.writeLog(added, finded, deleted, sb);
                        System.exit(0);
                }
            }
        } catch (InputMismatchException ex) {
            System.out.println("Неверная команда." + "\nРабота чата завершена.");
            sb.append("Неверная команда." + "\nРабота чата завершена.");
        }
    }
}

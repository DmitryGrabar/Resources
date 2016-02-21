import JSONFileWorkers.Writer;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chat {
    private List<Message> messages;
    Scanner in = new Scanner(System.in);

    public Chat() {
        messages = new ArrayList<Message>();
    }

    public Message readMessage() {
        System.out.println("Введите ваше сообщение и через // имя: " + "\n" + "P.S.   Blablablablablabla.//DMITRY  ");
        String inputLine = in.nextLine();
        String tokens[] = inputLine.split("//");
        return new Message(tokens[0], tokens[1]);
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void idDeleteMessage() {
        System.out.println("Введите ID-номер для удаления сообщения : ");
        UUID id = UUID.fromString(in.nextLine());
        for (int i = 0; i < this.messages.size(); i++) {
            if (this.messages.get(i).getID().equals(id)) {
                messages.remove(this.messages.get(i));
            }
        }
    }

    public void authorSearch() {
        System.out.println("Введите автора для поиска сообщения : ");
        String author = in.nextLine();
        for (Message element : this.messages) {
            if (element.getAuthor().equals(author)) {
                showMessage(element);
            }
        }
    }

    public void showMessage(Message message) {
        System.out.println(message.getAuthor() + ": " + message.getText() + " " + message.getDate() + " ID: " + message.getID() + "\n");
    }

    public void showMessageList() {
        for (Message element : this.messages) {
            showMessage(element);
        }
    }

    public void lexemeSearch() {
        System.out.println("Введите лексему для поиска сообщения : ");
        String lexeme = in.nextLine();
        for (Message element : this.messages) {
            if (element.getText().contains(lexeme)) {
                showMessage(element);
            }
        }
    }

    public void regularExpressionSearch() {
        System.out.println("Поиск сообщений по регулярному выражению.");
        System.out.println("Введите регулярное выражение : ");
        String regularExpression = in.nextLine();
        for (Message element : this.messages) {
            Pattern pattern = Pattern.compile(regularExpression);
            Matcher matcher = pattern.matcher(element.getText());
            if (matcher.find()) {
                showMessage(element);
            } else {
                System.out.println("Такого сообщения не существует.");
            }
        }
    }

    public void timeSearch() {
        TimeSearcher ts = new TimeSearcher();
        for (Message element : this.messages) {
            if (ts.rightDate(element)) {
                showMessage(element);
            }
        }
    }

    public List<JsonObject> transferMessageToJson() {
        Writer writer = new Writer();
        for (Message element : this.messages) {
            writer.addObj(writer.readObject(element.getAuthor(), element.getText(), element.getDate(), element.getID()));
        }
        return writer.getList();
    }

    public void transferJsonToMessage(List<JsonObject> jsonList) {
        for (JsonObject element : jsonList) {
            this.messages.add(new Message(element));
        }
    }
}

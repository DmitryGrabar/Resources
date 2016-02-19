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

    private Message readMessage() {
        System.out.println("Enter your message, and by // name: " + "\n" + "P.S.   Blablablablablabla.//DMITRY  ");
        String inputLine = in.nextLine();
        String tokens[] = inputLine.split("//");
        return new Message(tokens[0], tokens[1]);
    }

    private void addMessage(Message message) {
        this.messages.add(message);
    }

    private void idDeleteMessage() {
        System.out.println("Enter ID to delete message : ");
        UUID id =  UUID.fromString(in.nextLine());
        for (Message element : this.messages) {
            if (element.getID().equals(id)) {
                messages.remove(element);
            }
        }
    }

    private void authorSearch() {
        System.out.println("Enter author to find message(s) : ");
        String author = in.nextLine();
        for (Message element : this.messages) {
            if (element.getAuthor().equals(author)) {
                showMessage(element);
            }
        }
    }

    private void showMessage(Message message) {
        System.out.println(message.getAuthor() + ": " + message.getText() + " " + message.getDate() + " ID: " + message.getID());
    }

    private void showMessageList() {
        for (Message element : this.messages) {
            showMessage(element);
        }
    }

    private void lexemeSearch() {
        System.out.println("Enter lexem to find message(s) : ");
        String lexeme = in.nextLine();
        for (Message element : this.messages) {
            if (element.getText().contains(lexeme)) {
                showMessage(element);
            }
        }
    }

    private void regularExpressionSearch() {
        System.out.println("Regular expression search.");
        System.out.println("Input regular expression : ");
        String regularExpression = in.nextLine();
        for (Message element : this.messages) {
            Pattern pattern = Pattern.compile(regularExpression);
            Matcher matcher = pattern.matcher(element.getText());
            if (matcher.find()) {
                showMessage(element);
            } else {
                System.out.println("Not found.");
            }
        }
    }

    private void timeSearch() {
        TimeSearcher ts = new TimeSearcher();
        for (Message element : this.messages) {
            if (ts.rightDate()) {
                showMessage(element);
            }
        }
    }

    public static void main(String[] args) {
        Chat chat = new Chat();

        chat.addMessage(chat.readMessage());
//        chat.showMessageList();
//        chat.regularExpressionSearch();
//        chat.authorSearch();
//        chat.lexemeSearch();
        chat.timeSearch();
        chat.idDeleteMessage();
        chat.showMessageList();
    }
}

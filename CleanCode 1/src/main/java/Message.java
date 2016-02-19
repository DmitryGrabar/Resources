import java.util.Date;
import java.util.UUID;

public class Message {
    private UUID id;
    private Date timestamp;
    private String text;
    private String author;

    public Message(){
        this.id = UUID.randomUUID();
        this.timestamp = new Date(System.currentTimeMillis());
        this.text = "";
        this.author = "";
    }
    public Message(String text, String author) {
        this.id = UUID.randomUUID();
        this.timestamp = new Date(System.currentTimeMillis());
        this.text = text;
        this.author = author;
    }
    public UUID getID (){
        return this.id;
    }
    public Date getDate(){
        return this.timestamp;
    }
    public String getText(){
        return this.text;
    }
    public String getAuthor(){
        return this.author;
    }
}

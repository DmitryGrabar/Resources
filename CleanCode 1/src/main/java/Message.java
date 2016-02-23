import javax.json.JsonObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Message {
    private UUID id;
    private Date timestamp;
    private String text;
    private String author;

    public Message(String text, String author) {
        this.id = UUID.randomUUID();
        this.timestamp = new Date(System.currentTimeMillis());
        this.text = text;
        this.author = author;
    }
    private Date parseDate(JsonObject jsonObject){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        try {
            return sdf.parse(jsonObject.getString("Date: "));
        }catch (ParseException ex){
            System.out.println(ex.getMessage());
            return getDate();
        }
    }
    public Message(JsonObject jsonObject) {

        this.timestamp = parseDate(jsonObject);
        this.id = UUID.fromString(jsonObject.getString("ID-number: "));
        this.text = jsonObject.getString("MessageText:");
        this.author = jsonObject.getString("Author:");
    }

    public UUID getID() {
        return this.id;
    }

    public Date getDate() {
        return this.timestamp;
    }

    public String getText() {
        return this.text;
    }

    public String getAuthor() {
        return this.author;
    }
}

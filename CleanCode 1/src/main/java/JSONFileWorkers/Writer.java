package JSONFileWorkers;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Writer {
    private List<JsonObject> writeMessages;

    public Writer() {
        writeMessages = new ArrayList<JsonObject>();
    }

    public JsonObject readObject(String author, String text, Date timestamp, UUID id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return Json.createObjectBuilder()
                .add("Author:", author)
                .add("MessageText:", text)
                .add("Date: ", sdf.format(timestamp))
                .add("ID-number: ", id.toString()).build();
    }

    public void addObj(JsonObject object) {
        this.writeMessages.add(object);
    }

    public List<JsonObject> getList() {
        return this.writeMessages;
    }

    public void writeFile(List<JsonObject> humanMessages) {
        File file = new File("output.txt");
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        try {
            FileWriter fw = new FileWriter(file);
            for (JsonObject element : humanMessages) {
                writer.writeObject(element);
                fw.write(stringWriter.getBuffer().toString());
            }
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

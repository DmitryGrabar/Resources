package JSONFileWorkers;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.*;

public class Reader {

    private List<JsonObject> readMessages;

    public Reader(){
        readMessages = new ArrayList<JsonObject>();
    }

    public void addObj(JsonObject object) {
        this.readMessages.add(object);
    }

    public List<JsonObject> getList() {
        return this.readMessages;
    }

    public void readFile() {
        try {
            Scanner sc = new Scanner(new File("input.txt").getAbsoluteFile());
            while (sc.hasNextLine()) {
                JsonReader reader = Json.createReader(new StringReader(sc.nextLine()));
                JsonObject message = reader.readObject();
                addObj(message);
                reader.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

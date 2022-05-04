package persistence;

import model.Batter;
import model.ListOfBatters;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Modeled from JsonSpecializationDemo @ https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads listofbatters from JSON data stored in file
public class JsonReaderBatter {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderBatter(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfBatters from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfBatters read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfBatters(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListOfBatters from JSON object and returns it
    private ListOfBatters parseListOfBatters(JSONObject jsonObject) {
        ListOfBatters lb = new ListOfBatters();
        addBatters(lb, jsonObject);
        return lb;
    }

    // MODIFIES: lb
    // EFFECTS: parses 'List Of Batters' from JSON object and adds them to ListOfBatters
    private void addBatters(ListOfBatters lb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("List Of Batters");
        for (Object json : jsonArray) {
            JSONObject nextBatter = (JSONObject) json;
            addBatter(lb, nextBatter);
        }
    }

    // MODIFIES: lb
    // EFFECTS: parses Batter from JSON object and adds it to ListOfBatters
    private void addBatter(ListOfBatters lb, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int ab = jsonObject.getInt("at bat");
        int runs = jsonObject.getInt("runs");
        int hit = jsonObject.getInt("hits");
        int doubles = jsonObject.getInt("doubles");
        int triples = jsonObject.getInt("triples");
        int hr = jsonObject.getInt("hr");
        int bb = jsonObject.getInt("bb");
        int rbi = jsonObject.getInt("rbi");

        Batter b = new Batter(name, ab, runs, hit, doubles, triples, hr, bb, rbi);
        lb.addBatter(b);
    }
}

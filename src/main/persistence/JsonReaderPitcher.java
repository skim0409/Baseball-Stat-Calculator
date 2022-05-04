package persistence;


import model.ListOfPitchers;
import model.Pitcher;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Modeled from JsonSpecializationDemo @ https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads listofpitchers from JSON data stored in file
public class JsonReaderPitcher {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderPitcher(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfPitchers from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfPitchers read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfPitchers(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListOfPitchers from JSON object and returns it
    private ListOfPitchers parseListOfPitchers(JSONObject jsonObject) {
        ListOfPitchers lp = new ListOfPitchers();
        addPitchers(lp, jsonObject);
        return lp;
    }

    // MODIFIES: lp
    // EFFECTS: parses 'List Of Pitchers' from JSON object and adds them to ListOfPitchers
    private void addPitchers(ListOfPitchers lp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("List Of Pitchers");
        for (Object json : jsonArray) {
            JSONObject nextPitcher = (JSONObject) json;
            addPitcher(lp, nextPitcher);
        }
    }

    // MODIFIES: lp
    // EFFECTS: parses pitcher from JSON object and adds it to ListOfPitcher
    private void addPitcher(ListOfPitchers lp, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int wins = jsonObject.getInt("wins");
        int losses = jsonObject.getInt("losses");
        double innings = jsonObject.getDouble("innings");
        int er = jsonObject.getInt("er");
        int hits = jsonObject.getInt("hits");
        int bb = jsonObject.getInt("bb");
        int so = jsonObject.getInt("so");

        Pitcher p = new Pitcher(name, wins, losses, innings, er, hits, bb, so);
        lp.addPitcher(p);
    }

}

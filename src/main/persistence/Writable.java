package persistence;

import org.json.JSONObject;

// Modeled from JsonSpecializationDemo @ https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {
    // returns this as JSON obj
    JSONObject toJson();
}

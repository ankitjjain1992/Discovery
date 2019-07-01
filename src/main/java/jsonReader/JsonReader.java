package jsonReader;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Reporter;

public class JsonReader {

	public static JSONObject jsonObject;

	public static JSONObject jsoncreater(String FilePath) {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader("./data/" + FilePath));
			jsonObject = (JSONObject) obj;
		} catch (Exception e) {
			Reporter.log(e.getMessage(), true);
		}
		return jsonObject;
	}
}
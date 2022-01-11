package mirea.ippo.parser;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseJson {

    public JSONObject parseJsonFromService(String json) throws ParseException {
        String strinG = "{\"Persons\":" + json + "}";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(strinG);
        return jsonObject;
    }
}

package mirea.ippo.rest;


import com.sun.jersey.api.client.Client;
import mirea.ippo.client.HttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("/get/allPersons")
    public JSONObject getAllPersons() throws ParseException {
        Client client = new Client();
        HttpClient httpClient = new HttpClient();
        return httpClient.getJson(client, "http://localhost:8333/get/allPersons");
    }

    @PostMapping(path = "/get/byId", consumes = "*/*;charset=UTF-8", produces = "application/json")
    public JSONObject authorization(@RequestBody String member) throws ParseException {
        System.out.println("Connection is true");
        Client client = new Client();
        HttpClient httpClient = new HttpClient();
        return httpClient.postJson(client, "http://localhost:8333/get/byId", member);
    }

}

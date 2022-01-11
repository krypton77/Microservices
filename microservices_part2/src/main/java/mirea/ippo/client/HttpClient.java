package mirea.ippo.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import mirea.ippo.parser.ParseJson;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


public class HttpClient {

    public JSONObject getJson(Client client, String url) throws ParseException {
        WebResource webResource = client.resource(url);

        ClientResponse response = webResource.get(ClientResponse.class);

        // Status 200 is successful.
        if (response.getStatus() != 200) {
            System.out.println("Failed with HTTP Error code: " + response.getStatus());
            String error = response.getEntity(String.class);
            System.out.println("Error: " + error);
            //return null;
        }
        String output = response.getEntity(String.class);
        System.out.println("Output from cloud .... \n" + output);
        ParseJson parseJson = new ParseJson();
        return parseJson.parseJsonFromService(output);

    }

    public JSONObject postJson(Client client, String url, String object) throws ParseException {


        WebResource webResource = client.resource(url);

        // POST method
        ClientResponse response = webResource.accept("application/json")
                .type("application/json").post(ClientResponse.class, object);

        // check response status code

        if (response.getStatus() == 500){
            System.out.println("Такого пользователя не существует, проверьте введенные данные");
            ParseJson parseJson = new ParseJson();
            return parseJson.parseJsonFromService("{\"error\":\"We do not have such Person\"}");
        }
        else if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        // display response
        String output = response.getEntity(String.class);
        System.out.println("Output from Server .... ");
        System.out.println(output + "\n");

        ParseJson parseJson = new ParseJson();
        return parseJson.parseJsonFromService(output);
    }
}

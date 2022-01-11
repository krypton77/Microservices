package mirea.ippo.rest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import mirea.ippo.service.PersonService;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    PersonService personService;

    @GetMapping("/get/allPersons")
    public String getAllPersons(){
        return personService.allPersons().toString();
    }

    @PostMapping(path = "/get/byId", consumes = "*/*;charset=UTF-8", produces = "application/json")
    public String authorization(@RequestBody String member) throws ParseException {
        System.out.println("Connection is true");
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(String.valueOf(member));
        return personService.getPersonById(Integer.parseInt(json.get("id").toString())).toString();
    }

//    public boolean checkPerson(String id){
//        if(personService.allPersons().get(id)){
//
//        }
//    }
}

package mirea.ippo.service;


import mirea.ippo.entity.Person_first;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mirea.ippo.repository.PersonRepository;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private final PersonRepository personRepository;


    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person_first> allPersons(){
        return personRepository.allPersons();
    }

    public Person_first getPersonById(Integer id) {
        return personRepository.getPersonById(id);
    }
}

package com.person.pact.provider;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PersonRepository {

    private static int ID_COUNTER = 1;

    Map<Integer, Person> map = new HashMap<>();

    public void add(Person person) {
        SsnValidator.validate(person.getSsn());
        person.setId(ID_COUNTER++);
        map.put(person.getId(), person);
    }

    public Person get(Integer id) {
        return map.get(id);
    }

    public Person getBySSn(String ssn) {
        return map.values().stream()
                .filter(person -> person.getSsn().equals(ssn))
                .findFirst().orElse(null);
    }
}

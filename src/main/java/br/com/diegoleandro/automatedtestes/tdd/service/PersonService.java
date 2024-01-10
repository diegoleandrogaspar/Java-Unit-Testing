package br.com.diegoleandro.automatedtestes.tdd.service;

import br.com.diegoleandro.automatedtestes.tdd.model.Person;

import java.util.concurrent.atomic.AtomicLong;

public class PersonService implements IPersonService {

    @Override
    public Person createPerson(Person person) {
       // Simulando acesso ao banco de dados

        if (person.getEmail() == null || person.getEmail().isBlank()) {
            throw new IllegalArgumentException("The Person e-mail is null or empty!");
        }

        var id = new AtomicLong().incrementAndGet();
        person.setId(id);
        return  person;
    }
}

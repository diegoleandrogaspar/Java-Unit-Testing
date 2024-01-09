package br.com.diegoleandro.automatedtestes.service;

import br.com.diegoleandro.automatedtestes.model.Person;

public class PersonService implements IPersonService {


    @Override
    public Person createPerson(Person person) {

        return  new Person();

    }
}

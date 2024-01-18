package br.com.diegoleandro.automatedtestes.services;

import br.com.diegoleandro.automatedtestes.exceptions.ResourceNotFoundException;
import br.com.diegoleandro.automatedtestes.model.Person;
import br.com.diegoleandro.automatedtestes.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private static final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    public List<Person> findAll() {
      return personRepository.findAll();
    }

    public Person findById(Long id) {

    logger.info("Finding one person!");

    Person person = new Person();
    person.setId(counter.incrementAndGet());
    person.setFirstName("Diego");
    person.setLastName("Leandro");
    person.setAddress("Rio de Janeiro");
    person.setGender("Male");

    return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!" ));

    }

    public Person create(Person person) {
        logger.info("Creating one person!");

        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Update one person!");

       var entity = personRepository.findById(person.getId())
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

       entity.setFirstName(person.getFirstName());
       entity.setLastName(person.getLastName());
       entity.setAddress(person.getAddress());
       entity.setGender(person.getGender());

       return personRepository.save(person);
    }

    public void delete(Long id) {
        logger.info("Delete one person!");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        personRepository.delete(entity);
    }

    private Person mockPerson(int i) {

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("lastName " + i);
        person.setAddress("Some address on Brasil " + i);
        person.setGender("Male");
        return person;

    }
}

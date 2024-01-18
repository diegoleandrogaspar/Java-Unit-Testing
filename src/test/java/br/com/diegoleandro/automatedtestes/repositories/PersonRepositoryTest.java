package br.com.diegoleandro.automatedtestes.repositories;


import br.com.diegoleandro.automatedtestes.model.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTest  {

    @Autowired
    private PersonRepository personRepository;

    @DisplayName("Given person Object when save the Return Saved Person")
    @Test
    void testGivenPersonObject_whenSave_thenReturnedSavedPerson() {

     // Given
    Person person0 = new Person("Diego", "Leandro",  "Rio de Janeiro - RJ - Brasil","Male",  "diego@mail.com"  );

     // When
    Person savedPerson = personRepository.save(person0);

    // Then
     assertNotNull(savedPerson);
     assertTrue(savedPerson.getId() > 0);

    }

    @DisplayName("Given person List when save the Return Saved Person List")
    @Test
    void testGivenPersonList_whenFindAll_thenReturnedSPersonList() {

        // Given
        Person person0 = new Person("Diego", "Leandro", "Rio de Janeiro - RJ - Brasil", "Male", "diego@mail.com");
        Person person1 = new Person("Zidane", "Leandro", "Rio de Janeiro - RJ - Brasil", "Male", "diego@mail.com");

        personRepository.save(person0);
        personRepository.save(person1);


        // When
        List<Person> personList = personRepository.findAll();

        // Then
        assertNotNull(personList);
        assertEquals(2, personList.size());

    }


    @DisplayName("Given person Object when findByID the Return Person Object")
    @Test
    void testGivenPersonObject_whenFindById_thenReturnedSavedPersonObject() {

        // Given
        Person person0 = new Person("Diego", "Leandro",  "Rio de Janeiro - RJ - Brasil","Male",  "diego@mail.com"  );
        personRepository.save(person0);

        // When
        Person savedPerson = personRepository.findById(person0.getId()).get();


        // Then
        assertNotNull(savedPerson);
        assertEquals(person0.getId(), savedPerson.getId());

    }



    }

package br.com.diegoleandro.automatedtestes.repositories;


import br.com.diegoleandro.automatedtestes.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTest  {

    @Autowired
    private PersonRepository personRepository;


    private Person person0;

    @BeforeEach
    public void setup() {
      //Given
     person0 = new Person("Diego", "Leandro",  "Rio de Janeiro - RJ - Brasil","Male",  "diego@mail.com"  );

    }



    @DisplayName("Given person Object when save the Return Saved Person")
    @Test
    void testGivenPersonObject_whenSave_thenReturnedSavedPerson() {
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

        personRepository.save(person0);

        // When
        Person savedPerson = personRepository.findById(person0.getId()).get();


        // Then
        assertNotNull(savedPerson);
        assertEquals(person0.getId(), savedPerson.getId());

    }


    @DisplayName("Given person Object when FindByEmail the Return Person Object")
    @Test
    void testGivenPersonObject_whenFindByEmail_thenReturnedSavedPersonObject() {

        personRepository.save(person0);

        // When
        Person savedPerson = personRepository.findByEmail(person0.getEmail()).get();


        // Then
        assertNotNull(savedPerson);
        assertEquals(person0.getId(), savedPerson.getId());

    }

    @DisplayName("Given person Object when Updated Person the Return Updated Person Object")
    @Test
    void testGivenPersonObject_whenUpdatePerson_thenReturnedUpdatePersonObject() {

        personRepository.save(person0);

        // When
        Person savedPerson = personRepository.findById(person0.getId()).get();
        savedPerson.setFirstName("Leonardo");
        savedPerson.setEmail("leonardo@erudio.com.br");

        Person updatePerson = personRepository.save(savedPerson);


        // Then
        assertNotNull(updatePerson);
        assertEquals("Leonardo", updatePerson.getFirstName());
        assertEquals("leonardo@erudio.com.br", savedPerson.getEmail());
    }

    @DisplayName("Given person Object when Delete the Remove Person")
    @Test
    void testGivenPersonObject_whenDelete_thenRemovePerson() {

        personRepository.save(person0);

        // When
        personRepository.deleteById(person0.getId());
        Optional<Person> personOptional = personRepository.findById(person0.getId());

        // Then
        assertTrue(personOptional.isEmpty());

    }

    @DisplayName("Given firstName and lastName when findJPQL the Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindJPQL_thenReturnedSavedPersonObject() {

        personRepository.save(person0);
        String firstName = "Diego";
        String lastName = "Leandro";


        // When
        Person savedPerson = personRepository.findbyJPQL(firstName, lastName);


        // Then
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());
    }

    @DisplayName("Given firstName and lastName when findJPQLNamedParameters the Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindJPQLNamedParameters_thenReturnedSavedPersonObject() {

        personRepository.save(person0);
        String firstName = "Diego";
        String lastName = "Leandro";


        // When
        Person savedPerson = personRepository.findbyJPQLNamedParameters(firstName, lastName);


        // Then
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());
    }


    @DisplayName("Given firstName and lastName when findByNativeSQL the Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindByNativeSQL_thenReturnedSavedPersonObject() {

        personRepository.save(person0);

        String firstName = "Diego";
        String lastName = "Leandro";


        // When
        Person savedPerson = personRepository.findbyNativeSQL(firstName, lastName);


        // Then
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());
    }


    @DisplayName("Given firstName and lastName when findByNativeSQLwithNamedParameters the Return Person Object")
    @Test
    void testGivenFirstNameAndLastName_whenFindByNativeSQLwithNamedParameters_thenReturnedSavedPersonObject() {

        personRepository.save(person0);

        String firstName = "Diego";
        String lastName = "Leandro";

        // When
        Person savedPerson = personRepository.findbyNativeSQLwithNamedParameters(firstName, lastName);

        // Then
        assertNotNull(savedPerson);
        assertEquals(firstName, savedPerson.getFirstName());
        assertEquals(lastName, savedPerson.getLastName());
    }

}

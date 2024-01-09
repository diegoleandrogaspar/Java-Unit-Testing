package br.com.diegoleandro.automatedtestes.tdd;


import br.com.diegoleandro.automatedtestes.model.Person;
import br.com.diegoleandro.automatedtestes.service.IPersonService;
import br.com.diegoleandro.automatedtestes.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

    @DisplayName("When Create a Person with Success Should Return a Person Object")
    @Test
    void testCreatePerson_WhenSucess_ShouldReturnPersonObject() {
        // Given
        IPersonService service = new PersonService();
        Person person = new Person(
                "diego",
                "leandro",
                "diego@dev.com.br",
                "Rio de Janeiro - BR",
                "Male"
        );

        // When
        Person actual = service.createPerson(person);

        // Then / Assert

        assertNotNull(actual, () -> "The createPerson() should not have returned null! ");


    }




}

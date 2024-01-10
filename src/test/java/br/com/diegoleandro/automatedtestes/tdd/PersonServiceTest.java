package br.com.diegoleandro.automatedtestes.tdd;

import br.com.diegoleandro.automatedtestes.tdd.model.Person;
import br.com.diegoleandro.automatedtestes.tdd.service.IPersonService;
import br.com.diegoleandro.automatedtestes.tdd.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceTest {

    private IPersonService service;

    Person actual;
    Person person;

    @BeforeEach
    void setup() {
        person = new Person(
             "diego",
             "leandro",
             "Rio de Janeiro -BR",
             "Male",
             "diego.dev@gmail.com.br"
        );
        service = new PersonService();
        actual = service.createPerson(person);
    }

    @DisplayName("When Create a Person with Success Should Return a Person Object")
    @Test
    void testCreatePerson_WhenSucess_ShouldReturnPersonObject() {
        // Given

        // When

        // Then / Assert

        assertNotNull(actual, () -> "The createPerson() should not have returned null! ");

    }

    @DisplayName("When create a Person With Sucess Should Contains Valid Fields in Returned Person Object")
    @Test
    void testCreatePerson_whenSucess_ShouldContainsValidFieldsInReturnedPersonObject() {

        assertNotNull(person.getId(), () -> "Person ID is Missing");
        assertEquals(
              person.getFirstName(),
              actual.getFirstName(),
              () -> "The firstName is Incorrect!");
        assertEquals(
                person.getLastName(),
                actual.getLastName(),
                () -> "The lastName is Incorrect!");
        assertEquals(
                person.getAddress(),
                actual.getAddress(),
                () -> "The Address is Incorrect!");
        assertEquals(
                person.getGender(),
                actual.getGender(),
                () -> "The Gender is Incorrect!");
        assertEquals(
                person.getEmail(),
                actual.getEmail(),
                () -> "The Email is Incorrect!");
    }

    @DisplayName("When Create a Person with null e-Mail Should throw Exception")
    @Test
    void testCreatePerson_WhithNullEMail_ShouldThrowIllegalArgumentException() {
        person.setEmail(null);

        var expectedMessage = "The Person e-mail is null or empty!";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.createPerson(person),
                () -> "Empty E-mail should have cause an IllegalArgumentException!"
        );
        assertEquals(
                expectedMessage,
                exception.getMessage(),
                () -> "Exception error message is incorrect!");
    }
}

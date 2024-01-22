package br.com.diegoleandro.automatedtestes.services;

import br.com.diegoleandro.automatedtestes.exceptions.ResourceNotFoundException;
import br.com.diegoleandro.automatedtestes.model.Person;
import br.com.diegoleandro.automatedtestes.repositories.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.mockito.BDDMockito.*;



@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServices personServices;

    private Person person0;

    @BeforeEach
    public void setup() {

        person0 = new Person(
                "Diego",
                "leandro",
                "Rio de janeiro RJ",
                "Male",
                "diego@email.com");
    }

    @DisplayName("JUnit test for Given Person Object when Save Person then Return Person Object")
    @Test
    void testGivenPersonObject_WhenSavePerson_thenReturnPersonObject() {

        given(personRepository.findByEmail(anyString())).willReturn(Optional.empty());
        given(personRepository.save(person0)).willReturn(person0);

        Person savedPerson = personServices.create(person0);

        Assertions.assertNotNull(savedPerson);
        Assertions.assertEquals("Diego", savedPerson.getFirstName());
    }

    @DisplayName("JUnit test for Given Existing Email  when Save Person then throws Exception")
    @Test
    void testGivenExistingEmail_WhenSavePerson_thenThrowsException() {

        given(personRepository.findByEmail(anyString())).willReturn(Optional.of(person0));

        Assertions.assertThrows(ResourceNotFoundException.class, () ->{
            personServices.create(person0);
        });

        Mockito.verify(personRepository, never()).save(any(Person.class));
    }





}

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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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


    @DisplayName("JUnit test for Given Person List when findALL then Person List")
    @Test
    void testGivenPersonList_WhenFindAllPerson_thenReturnPersonList() {

        Person person1 = new Person("Zidane", "Leandro", "Rio de Janeiro - RJ - Brasil", "Male", "diego@mail.com");

        given(personRepository.findAll()).willReturn(List.of(person0, person1));

        List<Person> personList = personServices.findAll();

        Assertions.assertNotNull(personList);
        Assertions.assertEquals(2, personList.size());
    }

    @DisplayName("JUnit test for Given Empty Person List when findALL then Person List")
    @Test
    void testGivenEmptyPersonList_WhenFindAllPerson_thenReturnEmptyPersonList() {

        given(personRepository.findAll()).willReturn(Collections.emptyList());

        List<Person> personList = personServices.findAll();

        assertTrue(personList.isEmpty());
        Assertions.assertEquals(0, personList.size());
    }

    @DisplayName("JUnit test for Given Return PersonId when findById then personId")
    @Test
    void testGivenPersonId_WhenFindByIdPerson_thenReturnPersonId() {

        Person person1 = new Person( 1L,"Zidane", "Leandro", "Rio de Janeiro - RJ - Brasil", "Male", "diego@mail.com");

        given(personRepository.findById(person1.getId())).willReturn(Optional.of(person1));

        Person personFound = personServices.findById(person1.getId());

        assertNotNull(personFound);
        assertEquals(person1.getId(), personFound.getId());
    }

    @DisplayName("JUnit test for Given Return Person Update when ")
    @Test
    void testGivenPersonObject_WhenUpdatePerson_thenReturnPersonUpdade() {

        person0.setId(1L);
        given(personRepository.findById(anyLong())).willReturn(Optional.of(person0));

        person0.setFirstName("Silva");
        person0.setEmail("silva123@gmail.com");

        given(personRepository.save(person0)).willReturn(person0);

        Person updatePerson = personServices.update(person0);

        assertNotNull(updatePerson);
        assertEquals("Silva", updatePerson.getFirstName());
        assertEquals("silva123@gmail.com", updatePerson.getEmail());
    }

    @DisplayName("JUnit test for Given Person Person Update when ")
    @Test
    void testGivenPersonID_WhenDeletePerson_thenDoNothing() {

        person0.setId(1L);
        given(personRepository.findById(anyLong())).willReturn(Optional.of(person0));
        willDoNothing().given(personRepository).delete(person0);

        personServices.delete(person0.getId());

        verify(personRepository, times(1)).delete(person0);
    }

}

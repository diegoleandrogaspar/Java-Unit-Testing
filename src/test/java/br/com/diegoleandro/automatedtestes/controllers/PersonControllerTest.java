package br.com.diegoleandro.automatedtestes.controllers;








import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.diegoleandro.automatedtestes.exceptions.ResourceNotFoundException;
import br.com.diegoleandro.automatedtestes.model.Person;
import br.com.diegoleandro.automatedtestes.services.PersonServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonServices personServices;

    private Person person;

    @BeforeEach
    public void setup() {
        person = new Person(
           "diego",
           "leandro",
           "Rio de Janeiro",
           "Male",
           "diegoleandro@email.com"
        );
    }

    @Test
    @DisplayName("JUnit test for Given Person Object when Create Person")
    void testGivenPersonObject_WhenCreatePerson_thenReturnSavedPerson() throws Exception {

        // Given
        given(personServices.create(any(Person.class))).willAnswer((invocationOnMock) -> invocationOnMock.getArgument(0));

        // When
      ResultActions response = mockMvc.perform(post("/person")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(person)));

      // then

        response.andDo(print()).andExpect(status().isOk())
              .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
              .andExpect(jsonPath("$.lastName", is(person.getLastName())))
              .andExpect(jsonPath("$.email", is(person.getEmail())));
    }


    @Test
    @DisplayName("JUnit test for Given List Of Persons when List of Person")
    void testGivenListOfPersons_WhenFindAllPerson_thenReturnPersonsList() throws Exception {

        // Given
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add( new Person(
                "silva",
                "gaspar",
                "Rio de Janeiro",
                "Male",
                "silvagaspar@email.com"));

        given(personServices.findAll()).willReturn(persons);

        // When
        ResultActions response = mockMvc.perform(get("/person"));

        // then
        response
           .andExpect(status().isOk())
           .andDo(print())
           .andExpect(jsonPath("$", hasSize(persons.size())));
    }

    @Test
    @DisplayName("JUnit test for Given Person ID when Create Person")
    void testGivenPersonId_WhenFindByIdPerson_thenReturnFindByIdPerson() throws Exception {

        // Given
        long personId = 1L;
        given(personServices.findById(personId)).willReturn(person);

        // When
        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        // then
        response
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(person.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(person.getLastName())))
                .andExpect(jsonPath("$.email", is(person.getEmail())));
    }


    @Test
    @DisplayName("JUnit test for given Invalid PersonId when findById then Return Not Found")
    void testGivenInvalidPersonId_whenFindById_thenReturnNotFound() throws Exception {

        long personId = 1L;
        given(personServices.findById(personId)).willThrow(ResourceNotFoundException.class);

        ResultActions response = mockMvc.perform(get("/person/{id}", personId));

        response.andExpect(status().isNotFound()).andDo(print());
    }

    @Test
    @DisplayName("JUnit test for Given Updated Person when Update then Return Updated Person")
    void testGivenUpdatedPerson_whenUpdate_thenReturnUpdate() throws Exception {

        long personId = 1L;
        given(personServices.findById(personId)).willReturn(person);
        given(personServices.update(any(Person.class)))
                .willAnswer((invocationOnMock -> invocationOnMock.getArgument(0)));

        Person updatedPerson =  new Person(
                "silva",
                "gaspar",
                "Rio de Janeiro",
                "Male",
                "silvagaspar@email.com");

        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updatedPerson)));

        response
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(updatedPerson.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedPerson.getLastName())))
                .andExpect(jsonPath("$.email", is(updatedPerson.getEmail())));
    }

    @Test
    @DisplayName("JUnit test for Given Unexistent when Update then Return Not Found")
    void testGivenUnexistentPerson_whenUpdate_thenReturnNotFound() throws Exception {

        long personId = 1L;
        given(personServices.findById(personId)).willThrow(ResourceNotFoundException.class);
        given(personServices.update(any(Person.class)))
                .willAnswer((invocationOnMock -> invocationOnMock.getArgument(1)));

        Person updatedPerson =  new Person(
                "silva",
                "gaspar",
                "Rio de Janeiro",
                "Male",
                "silvagaspar@email.com");

        ResultActions response = mockMvc.perform(put("/person")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updatedPerson)));

        response
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("JUnit test for Given personId when Delete then Return NotContent")
    void testGivenPersonId_WhenDelete_thenReturnNotContent() throws Exception {

        //Given
        long personId = 1L;
        willDoNothing().given(personServices).delete(personId);

        // When
        ResultActions response = mockMvc.perform(delete("/person/{id}", personId));

        response.andExpect(status().isNoContent()).andDo(print());

    }





}

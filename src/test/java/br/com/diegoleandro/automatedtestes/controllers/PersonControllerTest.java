package br.com.diegoleandro.automatedtestes.controllers;








import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}

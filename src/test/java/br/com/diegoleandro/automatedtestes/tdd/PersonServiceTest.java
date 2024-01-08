package br.com.diegoleandro.automatedtestes.tdd;


import br.com.diegoleandro.automatedtestes.service.IPersonService;
import br.com.diegoleandro.automatedtestes.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonServiceTest {

    @DisplayName("When Create a Person with Success Should Return a Person Object")
    @Test
    void testCreatePerson_WhenSucess_ShouldReturnPersonObject() {

        IPersonService service = new PersonService();


    }




}

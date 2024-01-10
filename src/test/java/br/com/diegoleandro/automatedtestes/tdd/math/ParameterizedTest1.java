package br.com.diegoleandro.automatedtestes.tdd.math;

import  static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


public class ParameterizedTest1 {

    SimpleMath math;

    @BeforeEach
    void beforeEachMethod(){
        math = new SimpleMath();
        System.out.println("Running @BeforeEach method!");
    }


    @ParameterizedTest
    @ValueSource(strings =  {"Diego", "zidane", "henry"})
    void testValurSource(String firstName) {
        System.out.println(firstName);
        assertNotNull(firstName);
    }



    @DisplayName("Test 6.2 / 2 = 3.1")
    @ParameterizedTest
   // @MethodSource("testDivisionInputParameters")

    @CsvSource({
         "6.2, 2, 3.1",
         "71, 14, 5.07",
         "18.3, 3.1, 5.90"
    })
    void testDivision(double firstNumber, double secondNumber, double expected) {

        Double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual,2D,
             () -> firstNumber + "/" + secondNumber + " did not produce " + expected + "!");
    }

    /*
    public static Stream<Arguments> testDivisionInputParameters() {
        return Stream.of(
                Arguments.of(6.2D, 2D, 3.10D),
                Arguments.of(71D, 14D, 5.0D),
                Arguments.of(18.3D, 3.1D, 5.90D)
        );
    }

     */


}

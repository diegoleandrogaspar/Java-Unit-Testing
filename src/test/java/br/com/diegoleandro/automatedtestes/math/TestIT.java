package br.com.diegoleandro.automatedtestes.math;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Math Operations in SimpleMath Class")
public class TestIT {

    SimpleMath math = new SimpleMath();

    @BeforeAll
    static void setup() {
        System.out.println("Running @BeforeAll method!");

    }

    @AfterAll
    static void cleanup() {
        System.out.println("Running @afterAll method!");
    }

    @Test
    @DisplayName("Test 6.2 + 2 = 8.2")
    void testSum_when_SixDotTwoIsAddebedByTwo_ShouldReturnEightDotTwo() {
       // Given
        SimpleMath math = new SimpleMath();

        double firstNumber = 6.2D;
        double secondNumber = 2D;
        double expected = 8.2D;

        //when
        Double actual = math.sum(firstNumber, secondNumber);

        //then
        assertEquals(expected, actual, () -> firstNumber + " + " + secondNumber + " did not produce " + expected + "!");
        assertNotEquals(9.2, actual);
        assertNotNull(actual);

    }

    @Test
    void testSubtraction() {
        SimpleMath math = new SimpleMath();
        double firstNumber = 10D;
        double secondNumber = 5D;

        double expected = 5D;
        double actual = math.subtraction(firstNumber, secondNumber);

        assertEquals(expected, actual);
    }

    @Test
    void testMultiplication() {
        SimpleMath math = new SimpleMath();
        Double firstNumber = 5D;
        Double secondNumber = 5D;

        double expected = 25D;

        double actual = math.multiplication(firstNumber, secondNumber);

        assertEquals(expected, actual);
    }

    @Test
    void testDivision() {
        SimpleMath math = new SimpleMath();
        Double firstNumber = 10D;
        Double secondNumber = 5D;

        double expected = 2D;
        double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual, () -> firstNumber  + " / " + secondNumber + " did not produce " + expected + "!");

    }

    @DisplayName("Test Division by Zero")
    @Test
    void testDivision_when_firstNumberIsDividevByZero_ShouldThrowArithmetic() {

       //given
        double firstNumber = 6.2D;
        double secondNumber = 2D;

        var expectedMessage = "Impossible to divide by zero!";

        //when e then
        ArithmeticException actual = assertThrows(ArithmeticException.class, () -> {
           math.division(firstNumber, secondNumber);
       }, () -> "Division by zero should throw an ArithmeticException");

        assertEquals(expectedMessage, actual.getMessage(), () -> "Unexpected exception message");

    }

    @Test
    public void testIsEven() {
        ParityCheckerTest parityCheckerTest = new ParityCheckerTest();

        boolean expected =  true;

        boolean result = parityCheckerTest.isEven(8);
        assertEquals(expected, result);

    }

    @Test
    void testConcatenate() {
        StringConcatenator stringConcatenator = new StringConcatenator();
        String result = stringConcatenator.concatenate("Hello", "Java");
        String expected = "Hello Java";
        assertEquals(expected, result);
    }

    @Test
    void testCountCharacters() {
        CharacterCounter characterCounter = new CharacterCounter();
        int result = characterCounter.countCharacters("String");
        int expected = 6;
        assertEquals(expected, result);
    }
}

package br.com.diegoleandro.automatedtestes.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;

public class ArraysCompareTest {

    @Test
    void test() {
        int[] numbers = {25,8,21,32,3};
        int[] expectedArray = {3,8,21,25,32};

        Arrays.sort(numbers);

        Assertions.assertArrayEquals(numbers, expectedArray);
    }


}

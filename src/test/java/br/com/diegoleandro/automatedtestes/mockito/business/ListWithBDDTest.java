package br.com.diegoleandro.automatedtestes.mockito.business;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ListWithBDDTest {


     List<?> list;


    @Test
    void testMockingList_When_sizeIsCalled_ShouldReturnMultipleValues() {

        // Given
        given(list.size()).willReturn(10);

        //when
        int size = list.size();

        //Then
        assertThat(size, is(10));

    }

    @Test
    void testMockingList_When_GetIsCalled_ShouldReturnDiego() {

        // Given
        var list = mock(List.class);
        when(list.get(0)).thenReturn("Diego");

        //When & Then
        assertEquals("Diego", list.get(0));
        assertNull(list.get(1));
    }

    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnDiego() {

        // Given
        var list = mock(List.class);
        when(list.get(anyInt())).thenReturn("Diego");

        //When & Then
        assertEquals("Diego", list.get(anyInt()));
        assertEquals("Diego",list.get(anyInt()));
    }


    @Test
    void testMockingList_When_ThrowsAnException() {

        // Given
        var list = mock(List.class);

        // If you are using argument matchers, all arguments
        // have to be provided by matchers.
        when(list.get(anyInt())).thenThrow(new RuntimeException("Foo Bar!"));

        //When & Then
        assertThrows(RuntimeException.class,
            () -> {
                // When
                list.get(anyInt());},
            () -> "Should have throw an RuntimeException");
    }

}

package br.com.diegoleandro.automatedtestes.mockito.business;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.mockito.Mockito.*;

public class ListTest {

    @Test
    void testMockingList_When_sizeIsCalled_ShouldReturnMultipleValues() {
        // Given
        List<?> list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20).thenReturn(30);

        //When & Then
        assertEquals(10, list.size());
        assertEquals(20, list.size());
        assertEquals(30, list.size());

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

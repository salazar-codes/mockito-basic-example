package com.salazarcodes.business;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;

public class ListTest {

    @Test
    public void letsMockListSizeMethod() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);
        assertEquals(2, listMock.size());
    }

    @Test
    public void letsMockListSizeMethod_ReturnMultipleValues() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);

        assertEquals(2, listMock.size());
        assertEquals(3, listMock.size());
    }

    @Test
    public void letsMockListGet() {
        List listMock = mock(List.class);
        //when(listMock.get(0)).thenReturn("salazarcodes");
        //Argument Matcher
        when(listMock.get(anyInt())).thenReturn("salazarcodes");

        assertEquals("salazarcodes", listMock.get(0));
        assertEquals("salazarcodes", listMock.get(1)); // w/ argumentMatcher
        //assertEquals(null, listMock.get(1));
    }

    @Test
    public void letsMockList_throwAnException() {
        List listMock = mock(List.class);
        // Argument Matcher
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Somehting"));

        assertThrows(RuntimeException.class, ()-> listMock.get(0));
    }

    @Test
    public void letsMockList_mixinUp() {
        List listMock = mock(List.class);
        // Argument Matcher
        when(listMock.subList(anyInt(),5)).thenThrow(new RuntimeException("Somehting"));

        assertThrows(RuntimeException.class, ()-> listMock.get(0));
    }

    @Test
    public void letsMockListGet_usingBDD() {
        //Given
        List<String> listMock = mock(List.class);
        given(listMock.get(anyInt())).willReturn("salazarcodes");

        //When
        String firstElement = listMock.get(0);

        //Then
        assertEquals("salazarcodes",firstElement);
        //assertThat( firstElement,is("salazarcodes"));
    }
}

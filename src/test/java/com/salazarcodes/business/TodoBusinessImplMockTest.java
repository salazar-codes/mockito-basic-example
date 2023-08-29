package com.salazarcodes.business;

import com.salazarcodes.data.api.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
public class TodoBusinessImplMockTest {

    @Mock
    TodoService todoServiceMock;

    @Test
    public void testRetrieveTodosRelatedToSpring_usingAMock() {
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring","Learn to Dance");
        when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2,filteredTodos.size());
    }

    @Test
    public void testRetrieveTodosRelatedToSpring_usingBDD() {

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring","Learn to Dance");
        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        // When
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

        // Then
        //assertThat(2,filteredTodos.size(), is(2));
        assertEquals(2,filteredTodos.size());
    }

    @Test
    public void testDeleteTodosRelatedToSpring_usingBDD() {
        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring","Learn to Dance");
        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        // When
        todoBusinessImpl.deleteTodosRelatedToSpring("Dummy");

        // Then
        verify(todoServiceMock).deleteTodos("Learn to Dance");
        then(todoServiceMock).should().deleteTodos("Learn to Dance");

        //verify(todoServiceMock, atLeastOnce()).deleteTodos("Learn to Dance");
        //verify(todoServiceMock, atLeast(5)).deleteTodos("Learn to Dance");
        //verify(todoServiceMock, times(1)).deleteTodos("Learn to Dance");

        verify(todoServiceMock, never()).deleteTodos("Learn Spring");
        then(todoServiceMock).should(never()).deleteTodos("Learn Spring");
    }

    @Test
    public void testDeleteTodosRelatedToSpring_usingBDD_argumentCapture() {
        // Declare Argument Captor
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        // Define Argument captor

        // Given
        TodoService todoServiceMock = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn to Rock and Roll", "Learn Spring","Learn to Dance");
        given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

        // When
        todoBusinessImpl.deleteTodosRelatedToSpring("Dummy");

        // Then
        //then(todoServiceMock).should(atLeastOnce()).deleteTodos(stringArgumentCaptor.capture());
        then(todoServiceMock).should(times(2)).deleteTodos(stringArgumentCaptor.capture());
        //assertThat(stringArgumentCaptor.getValue(), is("Learn to Dance"));
        assertThat(stringArgumentCaptor.getAllValues().size(), is(2));
    }
}

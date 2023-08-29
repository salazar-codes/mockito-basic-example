package com.salazarcodes.data.api;

import java.util.List;

// External Service - Lets say this comes from WunderList

/**
 * For testing:
 * 1. Create TodoServiceStub
 * 2. Test TodoBusinessImpl using TodoServiceStub
 */
public interface TodoService {
    public List<String> retrieveTodos(String user);

    public void deleteTodos(String todo);
}
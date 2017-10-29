package com.onlinelibrary.templates;

import com.onlinelibrary.utils.functions.Action;

import java.util.function.Function;

public interface DataAccessTemplate {

    <T> T findData(Function<T, T> operation);

    void executeOperation(Action operation);
}

package com.onlinelibrary.templates;

import com.onlinelibrary.utils.JpaUtil;
import com.onlinelibrary.utils.functions.Action;

import java.util.function.Function;

public class JpaTemplate implements DataAccessTemplate {

    @Override
    public <T> T findData(Function<T, T> operation) {
        T t = null;
        try {
            JpaUtil.beginTransaction();
            t = operation.apply(t);
            JpaUtil.commitTransaction();
        } catch (Exception e) {
            JpaUtil.rollbackTransaction();
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void executeOperation(Action operation) {
        try {
            JpaUtil.beginTransaction();
            operation.action();
            JpaUtil.commitTransaction();
        } catch (Exception e) {
            JpaUtil.rollbackTransaction();
            e.printStackTrace();
        }
    }
}

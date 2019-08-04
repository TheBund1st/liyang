package com.thebund1st.liyang.domain.exception;

public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException(Class<?> entityClass, Object id) {
        super(String.format("Cannot find %s by identifier %s", entityClass.getSimpleName(), id.toString()));
    }
}

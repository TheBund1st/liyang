package com.thebund1st.liyang.domain.exception;

import org.apache.tomcat.util.buf.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException(Class<?> entityClass, Object id) {
        super(String.format("Cannot find %s by identifier %s", entityClass.getSimpleName(), id.toString()));
    }

    public NoSuchEntityException(Class<?> entityClass, Object... id) {
        super(String.format("Cannot find %s by identifier %s", entityClass.getSimpleName(),
                StringUtils.join(Arrays.stream(id).map(Object::toString).collect(Collectors.toList()))));
    }
}

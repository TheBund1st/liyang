package com.thebund1st.liyang.adapter.http.mvc.method;

import com.thebund1st.liyang.time.Clock;
import com.thebund1st.liyang.time.NowSetter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;
import java.util.Arrays;

@RequiredArgsConstructor
public class NowSetterRequestBodyAdvice extends RequestBodyAdviceAdapter {

    private final Clock clock;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return Arrays.stream(((Class) targetType).getInterfaces()).anyMatch(i -> i.isAssignableFrom(NowSetter.class));
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (body instanceof NowSetter) {
            ((NowSetter) body).setNow(clock.now().toEpochSecond());
        }
        return body;
    }
}

package com.thebund1st.liyang.adapter.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RequiredArgsConstructor
@RestController
public class DelayJobRestController {

    @PostMapping("#{liyangRestEndpointProperties.createDelayJobPath}")
    @ResponseStatus(ACCEPTED)
    public void handle() {
    }

}

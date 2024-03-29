package com.thebund1st.liyang.adapter.http.rest;

import com.thebund1st.liyang.adapter.http.rest.assembler.DelayJobResourceAssembler;
import com.thebund1st.liyang.adapter.http.rest.resource.DelayJobResource;
import com.thebund1st.liyang.application.CancelDelayJobCommandHandler;
import com.thebund1st.liyang.application.CreateDelayJobCommandHandler;
import com.thebund1st.liyang.application.command.CancelDelayJobCommand;
import com.thebund1st.liyang.application.command.CreateDelayJobCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
public class DelayJobRestController {

    private final CreateDelayJobCommandHandler createDelayJobCommandHandler;

    private final CancelDelayJobCommandHandler cancelDelayJobCommandHandler;

    private final DelayJobResourceAssembler delayJobResourceAssembler;

    @PostMapping("#{liyangRestEndpointProperties.createDelayJobPath}")
    @ResponseStatus(ACCEPTED)
    public DelayJobResource handle(@RequestBody CreateDelayJobCommand command) {
        return delayJobResourceAssembler.toResource(createDelayJobCommandHandler.handle(command));
    }

    @DeleteMapping("#{liyangRestEndpointProperties.cancelDelayJobPath}")
    @ResponseStatus(OK)
    public DelayJobResource handle(@RequestBody CancelDelayJobCommand command) {
        return delayJobResourceAssembler.toResource(cancelDelayJobCommandHandler.handle(command));
    }

}

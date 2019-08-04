package com.thebund1st.liyang.application;

import com.thebund1st.liyang.application.command.TriggerDelayJobCommand;

public interface TriggerDelayJobCommandHandler {
    void handle(TriggerDelayJobCommand command);
}

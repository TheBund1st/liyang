package com.thebund1st.liyang.application;

import com.thebund1st.liyang.application.command.CreateDelayJobCommand;
import com.thebund1st.liyang.domain.model.DelayJob;

public interface CreateDelayJobCommandHandler {

    DelayJob handle(CreateDelayJobCommand command);
}

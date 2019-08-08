package com.thebund1st.liyang.application;

import com.thebund1st.liyang.application.command.CancelDelayJobCommand;
import com.thebund1st.liyang.domain.model.DelayJob;

public interface CancelDelayJobCommandHandler {

    DelayJob handle(CancelDelayJobCommand command);
}

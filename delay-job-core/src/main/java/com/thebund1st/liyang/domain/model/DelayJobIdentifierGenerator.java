package com.thebund1st.liyang.domain.model;

public interface DelayJobIdentifierGenerator {
    DelayJob.Identifier next();
}

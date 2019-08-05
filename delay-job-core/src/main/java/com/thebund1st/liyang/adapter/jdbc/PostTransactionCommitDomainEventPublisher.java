package com.thebund1st.liyang.adapter.jdbc;

import com.thebund1st.liyang.domain.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionSynchronization;

import static org.springframework.transaction.support.TransactionSynchronizationManager.registerSynchronization;

@RequiredArgsConstructor
public class PostTransactionCommitDomainEventPublisher implements DomainEventPublisher {

    private final DomainEventPublisher delegate;

    @Override
    public void publish(Object event) {
        registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                delegate.publish(event);
            }
        });
    }
}

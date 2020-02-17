package org.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.LoggerFactory;

public class BasicServiceTaskDelegate implements JavaDelegate {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(BasicServiceTaskDelegate.class);

    public void execute(DelegateExecution execution) {
        throw new RuntimeException("Exception thrown in basic task!");
    }
}

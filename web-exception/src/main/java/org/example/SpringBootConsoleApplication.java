package org.example;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableProcessApplication("Camunda simple console application")
@SpringBootApplication
public class SpringBootConsoleApplication
        implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(SpringBootConsoleApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(SpringBootConsoleApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public void run(String... args) {
        runSync();
        runAsync();
    }

    private void runSync(){
        try {
            LOG.info("EXECUTING : command line runner");
            String processInstanceId = runtimeService
                    .startProcessInstanceByKey("basic-process-exception")
                    .getProcessInstanceId();
            LOG.info("started instance: {}", processInstanceId);
            runtimeService.signalEventReceived("test_signal");
        }
        catch (RuntimeException e) {
            LOG.warn("exception caught: {}", e.getMessage());
        }
    }

    private void runAsync(){
        try {
            LOG.info("EXECUTING : command line runner");
            String processInstanceId = runtimeService
                    .startProcessInstanceByKey("basic-process-exception-async")
                    .getProcessInstanceId();
            LOG.info("started instance: {}", processInstanceId);
            runtimeService.signalEventReceived("test_signal");
        }
        catch (RuntimeException e) {
            LOG.warn("exception caught: {}", e.getMessage());
        }
    }

}

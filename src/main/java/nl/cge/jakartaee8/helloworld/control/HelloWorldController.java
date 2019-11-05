package nl.cge.jakartaee8.helloworld.control;

import nl.cge.jakartaee8.helloworld.entity.HelloWorld;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import java.util.Properties;

public class HelloWorldController {

    public HelloWorld execute() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Long executionId = jobOperator.start("myBatchJob", new Properties());

        return new HelloWorld("Job startied, id = " + executionId);


    }
}

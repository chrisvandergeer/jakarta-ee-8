package nl.cge.jakartaee8.batch.boundary;

import nl.cge.jakartaee8.batch.entity.BatchjobStartResult;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Properties;

@Produces("application/json")
@Path("mybatchjob")
@ApplicationScoped
public class MyBatchjobStarter {

    @Path("start")
    @GET
    public BatchjobStartResult helloWorld() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Long executionId = jobOperator.start("myBatchJob", new Properties());
        return new BatchjobStartResult(executionId);
    }
}

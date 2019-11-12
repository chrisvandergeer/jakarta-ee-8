package nl.cge.jakartaee8.batch.boundary;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Properties;

@Stateless
@Produces("application/json")
@Path("mybatchjob")
public class MyBatchjobStarter {

    @PersistenceContext(name = "my-pu")
    private EntityManager em;

    @Path("start")
    @GET
    public Response startBatch() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        long batchjobId = jobOperator.start("myBatchJob", new Properties());
        return Response.ok(String.format("Job started with id %s", batchjobId)).build();
    }

    @Path("prepare")
    @GET
    public Response prepareBatch() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        jobOperator.start("prepareBatch", new Properties());
        return Response.ok("Batch gestart").build();
    }
}

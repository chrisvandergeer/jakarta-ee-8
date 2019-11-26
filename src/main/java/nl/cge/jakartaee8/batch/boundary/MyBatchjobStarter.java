package nl.cge.jakartaee8.batch.boundary;

import com.sun.tools.javac.comp.Todo;
import nl.cge.jakartaee8.batch.control.jobstatus.JobStatusController;
import nl.cge.jakartaee8.batch.entity.TodoDto;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
@Produces("application/json")
@Path("mybatchjob")
public class MyBatchjobStarter {

    @Inject
    private JobStatusController jobStatusController;

    @PersistenceContext(name = "my-pu")
    private EntityManager em;

    /**
     * Geeft de status van alle batchjobs terug.
     */
    @Path("status")
    @GET
    public Response status() {
        return Response.ok(jobStatusController.getStatussen()).build();
    }

    /**
     * Start de batchjob.
     */
    @Path("start")
    @GET
    public Response startBatch() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        long batchjobId = jobOperator.start("myBatchJob", new Properties());
        return Response.ok(jobStatusController.getStatussen()).build();
    }

    @Path("todo")
    @GET
    public Response showTodo() {
        Long totalCount = em.createQuery("select count(e) from MyEntity e", Long.class)
                .getSingleResult();
        Long todoCount = em.createQuery("select count(e) from MyEntity e where e.calculatedUuid is null", Long.class)
                .getSingleResult();
        TodoDto todoDto = new TodoDto(totalCount, todoCount);
        return Response.ok(todoDto).build();

    }
}

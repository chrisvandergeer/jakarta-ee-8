package nl.cge.jakartaee8.helloworld.boundary;

import nl.cge.jakartaee8.helloworld.control.HelloWorldController;
import nl.cge.jakartaee8.helloworld.entity.HelloWorld;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Produces("application/json")
@Path("helloworld")
@ApplicationScoped
public class HelloWorldResource {

    @Inject
    private HelloWorldController controller;

    @GET
    public HelloWorld helloWorld() {
        return controller.execute();
    }
}

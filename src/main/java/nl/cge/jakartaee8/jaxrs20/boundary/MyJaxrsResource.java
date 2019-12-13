package nl.cge.jakartaee8.jaxrs20.boundary;

import nl.cge.jakartaee8.jaxrs20.boundary.entity.MyJaxrsDto;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Stateless
@Consumes("application/json")
@Produces("application/json")
@Path("myjaxrs")
public class MyJaxrsResource {

    @POST
    public Response doPost(MyJaxrsDto inputDto) {
        BigDecimal newNumber = inputDto.getNummer().multiply(BigDecimal.valueOf(2));
        newNumber = newNumber.setScale(2, RoundingMode.CEILING);
        MyJaxrsDto outputDto = new MyJaxrsDto(new Date(), newNumber);
        return Response.ok(outputDto).build();
    }

}

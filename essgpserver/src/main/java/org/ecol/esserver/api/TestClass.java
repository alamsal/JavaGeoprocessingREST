package org.ecol.esserver.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class TestClass {

    @GET
    @Path("/value")
    @Produces(MediaType.APPLICATION_JSON)
    public String GetTestValue(){
        return "Hi from Test";
    }

}

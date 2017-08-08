package org.ecol.esserver.server;


import org.ecol.esserver.api.TestClass;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ConfigRestEndPoints {
    public ResourceConfig GetRestEndPointsResourceConfig(){
        ResourceConfig resourceConfig = new ResourceConfig();

        resourceConfig.register(TestClass.class);

        resourceConfig.register(JacksonFeature.class);

        return resourceConfig;
    }
}

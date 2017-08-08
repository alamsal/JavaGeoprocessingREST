package org.ecol.esserver.server;


import org.glassfish.jersey.server.ResourceConfig;

public class ConfigJaxRsPackage extends ResourceConfig {

    public ConfigJaxRsPackage(){
        packages("org.ecol.esserver");
    }
}

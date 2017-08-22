package org.ecol.esserver.api;

import org.ecol.esserver.DataAccess.Connections;
import org.ecol.esserver.DataAccess.DataReader;
import org.ecol.esserver.DataAccess.IConnections;
import org.wololo.geojson.Feature;
import org.wololo.geojson.GeoJSONFactory;
import org.wololo.jts2geojson.GeoJSONReader;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.IOException;

@Path("/stat")
public class Statistics {

    @GET
    @Path("/zonalSummary")
    @Produces(MediaType.TEXT_PLAIN)

    public String GetZonalSummary() throws Exception {
        String geoJsonGeometry = "{\"type\":\"Feature\",\"properties\":{},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[-117.05932617187501,34.11180455556901],[-117.05932617187501,34.250405862125],[-116.79565429687501,34.250405862125],[-116.79565429687501,34.11180455556901],[-117.05932617187501,34.11180455556901]]]}}";

        Feature feature = (Feature) GeoJSONFactory.create(geoJsonGeometry);
        GeoJSONReader reader = new GeoJSONReader();
        com.vividsolutions.jts.geom.Geometry geom = reader.read(feature.getGeometry());

        IConnections connections = new Connections();
        connections.SetupConnection();

        DataReader dataReader = new DataReader(connections);
        dataReader.GetZonalSummary();




        return geom.toString();
    }



}



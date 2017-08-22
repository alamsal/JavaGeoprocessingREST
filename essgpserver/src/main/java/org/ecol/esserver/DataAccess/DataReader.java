package org.ecol.esserver.DataAccess;

import java.sql.ResultSet;
import java.sql.Statement;

public class DataReader {

    private IConnections pgConnections;

    public DataReader(IConnections connections){
            pgConnections = connections;
    }

    public String GetZonalSummary(String rasterTable, String geometryAsGeoJson) throws Exception{
        return null;
    }

    public String GetZonalSummary() throws Exception{
        Statement sqlStatement = pgConnections.SetupConnection().createStatement();
        String pgSqlQuery = "Select (ST_Area(ST_GeomFromText\n" +
                "('POLYGON ((-117.16918945312501 34.27083595165,-117.1307373046875 34.166363384737892,-117.00988769531251 34.161818161230386,-116.93298339843751 34.239053668516412,-116.98516845703126 34.27083595165,-117.16918945312501 34.27083595165))',4326)))";

        ResultSet resultSet = sqlStatement.executeQuery(pgSqlQuery);

        return null;
    }

}

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

        String geomString = "POLYGON ((-117.16918945312501 34.27083595165,-117.1307373046875 34.166363384737892,-117.00988769531251 34.161818161230386,-116.93298339843751 34.239053668516412,-116.98516845703126 34.27083595165,-117.16918945312501 34.27083595165))";

        Statement sqlStatement = pgConnections.SetupConnection().createStatement();
       // String pgSqlQuery = "Select (ST_Area(ST_GeomFromText('"+geomString+"',4326)))";

        /*
        String pgSqlQuery = "SELECT  (ST_SummaryStats (ST_Clip(ST_Union(rast), ST_GeomFromText('POLYGON ((-117.16918945312501 34.27083595165,-117.1307373046875 34.166363384737892,-117.00988769531251 34.161818161230386,-116.93298339843751 34.239053668516412,-116.98516845703126 34.27083595165,-117.16918945312501 34.27083595165))',4326),true))).* "+
                "FROM public.biodiv_ssolnw_wgs84 "+
        "WHERE ST_Intersects "+
        "(rast,ST_GeomFromText "+
                "('POLYGON ((-117.16918945312501 34.27083595165,-117.1307373046875 34.166363384737892,-117.00988769531251 34.161818161230386,-116.93298339843751 34.239053668516412,-116.98516845703126 34.27083595165,-117.16918945312501 34.27083595165))',4326))";
*/

        String pgSqlQuery =  "SELECT row_to_json(t) from ( SELECT  (ST_SummaryStats (ST_Clip(ST_Union(rast), ST_GeomFromText('POLYGON ((-117.16918945312501 34.27083595165,-117.1307373046875 34.166363384737892,-117.00988769531251 34.161818161230386,-116.93298339843751 34.239053668516412,-116.98516845703126 34.27083595165,-117.16918945312501 34.27083595165))',4326),true))).*,"
                +" ST_Area(ST_GeomFromText('POLYGON ((-117.16918945312501 34.27083595165,-117.1307373046875 34.166363384737892,-117.00988769531251 34.161818161230386,-116.93298339843751 34.239053668516412,-116.98516845703126 34.27083595165,-117.16918945312501 34.27083595165))',4326))"
                +" FROM public.biodiv_ssolnw_wgs84"
        +" WHERE ST_Intersects"
        +" (rast,ST_GeomFromText"
                +" ('POLYGON ((-117.16918945312501 34.27083595165,-117.1307373046875 34.166363384737892,-117.00988769531251 34.161818161230386,-116.93298339843751 34.239053668516412,-116.98516845703126 34.27083595165,-117.16918945312501 34.27083595165))',4326))"
+" )t";

        ResultSet resultSet = sqlStatement.executeQuery(pgSqlQuery);
        String zonalSummary = null;
        if (resultSet.next()){
            zonalSummary = resultSet.getString(1);
        }


        return zonalSummary;
    }



}

package org.ecol.esserver.DataAccess;

import java.sql.*;
import java.util.Properties;

public class Connections implements IConnections {

    private static final String SERVER_URL = "jdbc:postgresql://localhost:15432/ecolservicedb";
    private static final String USER="postgres";
    private static final String PASSWORD = "postgres";

    public Connection SetupConnection() throws Exception{
        LoadDriver();

        Properties serverProperties = new Properties();
        serverProperties.setProperty("user",USER);
        serverProperties.setProperty("password",PASSWORD);

        Connection pgSqlConnection = DriverManager.getConnection(SERVER_URL,serverProperties);
        System.out.println(pgSqlConnection.getMetaData());
        return pgSqlConnection;
    }

    private void LoadDriver(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR - postgreql driver failed to load "+ e.toString());
        }
    }
}

package org.ecol.esserver.DataAccess;

import java.sql.Connection;

public interface IConnections {
    Connection SetupConnection() throws Exception;
}

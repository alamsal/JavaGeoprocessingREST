package ecol.api.esserver.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import javax.servlet.DispatcherType;
import java.util.EnumSet;


public class ServerStarter {

    private static final int SERVER_PORT = 8680;

    public static void main(String[] args) throws  Exception{

        ResourceConfig resourceConfig = new  ConfigRestEndPoints().GetRestEndPointsResourceConfig();
        Server server = new Server(SERVER_PORT);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(resourceConfig));
        servletContextHandler.addServlet(servletHolder,"/rest");

        // Add the filter, and then use the provided FilterHolder to configure it
        FilterHolder cors = servletContextHandler.addFilter(CrossOriginFilter.class,"/*", EnumSet.of(DispatcherType.REQUEST));
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD,PUT,DELETE");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");

        servletContextHandler.addFilter(cors,"*", EnumSet.of(DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.INCLUDE));

        server.setHandler(servletContextHandler);

        server.join();
        server.start();
    }
}

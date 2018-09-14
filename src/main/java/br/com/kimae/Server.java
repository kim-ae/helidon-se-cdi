package br.com.kimae;

import java.io.IOException;
import java.util.logging.LogManager;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.interceptor.Interceptor;

import io.helidon.config.Config;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.json.JsonSupport;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class Server {

    @Inject
    private GreetService greetService;

    @PostConstruct
    public void post(){
        log.info("try");
    }

    public void init(@Observes @Priority(Interceptor.Priority.APPLICATION - 100)
    @Initialized(ApplicationScoped.class) Object init) throws Exception{
        log.info("Iniciando servidor, com CDI");
        startServer();
    }

    /**
     * Start the server.
     * @return the created {@link WebServer} instance
     * @throws IOException if there are problems reading logging properties
     */
    private  WebServer startServer() throws IOException {

        // load logging configuration
        LogManager.getLogManager().readConfiguration(
            Main.class.getResourceAsStream("/logging.properties"));

        // By default this will pick up application.yaml from the classpath
        Config config = Config.create();

        // Get webserver config from the "server" section of application.yaml
        ServerConfiguration serverConfig =
            ServerConfiguration.fromConfig(config.get("server"));

        WebServer server = WebServer.create(serverConfig, createRouting());

        // Start the server and print some info.
        server.start().thenAccept(ws -> {
            System.out.println(
                "WEB server is up! http://localhost:" + ws.port());
        });

        // Server threads are not demon. NO need to block. Just react.
        server.whenShutdown().thenRun(()
            -> System.out.println("WEB server is DOWN. Good bye!"));

        return server;
    }

    /**
     * Creates new {@link Routing}.
     *
     * @return the new instance
     */
    private  Routing createRouting() {
        return Routing.builder()
            .register(JsonSupport.get())
            .register("/greet", greetService)
            .build();
    }
}

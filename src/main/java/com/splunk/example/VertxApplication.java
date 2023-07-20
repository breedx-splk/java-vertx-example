package com.splunk.example;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

import java.util.concurrent.TimeUnit;

public class VertxApplication {

    public static final int PORT = 9080;

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);
        router
                .get("/hello")
                .respond(ctx -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                        return Future.succeededFuture(new JsonObject().put("hello", "from vertx-web"));
                    } catch (InterruptedException e) {
                        return Future.failedFuture("interrupted a bunch");
                    }
                });

        server.requestHandler(router).listen(PORT);
        System.out.println("Server is running on port " + PORT);
    }
}

package com.splunk.example;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.tracing.opentelemetry.OpenTelemetryOptions;

import java.util.concurrent.TimeUnit;

public class VertxApplication {

    public static final int PORT = 9080;

    public static void main(String[] args) {
        OpenTelemetry otel = GlobalOpenTelemetry.get();
        VertxOptions options = new VertxOptions()
                .setTracingOptions(new OpenTelemetryOptions(otel));
        Vertx vertx = Vertx.vertx(options);
        HttpServer server = vertx.createHttpServer();

        Router router = buildRouter(vertx);
        server.requestHandler(router).listen(PORT);
        System.out.println("Server is running on port " + PORT);
    }

    static Router buildRouter(Vertx vertx) {
//        return buildVersion3Router(vertx);
        return buildVersion4Router(vertx);
    }
//
//    static Router buildVersion3Router(Vertx vertx) {
//        Router router = Router.router(vertx);
//        router
//                .get("/hello").handler(ctx -> {
//                    try {
//                        TimeUnit.MILLISECONDS.sleep(500);
//                        String body = new JsonObject().put("hello", "from vertx-web").encode();
//                        ctx.response().setStatusCode(200)
//                                .putHeader("Content-Type", "application/json")
//                                .end(body);
//                    } catch (InterruptedException e) {
//                        ctx.response().setStatusCode(500)
//                                .putHeader("Content-Type", "text/plain")
//                                .end("interrupted a bunch");
//                    }
//                });
//        router.get("/error").handler(new Handler<RoutingContext>() {
//            @Override
//            public void handle(RoutingContext event) {
//                if (1 == 1) {
//                    throw new RuntimeException("boom");
//                }
//            }
//        });
//        return router;
//
//    }

    private static Router buildVersion4Router(Vertx vertx) {
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
        router.get("/error")
                .respond(ctx -> {
                    if (1 == 1) {
                        throw new RuntimeException("boom");
                    }
                    return Future.failedFuture("ouch");
                });
        return router;
    }
}

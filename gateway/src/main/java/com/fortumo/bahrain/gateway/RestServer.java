package com.fortumo.bahrain.gateway;

import com.fortumo.bahrain.gateway.exceptions.ApiException;
import com.fortumo.bahrain.gateway.handlers.ApiHandlers;
import com.fortumo.bahrain.gateway.handlers.CustomHandlers;
import com.fortumo.bahrain.gateway.handlers.Middleware;
import com.fortumo.bahrain.gateway.handlers.RoutingHandlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;

import static com.fortumo.bahrain.gateway.handlers.CustomHandlers.timed;

public class RestServer {

    // {{start:routes}}
    public static final RoutingHandler ROUTES = new RoutingHandler()
            .get("/users", timed("createUser", UserRoutes::createUser))
            .setFallbackHandler(timed("notFound", RoutingHandlers::notFoundHandler))
            ;

    /*
     *  Small wrapper to mimic throwing exceptions. Just add &exception=true
     *  to any route and this will throw an exception. Notice it throws a RuntimeException
     *  not an API exception. This will be handled by the global ExceptionHandler.
     */
    private static final HttpHandler EXCEPTION_THROWER = (HttpServerExchange exchange) -> {
        new UserRequests().exception(exchange);
        ROUTES.handleRequest(exchange);
    };

    public static final HttpHandler ROOT = CustomHandlers.exception(EXCEPTION_THROWER)
            .addExceptionHandler(ApiException.class, ApiHandlers::handleApiException)
            .addExceptionHandler(Throwable.class, ApiHandlers::serverError)
            ;
    // {{end:routes}}

    // {{start:server}}
    public static void main(String[] args) {
        // Once again pull in a bunch of common middleware.
        SimpleServer server = SimpleServer.simpleServer(Middleware.common(ROOT));
        server.start();
    }
    // {{end:server}}

}

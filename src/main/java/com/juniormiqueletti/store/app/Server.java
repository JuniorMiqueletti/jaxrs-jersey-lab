package com.juniormiqueletti.store.app;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Server {

	
    public static void main(String[] args) throws IOException {
    	
    	ResourceConfig config = new ResourceConfig().packages("com.juniormiqueletti.store");
    	
        URI uri = URI.create("http://localhost:8080/");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
        
        System.out.println("Server running");
    }
}
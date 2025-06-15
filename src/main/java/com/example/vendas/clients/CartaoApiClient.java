package com.example.vendas.clients;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "cartao-api")
@Path("/api/cartoes") 
public interface CartaoApiClient {


    @GET
    @Path("/verificar/{numeroCartao}") 
    @Produces(MediaType.APPLICATION_JSON)
    void verificarCartao(@PathParam("numeroCartao") String numeroCartao);

}
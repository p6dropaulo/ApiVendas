package com.example.vendas;

import com.example.vendas.clients.CartaoApiClient;
import com.example.vendas.models.Venda;
import com.example.vendas.models.VendaRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import java.math.BigDecimal;
import java.util.List;

@Path("/vendas")
public class VendaResource {

    @Inject
    VendaRepository vendaRepository;

    @Inject
    @RestClient
    CartaoApiClient cartaoApiClient;

    public static class VendaRequest {
        public String numeroCartao;
        public String produto;
        public BigDecimal valor;
    }

    @POST
    @Transactional
    public Response registrarVenda(VendaRequest request) {
        
        try {
            cartaoApiClient.verificarCartao(request.numeroCartao);
            
        } catch (WebApplicationException e) {
            
            if (e.getResponse().getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Cartão não encontrado ou inválido.")
                               .build();
            }
            
            return Response.serverError()
                           .entity("Erro ao verificar o cartão: " + e.getMessage())
                           .build();
        }

        
        Venda novaVenda = new Venda();
        novaVenda.setNumeroCartao(request.numeroCartao);
        novaVenda.setProduto(request.produto);
        novaVenda.setValor(request.valor);

        vendaRepository.persist(novaVenda);

        return Response.status(Response.Status.CREATED).entity("Venda registrada com sucesso.").build();
    }

    @GET
    public Response listarTodasAsVendas() {
        List<Venda> vendas = vendaRepository.listAll();
        return Response.ok(vendas).build();
    }


}

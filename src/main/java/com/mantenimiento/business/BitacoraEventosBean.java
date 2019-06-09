/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mantenimiento.business;

import com.mantenimiento.dto.BitacoraDTO;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
public class BitacoraEventosBean implements BitacoraEventosLocal {

    private final String END_POINT = "http://localhost:7001/MantenimientoWeb/webresources";
    private Client cliente;
    private WebTarget webTarget;
    private BitacoraDTO bitacoraDTO;
    private List<BitacoraDTO> listaBitacora;
    private Invocation.Builder invocationBuilder;
    private Response response;

    @Override
    public List<BitacoraDTO> obtenerListaEventos() {
        cliente = ClientBuilder.newClient();
        webTarget = cliente.target(END_POINT).path("/bitacora");
        listaBitacora = webTarget.request(MediaType.APPLICATION_XML).get(Response.class)
                .readEntity(new GenericType<List<BitacoraDTO>>() {
                });
        return listaBitacora;
    }

    @Override
    public BitacoraDTO obtenerBitacoraPorId(BigDecimal id) {
        cliente = ClientBuilder.newClient();
        webTarget = cliente.target(END_POINT).path("/bitacora");
        bitacoraDTO = webTarget.path("/" + id.toString()).request(MediaType.APPLICATION_XML).get(BitacoraDTO.class);
        return bitacoraDTO;
    }

    @Override
    public void agregarBitacora(BitacoraDTO bitacoraDTO) {
        bitacoraDTO.setFechaIni(new Date());
        cliente = ClientBuilder.newClient();
        webTarget = cliente.target(END_POINT).path("/bitacora");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
        response = invocationBuilder.post(Entity.entity(bitacoraDTO, MediaType.APPLICATION_XML));
    }

    public BitacoraDTO getBitacoraDTO() {
        return bitacoraDTO;
    }

    public List<BitacoraDTO> getListaBitacora() {
        return listaBitacora;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public WebTarget getWebTarget() {
        return webTarget;
    }

    public void setWebTarget(WebTarget webTarget) {
        this.webTarget = webTarget;
    }

    public Invocation.Builder getInvocationBuilder() {
        return invocationBuilder;
    }

    public void setInvocationBuilder(Invocation.Builder invocationBuilder) {
        this.invocationBuilder = invocationBuilder;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}

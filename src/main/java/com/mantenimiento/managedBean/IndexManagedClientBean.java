/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mantenimiento.managedBean;

import com.mantenimiento.business.BitacoraEventosLocal;
import com.mantenimiento.dto.BitacoraDTO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "indexManagedClientBean")
@ViewScoped
public class IndexManagedClientBean {

    @EJB
    private BitacoraEventosLocal bitacoraEventosBean;
    private List<BitacoraDTO> listaBitacora;
    private BitacoraDTO bitacoraDTO;

    public IndexManagedClientBean() {
    }

    @PostConstruct
    public void inicializar() {
        bitacoraDTO = new BitacoraDTO();
        consultarBitacora();
    }

    public BitacoraDTO getBitacoraDTO() {
        return bitacoraDTO;
    }

    public void setBitacoraDTO(BitacoraDTO bitacoraDTO) {
        this.bitacoraDTO = bitacoraDTO;
    }

    public List<BitacoraDTO> getListaBitacora() {
        return listaBitacora;
    }

    public void setListaBitacora(List<BitacoraDTO> listaBitacora) {
        this.listaBitacora = listaBitacora;
    }

    public void consultarBitacora() {
        listaBitacora = bitacoraEventosBean.obtenerListaEventos();
    }

    public void insertarNovedad() {
        try {
            if (!bitacoraDTO.getObservaciones().equals("")) {
                bitacoraEventosBean.agregarBitacora(bitacoraDTO);
                listaBitacora.add(bitacoraDTO);
                inicializar();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Novedad", "Novedad registrada satisfactoriamente."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Datos incompletos para el registro."));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contacte al administrador del sistema."));
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mantenimiento.business;

import com.mantenimiento.dto.BitacoraDTO;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;

@Local
public interface BitacoraEventosLocal {
    
    public List<BitacoraDTO> obtenerListaEventos ();
    
    public BitacoraDTO obtenerBitacoraPorId(BigDecimal id);
    
    public void agregarBitacora(BitacoraDTO bitacoraDTO);
    
}

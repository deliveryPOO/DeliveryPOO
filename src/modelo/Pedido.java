/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author User
 */
public class Pedido {
    
   private Integer cliente;
   private Integer formaPagmento;
   private Integer entregador;
   private Integer atendente;
   private String obs;

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public Integer getFormaPagmento() {
        return formaPagmento;
    }

    public void setFormaPagmento(Integer formaPagmento) {
        this.formaPagmento = formaPagmento;
    }

    public Integer getEntregador() {
        return entregador;
    }

    public void setEntregador(Integer entregador) {
        this.entregador = entregador;
    }

    public Integer getAtendente() {
        return atendente;
    }

    public void setAtendente(Integer atendente) {
        this.atendente = atendente;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    
   
}

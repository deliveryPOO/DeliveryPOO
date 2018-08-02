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
    
   private Integer numeroPedido;
   private Cliente cliente;
   private List<Produto> pedidos;
   private FormaPagmt formaPagmento;
   private Entregador entregador;
   private Atendente atendente;

    public Integer getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Produto> pedidos) {
        this.pedidos = pedidos;
    }

    public FormaPagmt getFormaPagmento() {
        return formaPagmento;
    }

    public void setFormaPagmento(FormaPagmt formaPagmento) {
        this.formaPagmento = formaPagmento;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }
   
   
   
    public double calcularValorTotal(List<Produto> produtos){
       return 0;
        
        
    }
   
   
    
}

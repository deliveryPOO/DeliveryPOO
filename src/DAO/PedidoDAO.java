/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Pedido;

/**
 *
 * @author vinicius
 */
public class PedidoDAO {
    public static int create(Pedido p) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int idPedido = 0;
        
        try {
            stmt = con.prepareStatement("INSERT INTO pedido (cliente_idcliente, atendente_idatendente, entregador_identregador, "
                                        + "formadepagamento_idformadepagamento, obs) VALUES (?, ?, ?, ?, ?);");
            stmt.setInt(1, p.getCliente());
            stmt.setInt(2, p.getAtendente());
            stmt.setInt(3, p.getEntregador());
            stmt.setInt(4, p.getFormaPagmento());
            stmt.setString(5, p.getObs());
            stmt.executeUpdate();
            
            stmt.close();
            
            stmt = con.prepareStatement("SELECT MAX(idpedido) FROM pedido;");
            rs = stmt.executeQuery();
           
            while(rs.next())
            {
                idPedido = rs.getInt(1);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return idPedido;
    }
}

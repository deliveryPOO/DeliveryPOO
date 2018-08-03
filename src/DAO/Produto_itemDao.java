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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Entregador;
import modelo.Item;

/**
 *
 * @author vinicius
 */
public class Produto_itemDao {
    public static List<Integer> readItens(Integer idProduto) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Integer> idItens = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produto_item WHERE idProduto = ?");
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                idItens.add(rs.getInt("idItem"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntregadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return idItens;
    }
    
    public static void create(Item t) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO item(nome, qtd) VALUES (?,?)");
            stmt.setString(1, t.getNome());
            stmt.setInt(2, t.getQtdEstoque());
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
}

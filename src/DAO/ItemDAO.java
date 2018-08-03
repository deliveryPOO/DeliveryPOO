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
public class ItemDAO {
    
    public static List<Item> read(Integer idProduto) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
     
        List<Item> itens = new ArrayList<>();
        
        try {
            for(Integer p: Produto_itemDao.readItens(idProduto)) {
                stmt = con.prepareStatement("SELECT * FROM item WHERE id = ?");
                stmt.setInt(1, p);
                rs = stmt.executeQuery();
                    
                while(rs.next()) {
                    Item a = new Item();
                    a.setNome(rs.getString("nome"));
                    a.setQtdEstoque(rs.getInt("qtd")); 
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return itens;
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
    
    public static void update(Item t) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("UPDATE item SET nome = ?, qtd= ? WHERE id = ?");
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

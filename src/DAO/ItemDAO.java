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
    
    public static List<Item> read(String key) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
     
        List<Item> itens = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM item WHERE nome = ?;");
            stmt.setString(1,key);
            rs = stmt.executeQuery();
                    
            while(rs.next()) {
                Item a = new Item();
                a.setId(rs.getInt("iditem"));
                a.setNome(rs.getString("nome"));
                a.setQtdEstoque(rs.getInt("qtdEstoque")); 
                a.setValor(rs.getDouble("valor"));
                itens.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return itens;
    }
    
    
    public static List<Item> read() {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
     
        List<Item> itens = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM item;");
            rs = stmt.executeQuery();
                    
            while(rs.next()) {
                Item a = new Item();
                a.setId(rs.getInt("iditem"));
                a.setNome(rs.getString("nome"));
                a.setQtdEstoque(rs.getInt("qtdEstoque")); 
                a.setValor(rs.getDouble("valor"));
                itens.add(a);
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
            stmt = con.prepareStatement("INSERT INTO item (nome, qtdEstoque, valor) VALUES (?,?,?);");
            stmt.setString(1, t.getNome());
            stmt.setInt(2, t.getQtdEstoque());
            stmt.setDouble(3, t.getValor());
            stmt.executeUpdate();
            
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
    
    public static void delete(int idItem) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM item WHERE iditem =?;");
            stmt.setInt(1, idItem);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt);
        }
    }
}

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
import modelo.Item;

/**
 *
 * @author vinicius
 */
public class itemProdutoDAO {
    public static void create(int idProduto, int idItem) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO itemProduto (idProduto, idItem) VALUES (?,?);");
            stmt.setInt(1, idProduto);
            stmt.setInt(2, idItem);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt);
        }
    }
}

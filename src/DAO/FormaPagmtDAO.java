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
import modelo.FormaPagmt;
import modelo.Produto;

/**
 *
 * @author vinicius
 */
public class FormaPagmtDAO {
    public static List<FormaPagmt> read() {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<FormaPagmt> formas = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM formaPagamento;");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                FormaPagmt a = new FormaPagmt();
                a.setId(rs.getInt("idformaPagamento"));
                a.setTipo(rs.getString("tipo"));
                formas.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return formas;
    }
}

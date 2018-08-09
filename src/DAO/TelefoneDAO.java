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
import modelo.Telefone;

/**
 *
 * @author vinicius
 */
public class TelefoneDAO {
    
    public static List<Telefone> read(int idPessoa) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Telefone> telefones = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM telefone WHERE id_pessoa = ?");
            stmt.setInt(1, idPessoa);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Telefone a = new Telefone();
                
                a.setDdd(rs.getByte("ddd"));
                a.setNumero(rs.getInt("numero"));
                telefones.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return telefones;
    }
    
    
    public static void create(Telefone t, String tipoPessoa, int idPessoa) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO telefone(ddd, numero," +
                                        tipoPessoa + ") VALUES (?,?, ?);");
            stmt.setByte(1, t.getDdd());
            stmt.setInt(2, t.getNumero());
            stmt.setInt(3, idPessoa);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
    
    public static void update(Telefone t) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("UPDATE telefone ddd = ?, numero = ? WHERE id = ?");
            stmt.setByte(1, t.getDdd());
            stmt.setInt(2, t.getNumero());
            stmt.setInt(3, t.getId());
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
}

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
import modelo.Veiculo;

/**
 *
 * @author vinicius
 */
public class VeiculoDAO {
    
    public static Veiculo read(int idEntregador) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Veiculo a = new Veiculo();    
        
        try {
            stmt = con.prepareStatement("SELECT * FROM veiculo WHERE id_entregador = ?");
            stmt.setInt(1, idEntregador);
            rs = stmt.executeQuery();
            
            
            while(rs.next()) {

                a.setPlaca(rs.getString("placa"));
                a.setModelo(rs.getString("modelo"));
                a.setCor(rs.getString("cor"));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return a;
    }
    
    
    public static void create(Veiculo t, int idPessoa) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO veiculo(placa, modelo, cor) VALUES (?,?,?)");
            stmt.setString(1, t.getPlaca());
            stmt.setString(2, t.getModelo());
            stmt.setString(3, t.getCor());
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
    
    public static void update(Veiculo t) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("UPDATE veiculo SET placa = ?, modelo = ?, cor = ? WHERE id = ?");
            stmt.setString(1, t.getPlaca());
            stmt.setString(2, t.getModelo());
            stmt.setString(3, t.getCor());
            stmt.setInt(4, t.getId());
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(VeiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
}

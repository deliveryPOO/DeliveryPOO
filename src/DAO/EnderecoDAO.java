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
import modelo.Endereco;

/**
 *
 * @author vinicius
 */
public class EnderecoDAO {
    
    public static List<Endereco> read(String tipoPessoa, int idPessoa) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Endereco> enderecos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM endereco WHERE " + tipoPessoa + " = ?");
            stmt.setInt(1, idPessoa);
            rs = stmt.executeQuery();
            
            
            while(rs.next()) {
                Endereco a = new Endereco();
                a.setRua(rs.getString("rua"));
                a.setNumero(rs.getInt("numero"));
                a.setBairro(rs.getString("bairro"));
                a.setComplemento(rs.getString("complemento"));
                a.setPontoRef(rs.getString("pontoRef"));
                enderecos.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return enderecos;
    }
    
    public static void create(Endereco e, String tipoPessoa, int idPessoa) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO endereco(rua, numero, bairro, complemento, pontoRef,"
                                         + tipoPessoa + " ) VALUES (?,?,?,?,?,?);");
            stmt.setString(1, e.getRua());
            stmt.setInt(2, e.getNumero());
            stmt.setString(3, e.getBairro());
            stmt.setString(4, e.getComplemento());
            stmt.setString(5, e.getPontoRef());
            stmt.setInt(6, idPessoa);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
    
    public static void update(Endereco e) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("UPDATE endereco SET rua ?, numero ?, bairro ?, complemento ?, pontoRef ? WHERE id = ?");
            stmt.setString(1, e.getRua());
            stmt.setInt(2, e.getNumero());
            stmt.setString(3, e.getBairro());
            stmt.setString(4, e.getComplemento());
            stmt.setString(5, e.getPontoRef());
            stmt.setInt(6, e.getId());
            rs = stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
}

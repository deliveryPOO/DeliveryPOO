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
import modelo.Atendente;
import modelo.Endereco;
import modelo.Telefone;

/**
 *
 * @author vinicius
 */
public class AtendenteDAO {
    public static List<Atendente> read(String key) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Atendente> atendentes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM atendente WHERE nome = ?;");
            stmt.setString(1,key);
            rs = stmt.executeQuery();
            
            
            while(rs.next()) {
                Atendente a = new Atendente();
                a.setId(rs.getInt("idatendente"));
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getLong("cpf"));
                a.setUsuario(rs.getString("usuario"));
                a.setSenha(rs.getString("senha"));
                a.setEndereco(EnderecoDAO.read("atendente_idatendente", rs.getInt("idatendente")));
                a.setTelefone(TelefoneDAO.read("atendente_idatendente", rs.getInt("idatendente")));
                atendentes.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtendenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return atendentes;
    }
    public static Atendente read(int key) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Atendente atendente = new Atendente();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM atendente WHERE idatendente = ?;");
            stmt.setInt(1,key);
            rs = stmt.executeQuery();
            
            while(rs.next()) {                
                atendente.setId(rs.getInt("idatendente"));
                atendente.setNome(rs.getString("nome"));
                atendente.setCpf(rs.getLong("cpf"));
                atendente.setUsuario(rs.getString("usuario"));
                atendente.setSenha(rs.getString("senha"));
                atendente.setTelefone(TelefoneDAO.read("atendente_idatendente", rs.getInt("idatendente")));
                atendente.setEndereco(EnderecoDAO.read("atendente_idatendente", rs.getInt("idatendente")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return atendente;
    }
    
    public static void create(Atendente p, Endereco e, Telefone t) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO atendente (nome, cpf, usuario, senha) VALUES (?, ?, ?, ?);");
            stmt.setString(1, p.getNome());
            stmt.setLong(2, p.getCpf());
            stmt.setString(3, p.getUsuario());
            stmt.setString(4, p.getSenha());
            stmt.executeUpdate();
        
            stmt = con.prepareStatement("SELECT MAX(idatendente) FROM atendente;");
            rs = stmt.executeQuery();
            
            int idAtendente = 0;
            while(rs.next()){
                idAtendente=rs.getInt(1);
            }
            EnderecoDAO.create(e,"atendente_idatendente",idAtendente);
            TelefoneDAO.create(t,"atendente_idatendente",idAtendente);
        
        } catch (SQLException ex) {
            Logger.getLogger(AtendenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
    
    public static void update(Atendente p) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("UPDATE atendente set nome = ?, cpf = ? , usuario = ?, senha = ? WHERE id = ?");
            stmt.setString(1, p.getNome());
            stmt.setLong(2, p.getCpf());
            stmt.setString(3, p.getUsuario());
            stmt.setString(4, p.getSenha());
            stmt.setInt(5, p.getId());
            rs = stmt.executeQuery();        
        } catch (SQLException ex) {
            Logger.getLogger(AtendenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
}

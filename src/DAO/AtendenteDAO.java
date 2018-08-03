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
    public List<Atendente> read() {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Atendente> atendentes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM atendente");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Atendente a = new Atendente();
                
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getLong("cpf"));
                a.setUsuario(rs.getString("usuario"));
                a.setSenha(rs.getString("senha"));
                a.setEndereco(EnderecoDAO.read(rs.getInt("id")));
                a.setTelefone(TelefoneDAO.read(rs.getInt("id")));
                atendentes.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtendenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return atendentes;
    }
    
    public void create(Atendente p, Endereco e, Telefone t) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO atendente (nome, cpf, usuario, senha) VALUES (?, ?, ?, ?)");
            stmt.setString(1, p.getNome());
            stmt.setLong(2, p.getCpf());
            stmt.setString(3, p.getUsuario());
            stmt.setString(4, p.getSenha());
            rs = stmt.executeQuery();
        
            stmt = con.prepareStatement("SELECT LAST_INSERT_ID()");
            rs = stmt.executeQuery();
            
            EnderecoDAO.create(e, rs.getInt(1));
            TelefoneDAO.create(t, rs.getInt(1));
        
        } catch (SQLException ex) {
            Logger.getLogger(AtendenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
    
    public void update(Atendente p) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("UPDATE atendente set nome = ?, cpf = ? usuario = ?, senha = ? WHERE id = ?");
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

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
import modelo.Endereco;
import modelo.Telefone;

/**
 *
 * @author vinicius
 */
public class EntregadorDAO {
    public List<Entregador> read() {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Entregador> entregadores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM entregador");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Entregador a = new Entregador();
                
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getLong("cpf"));
                a.setEndereco(EnderecoDAO.read(rs.getInt("id")));
                a.setTelefone(TelefoneDAO.read(rs.getInt("id")));
                a.setVeiculo(VeiculoDAO.read(rs.getInt("id")));
                entregadores.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntregadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return entregadores;
    }
    
    public void create(Entregador p, Endereco e, Telefone t) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO entregador (nome, cpf) VALUES (?, ?)");
            stmt.setString(1, p.getNome());
            stmt.setLong(2, p.getCpf());
            rs = stmt.executeQuery();
        
            stmt = con.prepareStatement("SELECT LAST_INSERT_ID()");
            rs = stmt.executeQuery();
            
            EnderecoDAO.create(e, rs.getInt(1));
            TelefoneDAO.create(t, rs.getInt(1));
        
        } catch (SQLException ex) {
            Logger.getLogger(EntregadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
    
    public void update(Entregador p) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("UPDATE entregador set nome = ?, cpf = ? WHERE id = ?");
            stmt.setString(1, p.getNome());
            stmt.setLong(2, p.getCpf());
            stmt.setInt(5, p.getId());
            rs = stmt.executeQuery();        
        } catch (SQLException ex) {
            Logger.getLogger(EntregadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
}

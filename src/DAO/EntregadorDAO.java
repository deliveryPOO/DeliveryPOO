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
    public static List<Entregador> read(String key) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Entregador> entregadores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM entregador WHERE nome = ?;");
            stmt.setString(1,key);
            rs = stmt.executeQuery();
            
            
            while(rs.next()) {
                Entregador a = new Entregador();
                a.setId(rs.getInt("identregador"));
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getLong("cpf"));
                a.setEndereco(EnderecoDAO.read("entregador_identregador", rs.getInt("identregador")));
                a.setTelefone(TelefoneDAO.read("entregador_identregador", rs.getInt("identregador")));
                entregadores.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntregadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return entregadores;
    }
    
    public static List<Entregador> read() {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Entregador> entregadores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM entregador;");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Entregador entregador = new Entregador();
                entregador.setId(rs.getInt("identregador"));
                entregador.setNome(rs.getString("nome"));
                entregador.setCpf(rs.getLong("cpf"));
                entregadores.add(entregador);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return entregadores;
    }
    
    public static void create(Entregador p, Endereco e, Telefone t) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO entregador (nome, cpf) VALUES (?, ?);");
            stmt.setString(1, p.getNome());
            stmt.setLong(2, p.getCpf());
            stmt.executeUpdate();
        
            stmt = con.prepareStatement("SELECT MAX(identregador) FROM entregador;");
            rs = stmt.executeQuery();
            
            int idEntregador = 0;
            while(rs.next()){
                idEntregador=rs.getInt(1);
            }
            EnderecoDAO.create(e,"entregador_identregador",idEntregador);
            TelefoneDAO.create(t,"entregador_identregador",idEntregador);
        
        } catch (SQLException ex) {
            Logger.getLogger(EntregadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
    
    public static void update(Entregador p) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("UPDATE entregador set nome = ?, cpf = ? WHERE id = ?;");
            stmt.setString(1, p.getNome());
            stmt.setLong(2, p.getCpf());
            stmt.setInt(3, p.getId());
            rs = stmt.executeQuery();        
        } catch (SQLException ex) {
            Logger.getLogger(EntregadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
}

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
import modelo.Administrador;
import modelo.Endereco;
import modelo.Telefone;

/**
 *
 * @author vinicius
 */
public class AdministradorDao {
    public static List<Administrador> read(String key) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Administrador> administradores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM administrador WHERE nome = ?;");
            stmt.setString(1,key);
            rs = stmt.executeQuery();
            
            
            while(rs.next()) {
                Administrador a = new Administrador();
                a.setId(rs.getInt("idadministrador"));
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getLong("cpf"));
                a.setUsuario(rs.getString("usuario"));
                a.setSenha(rs.getString("senha"));
                a.setEndereco(EnderecoDAO.read("administrador_idadministrador", rs.getInt("idadministrador")));
                a.setTelefone(TelefoneDAO.read("administrador_idadministrador", rs.getInt("idadministrador")));
                administradores.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return administradores;
    }
    public static Administrador read(int key) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Administrador administrador = new Administrador();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM administrador WHERE idadministrador = ?;");
            stmt.setInt(1,key);
            rs = stmt.executeQuery();
            
            while(rs.next()) {                
                administrador.setId(rs.getInt("idadministrador"));
                administrador.setNome(rs.getString("nome"));
                administrador.setCpf(rs.getLong("cpf"));
                administrador.setUsuario(rs.getString("usuario"));
                administrador.setSenha(rs.getString("senha"));
                administrador.setTelefone(TelefoneDAO.read("administrador_idadministrador", rs.getInt("idadministrador")));
                administrador.setEndereco(EnderecoDAO.read("administrador_idadministrador", rs.getInt("idadministrador")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return administrador;
    }
    
    public static void create(Administrador p, Endereco e, Telefone t) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO administrador (nome, cpf, usuario, senha) VALUES (?, ?, ?, ?);");
            stmt.setString(1, p.getNome());
            stmt.setLong(2, p.getCpf());
            stmt.setString(3, p.getUsuario());
            stmt.setString(4, p.getSenha());
            stmt.executeUpdate();
        
            stmt = con.prepareStatement("SELECT MAX(idadministrador) FROM administrador;");
            rs = stmt.executeQuery();
            
            int idAdministrador = 0;
            while(rs.next()){
                idAdministrador=rs.getInt(1);
            }
            EnderecoDAO.create(e,"administrador_idadministrador",idAdministrador);
            TelefoneDAO.create(t,"administrador_idadministrador",idAdministrador);
        
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
    
    public static void update(Administrador p) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("UPDATE administrador set nome = ?, cpf = ? , usuario = ?, senha = ? WHERE id = ?");
            stmt.setString(1, p.getNome());
            stmt.setLong(2, p.getCpf());
            stmt.setString(3, p.getUsuario());
            stmt.setString(4, p.getSenha());
            stmt.setInt(5, p.getId());
            rs = stmt.executeQuery();        
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
}
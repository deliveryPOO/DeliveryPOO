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
import modelo.Cliente;
import modelo.Endereco;
import modelo.Telefone;

/**
 *
 * @author vinicius
 */
public class ClienteDAO {
    public static List<Cliente> read(String key) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente INNER JOIN telefone ON cliente.idcliente ="
                                        + "telefone.cliente_idcliente WHERE nome = ?;");
            stmt.setString(1,key);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Cliente a = new Cliente();
                
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getLong("cpf"));
                a.setEndereco(EnderecoDAO.read(rs.getInt("id")));
                a.setTelefone(TelefoneDAO.read(rs.getInt("id")));
                clientes.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return clientes;
    }
    
    public static void create(Cliente p, Endereco e, Telefone t) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO cliente (nome, cpf) VALUES (?, ?);");
            stmt.setString(1, p.getNome());
            stmt.setLong(2, p.getCpf());
            stmt.executeUpdate();
        
            stmt.close();
            
            stmt = con.prepareStatement("SELECT MAX(idcliente) FROM cliente;");
            rs = stmt.executeQuery();
            
            int idPessoa = 0;
            while(rs.next())
            {
                idPessoa = rs.getInt(1);
            }
            
            EnderecoDAO.create(e, "cliente_idcliente", idPessoa);
            TelefoneDAO.create(t, "cliente_idcliente", idPessoa);
        
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
    
    public void update(Cliente p) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("UPDATE cliente set nome = ?, cpf = ? WHERE id = ?");
            stmt.setString(1, p.getNome());
            stmt.setLong(2, p.getCpf());
            stmt.setInt(5, p.getId());
            rs = stmt.executeQuery();        
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
    }
}

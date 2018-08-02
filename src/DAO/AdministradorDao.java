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
import modelo.Pessoa;

/**
 *
 * @author vinicius
 */
public class AdministradorDao {
    public List<Administrador> buscar() {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Administrador> administradores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM administrador");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                Administrador a = new Administrador();
                List<Endereco> enderecos = new ArrayList<>();
                
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getLong("cpf"));
                a.setUsuario(rs.getString("usuario"));
                a.setSenha(rs.getString("senha"));
                a.setEndereco()
                administradores.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TurmaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return turmas;
    }
}

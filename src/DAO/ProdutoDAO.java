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
import modelo.Produto;
import modelo.Endereco;
import modelo.Item;
import modelo.Telefone;

/**
 *
 * @author vinicius
 */
public class ProdutoDAO {
//    public List<Produto> read() {
//        Connection con;
//        con = Conexao.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        
//        List<Produto> produtos = new ArrayList<>();
//        
//        try {
//            stmt = con.prepareStatement("SELECT * FROM produto");
//            rs = stmt.executeQuery();
//            
//            while(rs.next()) {
//                Produto a = new Produto();
//                
//                a.setNome(rs.getString("nome"));
//                a.setPreco(rs.getDouble("preco"));
//                a.setQtd(rs.getInt("qtd"));
//                a.setItens(ItemDAO.read(rs.getInt("id")));
//                produtos.add(a);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally{
//            Conexao.closeConnection(con, stmt, rs);
//        }
//        
//        return produtos;
//    }
    
    public static int create(Produto p) {
        Connection con;
        con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int idProduto = 0;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produto (descricao, valor, qtd) VALUES (?, ?, ?);");
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getQtd());
            stmt.executeUpdate();
            
            stmt.close();
            
            stmt = con.prepareStatement("SELECT MAX(idproduto) FROM produto;");
            rs = stmt.executeQuery();
           
            while(rs.next())
            {
                idProduto = rs.getInt(1);
            }
            
        
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            Conexao.closeConnection(con, stmt, rs);
        }
        
        return idProduto;
    }
    
//    public void update(Produto p) {
//        Connection con;
//        con = Conexao.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        
//        try {
//            stmt = con.prepareStatement("UPDATE produto set nome = ?, cpf = ? usuario = ?, senha = ? WHERE id = ?");
//            stmt.setString(1, p.getNome());
//            stmt.setLong(2, p.getCpf());
//            stmt.setString(3, p.getUsuario());
//            stmt.setString(4, p.getSenha());
//            stmt.setInt(5, p.getId());
//            rs = stmt.executeQuery();        
//        } catch (SQLException ex) {
//            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally{
//            Conexao.closeConnection(con, stmt, rs);
//        }
//    }
}

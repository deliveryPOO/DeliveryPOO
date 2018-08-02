package DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinicius
 */
public class Conexao {
   private static final String DRIVER = "com.mysql.jdbc.Driver";
   private static final String URL = "jdbc:mysql://localhost:3306/escola";
   private static final String USER = "root";
   private static final String PASS = "12345";

   public static Connection getConnection() {
       try {
           Class.forName(DRIVER);
           return DriverManager.getConnection(URL, USER, PASS);
       } catch (ClassNotFoundException | SQLException ex) {
           throw new RuntimeException("Erro na conexao:", ex);
       }
   }
   
   public static void closeConnection(Connection con) {
        try {
            if(null != con) {
               con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /**
     *
     * @param con
     * @param stmt
     */
    public static void closeConnection(Connection con, PreparedStatement stmt) {
        closeConnection(con);
        
        try {
            if(null != stmt) {
               stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        
        try {
            if(null != rs) {
               rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


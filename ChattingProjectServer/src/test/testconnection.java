/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DTO.UserDTO;
import databaseConnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hazem
 */
public class testconnection {
    public static void main(String[] argus){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/chatting", "root", "");
            Statement stmt = connection.createStatement() ;
            ResultSet rs = stmt.executeQuery("select * from status") ;
            if(rs.next()){
                System.out.println(rs.getString(2));
            }
//            rs.getString(1);
            //System.out.println(rs.getInt(1));
            connection.close();
        } catch (ClassNotFoundException ex) {
            System.out.println("cannot connect to the database");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImp;

import DAOInterface.StatusDAOInt;
import DTO.StatusDTO;
import DTO.UserDTO;
import databaseConnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hazem
 */
public class StatusDAOImp implements StatusDAOInt{
    Connection connection = null;
    @Override
    public int create(StatusDTO status) {
        
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into status(name) values(?)");
            pst.setString(1, status.getName());
            pst.executeUpdate();            
            pst.close();
            connection.close();
            return 1;
        } catch (SQLException ex) {
            System.out.println("cannot insert the admin");
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public StatusDTO read(int id) {
        StatusDTO status = new StatusDTO();
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from status where id = ? ");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                status.setId(rs.getInt(1));
                status.setName(rs.getString(2));
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        return status;
    }

    @Override
    public void update(StatusDTO status) {
        try { 
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("update status set name= ? where id=?");
            pst.setString(1,status.getName());
            pst.setInt(2, status.getId());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the status");
            ex.printStackTrace();
        }
    }

    @Override
    public boolean delete(StatusDTO status) {
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("delete from status where id = ?");
            pst.setInt(1, status.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void printStatus(StatusDTO status){
        System.out.println("status id is: " + status.getId() );
        System.out.println("status name is: " + status.getName());
        
    }
    
    
     @Override
    public ArrayList<StatusDTO> readAll() {
        ArrayList<StatusDTO> status = new ArrayList<StatusDTO>();
        
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from status");
            
            rs = pst.executeQuery();
            while(rs.next()){
                StatusDTO st = new StatusDTO();
                st.setId(rs.getInt(1));
                st.setName(rs.getString(2));
                status.add(st);
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        return status;
    }
    
}

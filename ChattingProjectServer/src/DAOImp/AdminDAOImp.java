/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImp;

import DAOInterface.AdminDAOInt;
import DTO.AdminDTO;
import DTO.UserDTO;
import databaseConnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hazem
 */
public class AdminDAOImp implements AdminDAOInt{
    
    Connection connection = null;
    @Override
    public int create(AdminDTO admin) {
        UserDTO user = new UserDTO();
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into admin(user_name,password,add_admin,add_status) values(?,?,?,?)");
            pst.setString(1, admin.getUserName());
            pst.setString(2, admin.getPassWord());
            pst.setInt(3, admin.getAddAdmin());
            pst.setInt(4, admin.getAddStatus());
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
    public AdminDTO read(int id) {
        AdminDTO admin = new AdminDTO();
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from admin where id = ? ");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                admin.setId(rs.getInt(1));
                admin.setUserName(rs.getString(2));
                admin.setPassWord(rs.getString(3));
                admin.setAddAdmin(rs.getInt(4));
                admin.setAddStatus(rs.getInt(5));
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
        return admin;
    }

    @Override
    public void update(AdminDTO admin) {
        try { 
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("update admin set user_name= ?,password=?,add_admin=?,add_status=? where id=?");
            pst.setString(1,admin.getUserName());
            pst.setString(2, admin.getPassWord());
            pst.setInt(3, admin.getAddAdmin());
            pst.setInt(4,admin.getAddStatus());
            pst.setInt(5, admin.getId());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the user");
            ex.printStackTrace();
        }
    }

    @Override
    public boolean delete(AdminDTO admin) {
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("delete from admin where id = ?");
            pst.setInt(1, admin.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void printAdmin(AdminDTO ad){
        System.out.println("admin id is: " + ad.getId() );
        System.out.println("admin username is: " + ad.getUserName());
        System.out.println("admin password is: " + ad.getPassWord());
        System.out.println("admin addadmin is: " + ad.getAddAdmin());
        System.out.println("admin addstatus is: " + ad.getAddStatus());
    }

    @Override
    public AdminDTO readByUsername(String username) {
        AdminDTO admin = null;
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from admin where user_name = ? ");
            pst.setString(1, username);
            rs = pst.executeQuery();
            if(rs.next()){
                admin = new AdminDTO();
                admin.setId(rs.getInt(1));
                admin.setUserName(rs.getString(2));
                admin.setPassWord(rs.getString(3));
                admin.setAddAdmin(rs.getInt(4));
                admin.setAddStatus(rs.getInt(5));
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the admin");
            ex.printStackTrace();
        }
        return admin;
    }
    
    
    
}

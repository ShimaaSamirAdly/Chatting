/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImp;

import DAOInterface.FriendDAOInt;
import DTO.FriendshipDTO;
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
public class FriendDAOImp implements FriendDAOInt{
    
    Connection connection = null;
    @Override
    public int create(FriendshipDTO friendship) {
        FriendshipDTO friend = new FriendshipDTO();
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into friends(user_id,friend_id,friend_status,fcolor,ffamily,fsize) values(?,?,?,?,?,?)");
            pst.setInt(1, friendship.getUser1());
            pst.setInt(2, friendship.getUser2());
            pst.setInt(3, friendship.getFriendStatus());
            pst.setString(4, friendship.getfColor());
            pst.setString(5, friendship.getfFamily());
            pst.setInt(6, friendship.getfSize());
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
     public void update(FriendshipDTO friendship){
        FriendshipDTO friend = new FriendshipDTO();
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("update friends set friend_status = ? where (user_id = ? and friend_id = ?) or (friend_id = ? and user_id = ?)");
            
            pst.setInt(1, friendship.getFriendStatus());
            pst.setInt(2, friendship.getUser1());
            pst.setInt(3, friendship.getUser2());
            pst.setInt(4, friendship.getUser2());
            pst.setInt(5, friendship.getUser1());
            
            pst.executeUpdate();            
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot insert the admin");
            ex.printStackTrace();
        }
     }

    @Override
    public void delete(FriendshipDTO friendship){
        FriendshipDTO friend = new FriendshipDTO();
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("delete from friends where (user_id = ? and friend_id = ?)or (friend_id = ? and user_id = ?)");            
            pst.setInt(1, friendship.getUser1());
            pst.setInt(2, friendship.getUser2());
            pst.setInt(3, friendship.getUser2());
            pst.setInt(4, friendship.getUser1());
            
            pst.executeUpdate();            
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot insert the admin");
            ex.printStackTrace();
        }
    }
    
    @Override
    public int checkFriends(int id1,int id2){
        ResultSet rs = null;
        int x = -1;
        try {
            
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select friend_status from friends where (friend_id = ? and user_id = ?) or (friend_id = ? and user_id = ?)");
            pst.setInt(1, id1);
            pst.setInt(2, id2);
            pst.setInt(3, id2);
            pst.setInt(4, id1);
            rs = pst.executeQuery();
            if(rs.next()){
                x = (rs.getInt(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    
    @Override
    public ArrayList<Integer> friendlist(int id){
        ArrayList<Integer> arr = new ArrayList<Integer>();
        ResultSet rs = null;
        try {
            connection = databaseConnection.DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select user_id from friends where ( friend_id=?) and friend_status=1");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                arr.add(rs.getInt(1));
                
            }
            pst = connection.prepareStatement("select friend_id from friends where ( user_id=?) and friend_status=1");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                arr.add(rs.getInt(1));
                
            }
            pst.close();
            connection.close();
            
            //return 1;
        } catch (SQLException ex) {
            System.out.println("cannot return the number of friends");
            ex.printStackTrace();
        }
        return arr;
    }

    @Override
    public void sendRequest(int sender , int receiver){
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into friends(user_id,friend_id,friend_status,fcolor,ffamily,fsize) values(?,?,?,?,?,?)");
            pst.setInt(1, sender);
            pst.setInt(2, receiver);
            pst.setInt(3, 0);
            pst.setString(4, "");
            pst.setString(5, "");
            pst.setInt(6, 14);
            pst.executeUpdate();            
            pst.close();
            connection.close();
            
        } catch (SQLException ex) {
            System.out.println("cannot insert the relationship");
            ex.printStackTrace();
        }
        
    }

    @Override
    public Object create(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object read(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FriendshipDTO returnTextStyle(int id1, int id2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
    


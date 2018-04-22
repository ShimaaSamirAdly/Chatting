/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImp;

import DAOInterface.ChatUserDAOInt;
import DTO.ChatUserDTO;
import databaseConnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hazem
 */
public class ChatUserDAOImp implements ChatUserDAOInt {
    Connection connection = null;
    @Override
    public int create(ChatUserDTO chat) {
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into groupmembers(group_id,member_id) values(?,?)");
            pst.setInt(1, chat.getChatId());
            pst.setInt(2, chat.getUserId());
            pst.executeUpdate();            
            pst.close();
            connection.close();
            return 1;
        } catch (SQLException ex) {
            System.out.println("cannot insert this chat with this member");
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<ChatUserDTO> read(int id) {
        ArrayList<ChatUserDTO> chat = new ArrayList<ChatUserDTO>();
        
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from groupmembers where group_id = ? ");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                ChatUserDTO ch = new ChatUserDTO();
                ch.setChatId(rs.getInt(1));
                ch.setUserId(rs.getInt(2));
                chat.add(ch);
                
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the chat");
            ex.printStackTrace();
        }
        return chat;
    }

    @Override
    public void update(ChatUserDTO chat) {
    }

    @Override
    public boolean delete(ChatUserDTO chat) {
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("delete from groupmembers where group_id = ?");
            pst.setInt(1, chat.getChatId());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int chatGroup(int id,ArrayList<Integer> users){
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = null;
            for(int i=0;i<users.size();i++){
                pst = connection.prepareStatement("insert into groupmembers(group_id,member_id) values(?,?)");
                pst.setInt(1, id);
                pst.setInt(2, users.get(i));
                pst.executeUpdate();
            }
                        
            pst.close();
            connection.close();
            return 1;
        } catch (SQLException ex) {
            System.out.println("cannot insert this chat with this member");
            ex.printStackTrace();
        }
        return 0;
    }
    
}

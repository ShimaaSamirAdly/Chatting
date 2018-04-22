/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImp;

import DAOInterface.ChatGroupDAOInt;
import DTO.ChatGroupDTO;
import DTO.ChatUserDTO;
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
public class ChatGroupDAOImp implements ChatGroupDAOInt {
    Connection connection = null;
    ResultSet rs = null;
    int x = 0;
    @Override
    public int create(ChatGroupDTO chat) {
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("insert into chatgroups(name,creator) values(?,?)");
            pst.setString(1, chat.getChatName());
            pst.setInt(2, chat.getUserId());
            pst.executeUpdate(); 
            pst = connection.prepareStatement("SELECT MAX(Id) FROM chatgroups");
            rs = pst.executeQuery();
            if(rs.next()){
              x =  rs.getInt(1);
            }
            pst.close();
            connection.close();
            return x;
        } catch (SQLException ex) {
            System.out.println("cannot insert this chat");
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public ChatGroupDTO read(int id) {
        ChatGroupDTO chat = new ChatGroupDTO();
        ResultSet rs = null;
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from chatgroups where id = ? ");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()){
                chat.setChatId(rs.getInt(1));
                chat.setChatName(rs.getString(2));
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
    public void update(ChatGroupDTO chat) {
        try { 
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("update status set name= ? where id=?");
            pst.setString(1,chat.getChatName());
            pst.setInt(2, chat.getChatId());
            pst.executeUpdate();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot update the chat");
            ex.printStackTrace();
        }
    }

    @Override
    public boolean delete(ChatGroupDTO chat) {
        try {
            connection = DatabaseConnection.getConnection();
            PreparedStatement pst = connection.prepareStatement("delete from chatgroups where id = ?");
            pst.setInt(1, chat.getChatId());
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ArrayList<ChatGroupDTO> myGroups(int id){
        
        ChatUserDTO chatuser = new ChatUserDTO();
        ArrayList<ChatGroupDTO> mychats = new ArrayList<ChatGroupDTO>();
        ArrayList<Integer> mygroupsId = new ArrayList<Integer>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            connection = DatabaseConnection.getConnection();
            pst = connection.prepareStatement("select group_id from groupmembers where member_id = ? ");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                mygroupsId.add(rs.getInt(1));
            }
            for(int i=0;i<mygroupsId.size();i++){
                pst = connection.prepareStatement("select * from chatgroups where id = ? ");
                pst.setInt(1, mygroupsId.get(i));
                rs = pst.executeQuery();
                while(rs.next()){
                    ChatGroupDTO chatgroup = new ChatGroupDTO();
                    chatgroup.setChatId(rs.getInt(1));
                    chatgroup.setChatName(rs.getString(2));
                    chatgroup.setUserId(rs.getInt(3));
                    mychats.add(chatgroup);
                }
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the chat");
            ex.printStackTrace();
        }
        return mychats;
    }
    
    public ArrayList<UserDTO> chatusers(int id){
        ArrayList<UserDTO> users= new ArrayList<UserDTO>();
        ArrayList<Integer> usersId = new ArrayList<Integer>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            connection = DatabaseConnection.getConnection();
            pst = connection.prepareStatement("select member_id from groupmembers where group_id = ? ");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                usersId.add(rs.getInt(1));
            }
            for(int i=0;i<usersId.size();i++){
                UserDTO user = new UserDTO();
                pst = connection.prepareStatement("select * from users where id = ? ");
                pst.setInt(1, usersId.get(i));
            rs = pst.executeQuery();
            if(rs.next()){
                user.setId(rs.getInt(1));
                user.setfName(rs.getString(2));
                user.setlName(rs.getString(3));
                user.setUserName(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPassWord(rs.getString(6));
                user.setCountry(rs.getString(7));
                user.setGender(rs.getString(8));
                user.setPhoneNumber(rs.getString(9));
                user.setOnOffLine(rs.getString(10));
                user.setStatusID(rs.getInt(11));
            }
            users.add(user);
            }
            rs.close();
            pst.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("cannot select the chat");
            ex.printStackTrace();
        }
        return users;
    }
    
    
    
}

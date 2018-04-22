/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatting;

import DTO.ChatGroupDTO;
import DTO.ChatUserDTO;
import DTO.FriendshipDTO;
import DTO.StatusDTO;
import DTO.UserDTO;
import java.io.File;
import java.net.InetAddress;
import java.rmi.*;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

/**
 *
 * @author Shimaa
 */
public interface Chatting extends Remote{
    
   void register(CallBack client, UserDTO user) throws Exception;
    
    UserDTO logIn(String userName) throws Exception;
    
    UserDTO singUp(UserDTO user) throws Exception;
    
    void unRegister(UserDTO user) throws Exception;

   ArrayList<UserDTO> getFriends(int id) throws Exception;
   
   void sendMessage(String msg, int receiver, int sender, String color, int size, String family) throws Exception;
   
   ArrayList<ChatGroupDTO> getGroups(int id) throws Exception;
   
   ArrayList<ChatUserDTO> getGroupMembers(int id) throws Exception;
   
   void sendToGroup(String msg, int groupId, int sender, String color, int size, String family) throws Exception;
   
   void update(UserDTO user) throws Exception;
   
   void tellOnOff(UserDTO user) throws Exception;
   
   ArrayList<StatusDTO> getStatus() throws Exception;
    
   StatusDTO getStatus(int id) throws Exception;
   
   int createGroup(ChatGroupDTO chat) throws Exception;
   
   int chatGroup(int id,ArrayList<Integer> users) throws Exception;
   
   void newGroup(UserDTO user, ArrayList<Integer> members) throws Exception;
   
   void updateRelations(FriendshipDTO friendship) throws Exception;
   
   void deleteFriend(FriendshipDTO friendship) throws Exception;
   
   int checkFriendsStatus(int id1,int id2) throws Exception;
   
   void acceptFile(String ip, int receiver, int sender) throws Exception;
   
   void sendRequest(int sender , int receiver) throws RemoteException; 
   
   UserDTO findUserByEmail(String email) throws RemoteException;
   
   void requestRespond(UserDTO sender,UserDTO receiver) throws RemoteException;
   
   public void acceptRequest(FriendshipDTO f) throws RemoteException;
   
   public void refuseRequest(FriendshipDTO f) throws RemoteException;
   
   public void notifyAllUsers(String msg) throws RemoteException;
   
   public void updateUser(UserDTO user) throws RemoteException;
   
   public void uploadImage(int id,File fis) throws RemoteException;
   
   public File downloadImage(int id) throws RemoteException;
   
   void unRegisterAll() throws RemoteException;
   
   void userExit(UserDTO user) throws RemoteException;
}

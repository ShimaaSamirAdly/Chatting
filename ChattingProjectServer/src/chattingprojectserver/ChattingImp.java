/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattingprojectserver;

import DAOImp.ChatGroupDAOImp;
import DAOImp.ChatUserDAOImp;
import DAOImp.UserDAOImp;
import DAOImp.FriendDAOImp;
import DAOImp.StatusDAOImp;
import DTO.ChatGroupDTO;
import DTO.ChatUserDTO;
import DTO.FriendshipDTO;
import DTO.StatusDTO;
import DTO.UserDTO;
import chatting.CallBack;
import chatting.Chatting;
import email.MailAPI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
//import DAOImp.UserDAOImp;

/**
 *
 * @author Shimaa
 */
public class ChattingImp extends UnicastRemoteObject implements Chatting,Serializable{
    
    //Vector<CallBack> clients = new Vector<CallBack>();
    Hashtable<Integer, CallBack> clients = new Hashtable<Integer, CallBack>();
    //int i = 1;
    int id = 0;
    
    int sendId = 0;
    
    UserDAOImp userImp = new UserDAOImp();
    FriendDAOImp friendImp = new FriendDAOImp();
    ChatGroupDAOImp groupImp = new ChatGroupDAOImp();
    ChatUserDAOImp groupMem = new ChatUserDAOImp();
    StatusDAOImp statusImp = new StatusDAOImp();
    
    public ChattingImp() throws RemoteException{}
    
    public void register(CallBack client, UserDTO user){

        clients.put(user.getId(), client);
        
    }
    
    public UserDTO logIn(String userName){
    
       return userImp.readByUsername(userName);
        
    }
    
    /**
     *
     * @param user
     * @return
     */
    @Override
    public UserDTO singUp(UserDTO user){
    
        return userImp.create(user);
    }
    
    public void unRegister(UserDTO user){
        
        clients.remove(user.getId());
        System.out.println("Client Removed");
    
    }
    
    
    public ArrayList<UserDTO> getFriends(int id){
    
       ArrayList<Integer> arr = new ArrayList<Integer>();
       arr = friendImp.friendlist(id);
       if(arr != null){
       return userImp.MyFriendList(arr);
       }else{
           return null;
       }
    }
    
    public void sendMessage(String msg, int receiver, int sender, String color, int size, String family) throws RemoteException{
    
        clients.get(receiver).recieve(msg, receiver, sender, color, size, family);
        clients.get(sender).recieve(msg, receiver, sender, color, size, family);
    }
    
    public ArrayList<ChatGroupDTO> getGroups(int id){
    
        return groupImp.myGroups(id);
    }
    
    public ArrayList<ChatUserDTO> getGroupMembers(int id){
    
        return groupMem.read(id);
    }
    
    public void sendToGroup(String msg, int groupId, int sender, String color, int size, String family) throws RemoteException{
    
        ChatUserDAOImp chatUser = new ChatUserDAOImp();
        ArrayList<ChatUserDTO> ch = new ArrayList<ChatUserDTO>();
        ch = chatUser.read(groupId);
        for(int i = 0; i<ch.size(); i++){
            //System.out.println(clients.get(ch.get(i).getUserId()));
            if(clients.get(ch.get(i).getUserId()) != null)
        clients.get(ch.get(i).getUserId()).recieveGroup(msg, groupId, sender, color, size, family);
        }
    }
    
    public void update(UserDTO user) throws Exception{
    
        userImp.update(user);
    }

    
    
    public void tellOnOff(UserDTO user) throws Exception{
    
       ArrayList<UserDTO> arr = new ArrayList<UserDTO>();
       arr = getFriends(user.getId());
       
       for(int i=0; i<arr.size(); i++){
       System.out.println(clients.get(arr.get(i).getId()));
           if(clients.containsKey(arr.get(i).getId())){
               System.out.println("hello");
               clients.get(arr.get(i).getId()).resetFriendList();
               clients.get(arr.get(i).getId()).notifyOnOff(user);
               
           }
       }
        
    }
    
    @Override
    public ArrayList<StatusDTO> getStatus() throws Exception{
    
        ArrayList<StatusDTO> ob = new ArrayList<StatusDTO>();
        ob.addAll(statusImp.readAll());
        return ob;
    }

    
    @Override
    public StatusDTO getStatus(int id) throws Exception{
    
        return statusImp.read(id);
        
    }
    
    @Override
    public int createGroup(ChatGroupDTO chat) throws Exception{
    
       return groupImp.create(chat);
    }
    
    @Override
    public int chatGroup(int id,ArrayList<Integer> users) throws Exception{
    
        return groupMem.chatGroup(id, users);
    }
    
    @Override
    public void newGroup(UserDTO user, ArrayList<Integer> members) throws Exception{
    
       clients.get(user.getId()).resetChat();
       for(int i=0; i<members.size(); i++){
       
           if(clients.get(members.get(i))!= null){
           
               clients.get(members.get(i)).resetChat();
           }
       }
        
    }
    
    public void updateRelations(FriendshipDTO friendship) throws Exception{
        
        friendImp.update(friendship);
       
    }
    
    public void deleteFriend(FriendshipDTO friendship) throws Exception{
    
        friendImp.delete(friendship);
    }
    
    
    public int checkFriendsStatus(int id1,int id2){
        return friendImp.checkFriends(id1, id2);
    }
    
    public void acceptFile(String ip, int receiver, int sender) throws Exception{
    
        clients.get(receiver).receiveFile(ip, receiver, sender);
    }

    @Override
    public void sendRequest(int sender, int receiver) throws RemoteException {
        friendImp.sendRequest(sender, receiver);
        clients.get(receiver).notifyRequest();
        
    }

    @Override
    public UserDTO findUserByEmail(String email) throws RemoteException {
         UserDTO user = userImp.findUserByEmail(email);
        return user;
    }

    @Override
    public void requestRespond(UserDTO sender, UserDTO receiver) throws RemoteException {
        clients.get(receiver.getId()).requestRespond(sender);
    }

    @Override
    public void acceptRequest(FriendshipDTO f) throws RemoteException {
        friendImp.update(f);
    }

    @Override
    public void refuseRequest(FriendshipDTO f) throws RemoteException {
        friendImp.delete(f);
    }

    @Override
    public void notifyAllUsers(String msg) throws RemoteException {
        for(Map.Entry m : clients.entrySet()){
            System.out.println(m.getKey());
            System.out.println(m.getValue());
           CallBack d = (CallBack)m.getValue();
            try {
                d.notifyUsers(msg);
                System.out.println(msg + "from d.notify");
            } catch (RemoteException ex) {
                Logger.getLogger(ChattingImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
        System.out.println("hello from notify all users");
    }

    @Override
    public void updateUser(UserDTO user) throws RemoteException {
        userImp.update(user);
    }

    @Override
    public void uploadImage(int id, File fis) throws RemoteException {
            try {
            System.out.println(id);
            System.out.println(fis.toString());
            userImp.uploadImage(id, fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChattingImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public File downloadImage(int id) throws RemoteException {
         File file = null ;
        try {
            file = userImp.downloadImage(id);
        } catch (IOException ex) {
            Logger.getLogger(ChattingImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return file;
        
    }

    public void welcommingMail(UserDTO user){
        System.out.println(MailAPI.serverMail);
        System.out.println(MailAPI.serverPassword);
        System.out.println(user.getEmail());
        MailAPI mailAPI = new MailAPI();
        mailAPI.setFrom(MailAPI.serverMail);
        mailAPI.setPassword(MailAPI.serverPassword);
        mailAPI.setTo(user.getEmail());
        mailAPI.setSubject("wlelcome to connect chat application");
        mailAPI.setText("welcome to our chat application ,we hope you will have a good time with your friends, here is your user name & password username: " + user.getUserName() + "password: " + user.getPassWord());
        mailAPI.sendMail(mailAPI);
    }
    
    
    public void unRegisterAll() throws RemoteException{
        
        notifyAllUsers("Server is down .......");
        Enumeration e = clients.keys();
        while(e.hasMoreElements()){
            clients.remove((Integer) e.nextElement());
            
            
        }
        
    
    }
    
    
    public void userExit(UserDTO user){
        
        clients.remove(user.getId());
        System.out.println("Client Removed");
    
    }

    
   
}

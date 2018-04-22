/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chattingprojectclientfxml;

import DTO.UserDTO;
import chatting.CallBack;
import java.io.Serializable;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javafx.scene.paint.Color;

/**
 *
 * @author Shimaa
 */
public class ClientImp extends UnicastRemoteObject implements CallBack, Serializable{
    
    SignInController signIn;
    SignUpController signUp;
    MainScreenController mainScreen;

    public void setSignIn(SignInController signIn) {
        this.signIn = signIn;
    }

    public void setSignUp(SignUpController signUp) {
        this.signUp = signUp;
    }

    public void setMainScreen(MainScreenController mainScreen) {
        this.mainScreen = mainScreen;
    }
    
    public ClientImp(SignInController signIn) throws RemoteException{
    
        this.signIn = signIn;
    }
    
    public ClientImp(SignUpController signUp) throws RemoteException{
    
        this.signUp = signUp;
    }
    
    public ClientImp(MainScreenController mainScreen) throws RemoteException{
    
        this.mainScreen = mainScreen;
    }


    @Override
    public void recieve(String msg,int receiver, int sender, String color, int size, String family) throws RemoteException {
      
      mainScreen.setMessage(msg, receiver, sender, color, size, family);

    }

    @Override
    public void recieveGroup(String msg,int groupId, int sender, String color, int size, String family) throws RemoteException {
      
      mainScreen.setMessageGroup(msg, groupId, sender, color, size, family);

    }
    
    @Override
    public void resetFriendList() throws RemoteException{
        
        mainScreen.resetFriendsList();
        
    }
    
    public void notifyOnOff(UserDTO user){
        if(user.getOnOffLine().equals("off")){
            mainScreen.notifyMe(user.getUserName() + "bocome offline");
        }else{
            mainScreen.notifyMe(user.getUserName() + "bocome online");
        }    
    }
    
    public void notifyRequest(){
        mainScreen.notifyMe( "You have aFriend request ....");
    }
    
    
    @Override
    public void resetChat() throws Exception{
    
        mainScreen.resetChat();
    }

    @Override
    public void receiveFile(String ip, int receiver, int sender) throws Exception {
        mainScreen.downloadFile(ip, receiver, sender);
    }
    
    @Override
    public void requestRespond(UserDTO user1) throws RemoteException {
        
        mainScreen.requestRespond(user1);
        System.out.println("requestRespond in chatting client impl");
        System.out.println(user1.getId());
        
    }
    
    @Override
    public void notifyUsers(String msg){
        
        mainScreen.notifyMe(msg);
    }
    
    
}

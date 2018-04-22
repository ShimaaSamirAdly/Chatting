/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatting;

import DTO.UserDTO;
import java.net.InetAddress;
import java.rmi.*;
import javafx.scene.paint.Color;

/**
 *
 * @author Shimaa
 */
public interface CallBack extends Remote{
    
   void recieve(String msg,int receiver, int sendId, String color, int size, String family) throws RemoteException;
    void recieveGroup(String msg, int groupId, int sender, String color, int size, String family) throws RemoteException;
    void resetFriendList() throws Exception;
    void resetChat() throws Exception;
    void receiveFile(String ip, int receiver, int sender) throws Exception;
    void requestRespond(UserDTO user1) throws RemoteException;
    void notifyUsers(String msg) throws RemoteException;
    void notifyOnOff(UserDTO user) throws RemoteException;
    void notifyRequest() throws RemoteException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import DTO.FriendshipDTO;
import java.util.ArrayList;

/**
 *
 * @author Hazem
 */
public interface FriendDAOInt extends GenericInterface {
    
    ArrayList<Integer> friendlist(int id);
    int create(FriendshipDTO friendship);
    void update(FriendshipDTO friendship);
    int checkFriends(int id1,int id2);
    void delete(FriendshipDTO friendship);
    void sendRequest(int sender , int receiver);
    FriendshipDTO returnTextStyle(int id1,int id2);
}

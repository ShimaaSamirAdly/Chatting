/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import DTO.ChatGroupDTO;
import DTO.UserDTO;
import java.util.ArrayList;

/**
 *
 * @author Hazem
 */
public interface ChatGroupDAOInt {
    int create(ChatGroupDTO chat);
    ChatGroupDTO read(int id);
    void update(ChatGroupDTO chat);
    boolean delete(ChatGroupDTO chat);
    ArrayList<ChatGroupDTO> myGroups(int id);
    ArrayList<UserDTO> chatusers(int id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import DTO.ChatUserDTO;
import DTO.UserDTO;
import java.util.ArrayList;

/**
 *
 * @author Hazem
 */
public interface ChatUserDAOInt {
    int create(ChatUserDTO chat);
    ArrayList<ChatUserDTO> read(int id);
    void update(ChatUserDTO chat);
    boolean delete(ChatUserDTO chat);
    int chatGroup(int id,ArrayList<Integer> users);
}

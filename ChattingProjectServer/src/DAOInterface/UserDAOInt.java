/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import DTO.UserDTO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Hazem
 */
public interface UserDAOInt {
    UserDTO create(UserDTO t);
    UserDTO read(int id);
    void update(UserDTO t);
    boolean delete(UserDTO t);
    int femaleusers();
    int maleusers();
    ArrayList<UserDTO> MyFriendList(ArrayList<Integer> users);
    void printuser(UserDTO u);
    UserDTO findUserByEmail(String email);
    UserDTO readByUsername(String username);
    void uploadImage(int id,File file) throws FileNotFoundException;
    File downloadImage(int id) throws FileNotFoundException, IOException;
    int offLineUsers();
    int onLineUsers();
}

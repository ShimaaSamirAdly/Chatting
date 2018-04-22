/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import DTO.StatusDTO;
import java.util.ArrayList;

/**
 *
 * @author Hazem
 */
public interface StatusDAOInt{
    int create(StatusDTO status);
    StatusDTO read(int id);
    void update(StatusDTO status);
    boolean delete(StatusDTO status);
    ArrayList<StatusDTO> readAll();
}

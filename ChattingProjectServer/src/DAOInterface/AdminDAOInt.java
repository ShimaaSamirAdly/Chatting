package DAOInterface;

import DTO.AdminDTO;

public interface AdminDAOInt {
    int create(AdminDTO admin);
    AdminDTO read(int id);
    void update(AdminDTO admin);
    boolean delete(AdminDTO admin);
    AdminDTO readByUsername(String username);
}
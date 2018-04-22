package DAOInterface;
public interface GenericInterface<T, ID>{
    ID create(T t);
    T read(ID id);
    void update(T t);
    boolean delete(T t);
    
}
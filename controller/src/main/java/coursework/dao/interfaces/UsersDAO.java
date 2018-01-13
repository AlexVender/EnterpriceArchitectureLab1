package coursework.dao.interfaces;

import coursework.dao.exceptions.DAOException;
import coursework.entities.UserEntity;

import java.util.List;


public interface UsersDAO {
    Integer create(UserEntity user) throws DAOException;
    
    UserEntity read(Integer id) throws DAOException;
    
    List<UserEntity> readAll(Integer limit, Integer offset) throws DAOException;
    
    void update(UserEntity user) throws DAOException;
    
    void delete(UserEntity userEntity) throws DAOException;
}

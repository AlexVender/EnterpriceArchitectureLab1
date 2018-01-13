package coursework.dao;

import coursework.dao.exceptions.DAOException;
import coursework.dao.interfaces.UsersDAO;
import coursework.entities.UserEntity;

import javax.ejb.Stateless;
import java.util.List;


@Stateless
public class UsersDAOImpl extends AbstractDAO implements UsersDAO {
    public UsersDAOImpl() {
    }
    
    @Override
    public Integer create(UserEntity user) throws DAOException {
        return super.create(user);
    }

    @Override
    public UserEntity read(Integer id) throws DAOException {
        return (UserEntity) read(id, UserEntity.class);
    }

    @Override
    public List<UserEntity> readAll(Integer limit, Integer offset) throws DAOException {
        return readAll(limit, offset, UserEntity.class);
    }

    @Override
    public void update(UserEntity user) throws DAOException {
        super.update(user);
    }

    @Override
    public void delete(UserEntity userEntity) throws DAOException {
        super.delete(userEntity);
    }
}

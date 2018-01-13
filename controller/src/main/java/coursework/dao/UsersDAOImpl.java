package coursework.dao;

import coursework.dao.exceptions.DAOException;
import coursework.dao.interfaces.UsersDAO;
import coursework.entities.UserEntity;

import java.util.List;


/**
 * Created by chanta on 08.03.17.
 */
public class UsersDAOImpl extends AbstractDAO implements UsersDAO {
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

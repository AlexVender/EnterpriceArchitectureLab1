package coursework.dao.interfaces;

import coursework.dao.exceptions.DAOException;
import coursework.entities.TaskEntity;

import javax.ejb.Remote;
import java.util.List;


@Remote
public interface TasksDAO {
    Integer create(TaskEntity task) throws DAOException;
    
    TaskEntity read(Integer id) throws DAOException;
    
    List<TaskEntity> readAll(Integer limit, Integer offset) throws DAOException;
    
    void update(TaskEntity task) throws DAOException;
    
    void delete(TaskEntity taskEntity) throws DAOException;
}

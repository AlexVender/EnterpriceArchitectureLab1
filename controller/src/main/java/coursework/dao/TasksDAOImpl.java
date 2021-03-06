package coursework.dao;

import coursework.dao.exceptions.DAOException;
import coursework.dao.interfaces.TasksDAO;
import coursework.entities.TaskEntity;

import javax.ejb.Stateless;
import java.util.List;


@Stateless
public class TasksDAOImpl extends AbstractDAO implements TasksDAO {
    public TasksDAOImpl() {
    }
    
    @Override
    public Integer create(TaskEntity task) throws DAOException {
        return super.create(task);
    }

    @Override
    public TaskEntity read(Integer id) throws DAOException {
        return (TaskEntity) read(id, TaskEntity.class);
    }

    @Override
    public List<TaskEntity> readAll(Integer limit, Integer offset) throws DAOException {
        return readAll(limit, offset, TaskEntity.class);
    }

    @Override
    public void update(TaskEntity task) throws DAOException {
        super.update(task);
    }

    @Override
    public void delete(TaskEntity taskEntity) throws DAOException {
        super.delete(taskEntity);
    }
}

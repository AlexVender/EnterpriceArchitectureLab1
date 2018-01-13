package coursework.dao;

import coursework.dao.exceptions.DAOException;
import coursework.dao.interfaces.ProjectsDAO;
import coursework.entities.ProjectEntity;

import java.util.List;


/**
 * Created by chanta on 08.03.17.
 */
public class ProjectsDAOImpl extends AbstractDAO implements ProjectsDAO {
    @Override
    public Integer create(ProjectEntity project) throws DAOException {
        return super.create(project);
    }

    @Override
    public ProjectEntity read(Integer id) throws DAOException {
        return (ProjectEntity) read(id, ProjectEntity.class);
    }

    @Override
    public List<ProjectEntity> readAll(Integer limit, Integer offset) throws DAOException {
        return readAll(limit, offset, ProjectEntity.class);
    }

    @Override
    public void update(ProjectEntity project) throws DAOException {
        super.update(project);
    }

    @Override
    public void delete(ProjectEntity projectEntity) throws DAOException {
        super.delete(projectEntity);
    }
}

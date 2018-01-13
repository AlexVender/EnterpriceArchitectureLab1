package coursework.dao.interfaces;

import coursework.dao.exceptions.DAOException;
import coursework.entities.ProjectEntity;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ProjectsDAO {
    Integer create(ProjectEntity project) throws DAOException;
    
    ProjectEntity read(Integer id) throws DAOException;
    
    List<ProjectEntity> readAll(Integer limit, Integer offset) throws DAOException;
    
    void update(ProjectEntity project) throws DAOException;
    
    void delete(ProjectEntity projectEntity) throws DAOException;
}

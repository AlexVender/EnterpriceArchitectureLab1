package coursework.utils;

import coursework.dao.interfaces.ProjectsDAO;
import coursework.dao.interfaces.TasksDAO;
import coursework.dao.interfaces.UsersDAO;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;



public class EJBBeanProvider {
    public static final EJBBeanProvider INSTANCE = new EJBBeanProvider();
    
    private Properties props;
    private static InitialContext ctx;
    
    @EJB
    private ProjectsDAO projectsDAO;
    
    private EJBBeanProvider() {
    }
    
    public static void initContext() {
        if (ctx == null) {
            try {
                ctx = new InitialContext();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static EJBBeanProvider getInstance() {
        initContext();
        return INSTANCE;
    }
    
    public ProjectsDAO getProjectsDAO() {
        try {
            return (ProjectsDAO) ctx.lookup("java:global/view-1.0-SNAPSHOT/ProjectsDAOImpl!coursework.dao.interfaces.ProjectsDAO");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public TasksDAO getTasksDAO() {
        try {
            return (TasksDAO) ctx.lookup("java:global/view-1.0-SNAPSHOT/TasksDAOImpl!coursework.dao.interfaces.TasksDAO");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public UsersDAO getUsersDAO() {
        try {
            return (UsersDAO) ctx.lookup("java:global/view-1.0-SNAPSHOT/UsersDAOImpl!coursework.dao.interfaces.UsersDAO");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public SessionFactoryProvider getSessionFactoryProvider() {
        try {
            return (SessionFactoryProvider) ctx.lookup("java:global/view-1.0-SNAPSHOT/HibernateSessionFactoryProvider!coursework.utils.SessionFactoryProvider");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}

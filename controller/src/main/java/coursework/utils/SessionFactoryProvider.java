package coursework.utils;

import org.hibernate.SessionFactory;

import javax.ejb.Remote;


@Remote
public interface SessionFactoryProvider {
    
    SessionFactory getSessionFactory();
    
    void shutdown();
}

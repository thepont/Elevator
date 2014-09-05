package com.paulesson.elevator.db.dao;

import com.paulesson.elevator.db.entities.Command;
import java.util.List;
import javax.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Paul Esson
 */
public class CommandDaoImpl implements CommandDao {
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public int saveCommand(Command cmd){
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(cmd);
        tx.commit();
        session.close();
        return cmd.getId();
    }
    @Override
    public List<Command>getCommands(){
        Session session = this.sessionFactory.openSession();
        List<Command> commandList = (List<Command>)session.createQuery("from Command").list();
        session.close();
        return commandList;
    }
}

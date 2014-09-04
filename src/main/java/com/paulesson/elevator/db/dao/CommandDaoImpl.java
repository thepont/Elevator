/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.paulesson.elevator.db.dao;

import com.paulesson.elevator.db.entities.Command;
import java.util.List;
import javax.annotation.PostConstruct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author pont
 */
public class CommandDaoImpl implements CommandDao {
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
//    @PostConstruct
//    void init(){
//        System.out.println("Creating table in given database...");
//        
//        Session session = this.sessionFactory.openSession();
//            
//
//        String sql = "CREATE TABLE COMMAND " +
//                    "(ID INTEGER not NULL, " +
//                    " FLOOR_FROM VARCHAR(255), " +
//                    " FLOOR_TO VARCHAR(255), " +
//                    " NUM_PEOPLE INTEGER)";
//        session.createQuery(sql);
//        session.close();
//    }
    
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

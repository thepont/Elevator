package com.paulesson.elevator.db.dao;

import com.paulesson.elevator.db.entities.Elevator;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Allows us to store and retrieve elevators form a database 
 * @author Paul Esson
 */
public abstract class ElevatorDaoImpl implements ElevatorDao {
    
    private SessionFactory sessionFactory;
 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveElevator(Elevator elevator) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(elevator);
        tx.commit();
        session.close();
    }
    @Override
    public void updateElevator(Elevator elevator){
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction(); 
        Elevator persisted = (Elevator)session.load(Elevator.class, elevator.getId());
        persisted.setLoad(elevator.getLoad());
        persisted.setStatus(elevator.getStatus());
        persisted.setCurrentFloor(elevator.getCurrentFloor());
        session.persist(persisted);
        tx.commit();
        session.close();
        
    }

    @Override
    public Elevator getElevator(int id) {
        Elevator returned;
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        returned = (Elevator)session.load(Elevator.class, id);
        Hibernate.initialize(returned);
        tx.commit();
        session.close();
        return returned;
    }
    
    protected Elevator createElevator(int id, String name)
    {
        Elevator returned;
        returned = new Elevator();
        returned.setId(id);
        returned.setName(name);
        saveElevator(returned);
        return returned;
    }
            
    
    @Override
    public Elevator getOrCreate(int id, String name) {
        Elevator returned;
        try{
            returned = getElevator(id);
        } catch (org.hibernate.ObjectNotFoundException e)
        {
            returned = createElevator(id, name);
        }
        return returned;
    }
    
}

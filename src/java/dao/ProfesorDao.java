/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Profesor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author esmeralda
 */
public class ProfesorDao {
    
    private Session session;
    private Transaction tx;
    private Integer idProfesor;
    
    private void init(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        tx = session.beginTransaction();
    }
            
    private void rollback(){
        if(tx != null){
            tx.rollback();
        }
    }
    
    public List<Profesor> getAll(){
        List<Profesor> list = null;
        try{
            init();
            String s = "from Profesor";
            Query query = session.createQuery(s);
            list = query.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean update(Profesor obj){
        boolean b = false;
        try{
            init();
            session.update(obj);
            tx.commit();
            b = true;
        }catch(Exception e){
            rollback();
        
            
        }
        return b;
    }
    
    public Integer insert(Profesor obj){
        Integer b = -1;
        try{
            init();
            idProfesor = (Integer) session.save(obj);
            tx.commit();
            b = idProfesor;
        }catch(Exception e){
            rollback();
        
            
        }
        return b;
    }
    
    public boolean delete(Profesor obj){
        boolean b = false;
        try{
            init();
            session.delete(obj);
            tx.commit();
            b = true;
        }catch(Exception e){
            rollback();
        
            
        }
        return b;
    }
    
    public Profesor getByID(int id){
        Profesor p = null;
        List<Profesor> list = null;
        try{
            init();
            String s = "FROM Profesor WHERE id_profesor=" + id;
            Query query = session.createQuery(s);
            list = query.list();
            p = list.remove(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return p;
    }
    
    public List<Profesor> getByNameAppApm(String crit){
        List<Profesor> list = null;
        try{
            init();
            String s = crit;
            Query query = session.createQuery(s);
            list = query.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
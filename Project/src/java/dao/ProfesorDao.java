/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import util.HibernateUtil;
import modelo.sql.Profesor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author raul__000
 */
public class ProfesorDao {
    private  static Session session;
    private Transaction tx;
    private Integer idProfesor;
    
    private void init(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        tx = session.beginTransaction();
    }
            

    public ProfesorDao(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    

    public Profesor Verificar(Profesor profesor){
        session.getTransaction().begin();
        Profesor f=null;
        try{
     String hql= "from Profesor where s_rfc= '" +profesor.getSRfc()+"' and s_contrasenha= '"+profesor.getSContrasenha()+"'";
     Query query=session.createQuery(hql);
     if(!query.list().isEmpty()){
         f=(Profesor)query.list().get(0);
         
     }  
        }catch(Exception e){
            throw e;
        }
        session.getTransaction().commit();
     
     return f;
    }
   private void rollback(){
        if(tx != null){
            tx.rollback();
        }
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

    


   
    
}

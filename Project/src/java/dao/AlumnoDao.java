/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.sql.Alumno;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author raul__000
 */
public class AlumnoDao {
    private  static Session session;
    private Transaction tx;
    
    private void init(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        tx = session.beginTransaction();
    }
            

    public AlumnoDao(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    

    public Alumno Verificar(Alumno alumno){
        session.getTransaction().begin();
        Alumno f=null;
        try{
     String hql= "from Alumno where s_nombre= '" +alumno.getSNombre()+"' and s_contrasenha= '"+alumno.getSContrasenha()+"'";
     Query query=session.createQuery(hql);
     if(!query.list().isEmpty()){
         f=(Alumno)query.list().get(0);
         
     }  
        }catch(Exception e){
            throw e;
        }
        session.getTransaction().commit();
     
     return f;
    }
    
}

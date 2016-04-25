/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import org.hibernate.Session;

import logica.HibernateUtil;
import modelo.Profesor;
import org.hibernate.Query;
import org.hibernate.Transaction;
/**
 *
 * @author luis
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
     String hql= "from Profesor where s_correo= '" +profesor.getSCorreo()+"' and s_contrasenha= '"+profesor.getSContrasenha()+"'";
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
    
    public void indroducirProfesor(String nombre, String contrasena, String correo) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Profesor n = new Profesor();

        n.setSNombre(nombre);
        n.setSContrasenha(contrasena);
        n.setSCorreo(correo);

        try {
            session.beginTransaction();
            session.save(n);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error del dao de profesor");
            session.getTransaction().rollback();
        }
    }
}

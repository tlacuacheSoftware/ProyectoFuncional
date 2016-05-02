/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import org.hibernate.Session;

import logica.HibernateUtil;
import modelo.Alumno;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author luis
 */
public class AlumnoDao {
    
    private  static Session session;
    private Transaction tx;
     
    public AlumnoDao(){
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    /**
     * Metodo auxiliar para registrar a un alumno en la base de datos
     * @param nombre Nombre del alumno
     * @param contrasena Contraseña del alumno
     * @param correo Correo del alumno
     */
    public void introducirAlumno(String nombre, String contrasena, String correo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Alumno n = new Alumno();
        n.setSNombre(nombre);
        n.setSContrasenha(contrasena);
        n.setSCorreo(correo);

        try {
            session.beginTransaction();
            session.save(n);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error del dao de Alumno");
            session.getTransaction().rollback();
        }
    }
    public boolean Existe(String correo){
        session.getTransaction().begin();
        boolean f=false;
        try{
     String hql= "from Alumno where s_correo= '"+correo+"'";
     Query query=session.createQuery(hql);
     if(!query.list().isEmpty()){
         f=true;
         
     }  
        }catch(Exception e){
            throw e;
        }
        session.getTransaction().commit();
     
     return f;
    }
    public Alumno Verificar(Alumno alumno){
        session.getTransaction().begin();
        Alumno f=null;
        try{
     String hql= "from Alumno where s_correo= '" +alumno.getSCorreo()+"' and s_contrasenha= '"+alumno.getSContrasenha()+"'";
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

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
    
private  static Session session;
    private Transaction tx;
    private Integer idProfesor;
    
    private void init(){
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }
            

    public ProfesorDao(){
        session = HibernateUtil.getSessionFactory().openSession();
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

        session = HibernateUtil.getSessionFactory().openSession();

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
                session.getTransaction().rollback();
            
                
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
        session.getTransaction().begin();
        try{
//            init();
            String s = "FROM Profesor WHERE id_Profesor=" + id;
            Query query = session.createQuery(s);
            if(!query.list().isEmpty()){
                p=(Profesor)query.list().get(0);
             }  
            }catch(Exception e){
                throw e;
            }
            session.getTransaction().commit();
            System.out.println(p.getIdProfesor()+" "+p.getSNombre()+p.getSCorreo()+p.getSContrasenha());
            return p;
    }
    
//    public List<Profesor> getByNameAppApm(String crit){
//        List<Profesor> list = null;
//        try{
//            init();
//            String s = crit;
//            Query query = session.createQuery(s);
//            list = query.list();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return list;
//    }
    
//    public boolean actulizar(Profesor profesor){
//        boolean bolean;
//        Configuration configuracion = new Configuration();
//        SessionFactory sessionFactory = configuracion.configure().buildSessionFactory();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        
//        return false;
//    }
}
package Dao;
import modelo.Profesor;
import modelo.Alumno;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import logica.HibernateUtil;

/**
 * @author Raul Eduardo García García
 * Clase creada para manejar el login de la pagina.
 */
public class LoginDao {
    private  static Session session;
    private Transaction tx;
    
    public LoginDao(){
        session = HibernateUtil.getSessionFactory().openSession();
    }
    /**
     * Verificar si el alumno existe en la base de datos
     * @param alumno
     * @return 
     */
    public Alumno VerificarA(Alumno alumno){
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
    /**
     * Verificar si el profesor existe en la base de datos
     * @param profesor
     * @return 
     */
      public Profesor VerificarP(Profesor profesor){
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
}

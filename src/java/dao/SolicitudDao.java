
package dao;

import java.util.List;
import modelo.Solicitud;

/**
 * WRAPPER para DAO en la tabla solicitud.
 * @author esmeralda
 */
public class SolicitudDao {

    private DAO<Solicitud> dao;
    
    public SolicitudDao() {
        dao = new DAO("Solicitud", "id_solicitud");
    }
    
    public void insertar (Solicitud obj){
        try {
            dao.insertar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void actualizar (Solicitud obj){
        try {
            dao.actualizar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void borrar (Solicitud obj){
        try {
            dao.borrar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Solicitud obtenerPorID(int id){
        Solicitud obj;
        try{
            obj = dao.obtenerPorID(id);
        }catch(Exception e){
            throw e;
        }
        return obj;
    }
    
    public List<Solicitud> obtenerPorActividad(int id){
        List<Solicitud> list = null;
        try{
            list = dao.buscarPorAtributo("id_actividad", "" + id);
        }catch(Exception e){
            throw e;
        }
        return list;
    }
    
    public List<Solicitud> obtenerPorAlumno(int id){
        List<Solicitud> list = null;
        try{
            list = dao.buscarPorAtributo("id_alumno", "" + id);
        }catch(Exception e){
            throw e;
        }
        return list;
    }
    
    public List<Solicitud> obtenerPorProfesor(int id){
        List<Solicitud> list = null;
        try{
            list = dao.buscarPorAtributo("id_profesor", "" + id);
        }catch(Exception e){
            throw e;
        }
        return list;
    }
}
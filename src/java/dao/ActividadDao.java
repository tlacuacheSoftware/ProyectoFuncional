
package dao;

import java.util.List;
import modelo.Actividad;

/**
 * WRAPPER para DAO en la tabla actividad.
 * @author esmeralda
 */
public class ActividadDao {

    private DAO<Actividad> dao;
    
    public ActividadDao() {
        dao = new DAO("Actividad", "id_actividad");
    }
    
    public void insertar (Actividad obj){
        try {
            dao.insertar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void actualizar (Actividad obj){
        try {
            dao.actualizar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void borrar (Actividad obj){
        try {
            dao.borrar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Actividad obtenerPorID(int id){
        Actividad obj;
        try{
            obj = dao.obtenerPorID(id);
        }catch(Exception e){
            throw e;
        }
        return obj;
    }
    
    public List<Actividad> obtenerPorProfesor(int id){
        List<Actividad> list = null;
        try{
            list = dao.buscarPorAtributo("id_profesor", "" + id);
        }catch(Exception e){
            throw e;
        }
        return list;
    }
    
    public List<Actividad> obtenerPorArea(int id){
        List<Actividad> list = null;
        try{
            list = dao.buscarPorAtributo("id_area", "" + id);
        }catch(Exception e){
            throw e;
        }
        return list;
    }
    
    public List<Actividad> obtenerPorTipo(int id){
        List<Actividad> list = null;
        try{
            list = dao.buscarPorAtributo("id_tipo", "" + id);
        }catch(Exception e){
            throw e;
        }
        return list;
    }
    
    public List<Actividad> obtenerTodas(){
        List<Actividad> list = null;
        try{
            list = dao.getAll();
        }catch(Exception e){
            throw e;
        }
        return list;
    }
}
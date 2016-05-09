
package dao;

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
    
}
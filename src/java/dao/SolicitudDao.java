
package dao;

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
    
}
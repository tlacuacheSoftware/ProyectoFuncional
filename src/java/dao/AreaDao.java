
package dao;

import java.util.List;
import modelo.Area;

/**
 * WRAPPER para DAO en la tabla area.
 * @author esmeralda
 */
public class AreaDao {

    private DAO<Area> dao;
    
    public AreaDao() {
        dao = new DAO("Area", "id_area");
    }
    
    public void insertar (Area obj){
        try {
            dao.insertar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void actualizar (Area obj){
        try {
            dao.actualizar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void borrar (Area obj){
        try {
            dao.borrar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Area obtenerPorID(int id){
        Area obj;
        try{
            obj = dao.obtenerPorID(id);
        }catch(Exception e){
            throw e;
        }
        return obj;
    }
    
    public List<Area> obtenerPorArea(String area){
        List<Area> list = null;
        try{
            list = dao.buscarPorAtributo("s_area", area);
        }catch(Exception e){
            throw e;
        }
        return list;
    }
}
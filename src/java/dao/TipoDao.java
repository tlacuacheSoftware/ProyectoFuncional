
package dao;

import java.util.List;
import modelo.Tipo;

/**
 * WRAPPER para DAO en la tabla tipo.
 * @author esmeralda
 */
public class TipoDao {

    private DAO<Tipo> dao;
    
    public TipoDao() {
        dao = new DAO("Tipo", "id_tipo");
    }
    
    public void insertar (Tipo obj){
        try {
            dao.insertar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void actualizar (Tipo obj){
        try {
            dao.actualizar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void borrar (Tipo obj){
        try {
            dao.borrar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Tipo obtenerPorID(int id){
        Tipo obj;
        try{
            obj = dao.obtenerPorID(id);
        }catch(Exception e){
            throw e;
        }
        return obj;
    }
    
    public List<Tipo> obtenerPorTipo(String tipo){
        List<Tipo> list = null;
        try{
            list = dao.buscarPorAtributo("s_tipo", tipo);
        }catch(Exception e){
            throw e;
        }
        return list;
    }
}
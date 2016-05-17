
package dao;

import java.util.List;
import modelo.Profesor;

/**
 * WRAPPER para DAO en la tabla profesor.
 * @author esmeralda
 */
public class ProfesorDao {

    private DAO<Profesor> dao;
    
    public ProfesorDao() {
        dao = new DAO("Profesor", "id_profesor");
    }
    
    public void insertar (Profesor obj){
        try {
            dao.insertar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void actualizar (Profesor obj){
        try {
            dao.actualizar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void borrar (Profesor obj){
        try {
            dao.borrar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Profesor obtenerPorID(int id){
        Profesor obj;
        try{
            obj = dao.obtenerPorID(id);
        }catch(Exception e){
            throw e;
        }
        return obj;
    }
    
    public boolean existeCorreo(String correo){
        String[] atributos,valores;
        boolean existe;
        atributos = new String[1];
        valores = new String[1];
        atributos[0] = "s_correo";
        valores[0] = correo;
        try{
            existe = dao.verificarExistencia(atributos, valores);
        }catch(Exception e){
            throw e;
        }
        return existe;
    }
    
    public Profesor verificarProfesor(Profesor obj){
        String[] atributos,valores;
        List<Profesor> list;
        Profesor aux = null;
        atributos = new String[2];
        valores = new String[2];
        atributos[0] = "s_correo";
        valores[0] = obj.getSCorreo();
        atributos[1] = "s_contrasenha";
        valores[1] = obj.getSContrasenha();
        try{
            list = dao.buscar(atributos, valores);
            if(list != null){
                if(!list.isEmpty()){
                    aux = list.get(0);
                }
            }
        }catch(Exception e){
            throw e;
        }
        return aux;
    }
}
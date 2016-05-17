
package dao;

import java.util.List;
import modelo.Alumno;

/**
 * WRAPPER para DAO en la tabla alumno.
 * @author esmeralda
 */
public class AlumnoDao {

    private DAO<Alumno> dao;
    
    public AlumnoDao() {
        dao = new DAO("Alumno", "id_alumno");
    }
    
    public void insertar (Alumno obj){
        try {
            dao.insertar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void actualizar (Alumno obj){
        try {
            dao.actualizar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void borrar (Alumno obj){
        try {
            dao.borrar(obj);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Alumno obtenerPorID(int id){
        Alumno obj;
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
    
    public Alumno verificarAlumno(Alumno obj){
        String[] atributos,valores;
        List<Alumno> list;
        Alumno aux = null;
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

package dao;

import java.util.LinkedList;
import java.util.List;
import modelo.Alumno;
import modelo.Solicitud;

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
    
    public List<Alumno> recuperarNombres(List<Solicitud> lista){
        List<Alumno> resultado = new LinkedList<Alumno>();
        Solicitud temporal = new Solicitud();
        Alumno temp = new Alumno();
        int i = 0;
        int tamaño = lista.size();
//        resultado = aux(lista, temporal, temp);
        while(i < tamaño && lista.get(i)!= null ){
            temporal = lista.get(i);
            temp = temporal.getAlumno();
            System.out.println(temp.getSNombre());
            resultado.add(temp);
            System.out.println("Entre "+ i + " veces");
            i++;
        }
        return resultado;
    }
    
//    public List<Alumno> aux(List<Solicitud> list, Solicitud temporal, Alumno tempo){
//        System.out.println("Entre aquí otra vez");
//        List<Alumno> resultado = new LinkedList<>();
//        int i = 0;
//        int tamaño = list.size();
//        while(i<tamaño){
//            temporal = list.get(i);
//            //list.add(i, temporal);
//            tempo = temporal.getAlumno();
//            System.out.println(tempo.getSNombre());
//            resultado.add(tempo);
//            System.out.println("Entre "+ i + " veces");
//            i++;
//        }
//        
//        return resultado;
//    }
}

package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author esmeralda
 */
@ManagedBean
@RequestScoped
public class beanIndex {

    public static final String INDEX = "index";
    public static final String LOGIN = "login";
    public static final String REGISTRO_ALUMNO = "registroalumno";
    public static final String REGISTRO_PROFESOR = "registroprofesor";
    public static final String INICIO_ALUMNO = "inicioalumno";
    public static final String INICIO_PROFESOR = "inicioprofesor";
    public static final String MODIFICAR_PERFIL = "modificarperfil";
    public static final String NUEVA_PUBLICACION = "nuevapublicacion";
    public static final String MIS_ACTIVIDADES = "misactividades";
    public static final String VER_ACTIVIDADES = "veractividades";
    
    public String INDEX() {
        return INDEX;
    }

    public String LOGIN() {
        return LOGIN;
    }

    public String REGISTRO_ALUMNO() {
        return REGISTRO_ALUMNO;
    }

    public String REGISTRO_PROFESOR() {
        return REGISTRO_PROFESOR;
    }

    public String INICIO_ALUMNO() {
        return INICIO_ALUMNO;
    }

    public String INICIO_PROFESOR() {
        return INICIO_PROFESOR;
    }

    public String MODIFICAR_PERFIL() {
        return MODIFICAR_PERFIL;
    }
    
    public String NUEVA_PUBLICACION(){
        return NUEVA_PUBLICACION;
    }
    
    public String MIS_ACTIVIDADES(){
        return MIS_ACTIVIDADES;
    }
    
    public String VER_ACTIVIDADES(){
        return VER_ACTIVIDADES;
    }
}

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
    public static final String ACTUALIZAR_PUBLICACION = "actualizarpublicacion";
    public static final String BORRAR_PUBLICACION = "borrarpublicacion";
    public static final String MIS_ACTIVIDADES = "misactividades";
    public static final String VER_ACTIVIDADES = "veractividades";
    public static final String MI_SOLICITUD = "misolicitud";

    public static String INDEX() {
        return INDEX;
    }

    public static String LOGIN() {
        return LOGIN;
    }

    public static String REGISTRO_ALUMNO() {
        return REGISTRO_ALUMNO;
    }

    public static String REGISTRO_PROFESOR() {
        return REGISTRO_PROFESOR;
    }

    public static String INICIO_ALUMNO() {
        return INICIO_ALUMNO;
    }

    public static String INICIO_PROFESOR() {
        return INICIO_PROFESOR;
    }

    public static String MODIFICAR_PERFIL() {
        return MODIFICAR_PERFIL;
    }

    public static String NUEVA_PUBLICACION() {
        return NUEVA_PUBLICACION;
    }

    public static String ACTUALIZAR_PUBLICACION() {
        return ACTUALIZAR_PUBLICACION;
    }

    public static String BORRAR_PUBLICACION() {
        return BORRAR_PUBLICACION;
    }

    public static String MIS_ACTIVIDADES() {
        return MIS_ACTIVIDADES;
    }

    public static String VER_ACTIVIDADES() {
        return VER_ACTIVIDADES;
    }

    public static String MI_SOLICITUD() {
        return MI_SOLICITUD;
    }
    
    
}
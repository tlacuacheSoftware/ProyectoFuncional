
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
}
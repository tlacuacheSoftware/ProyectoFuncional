
package bean;

import dao.AlumnoDao;
import dao.ProfesorDao;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Alumno;
import modelo.Profesor;

/**
 *
 * @author esmeralda
 */
@ManagedBean
@RequestScoped
public class beanRegistro {
    
    public final static String LETRAS = "QWERTYUIOPADFGHJKLÑZXCVBNMqwertyuiopasdfghjklñzxcvbnm";
    public final static String NUMEROS = "1234567890";
    public final static String SIMBOLOS = "";
    public final static String DOMINIO = "@ciencias.unam.mx";
    
    private String nombre;
    private String contrasenha;
    private String contrasenha2;
    private String correo;
    private final AlumnoDao daoA;
    private final ProfesorDao daoP;
    
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;
    
    public beanRegistro() {
        daoA = new AlumnoDao();
        daoP = new ProfesorDao();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }
    
    public String registrarProfesor(){
        Profesor p;
        String errorCont,errorNombre,errorCorreo;
        errorCont = validarContrasenha(contrasenha, contrasenha2);
        errorNombre = validarNombre(nombre);
        errorCorreo = validarCorreo(correo, false);
        if(!errorCont.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorCont, null);
            faceContext.addMessage(null, message);
            faceContext.getExternalContext().getFlash().setKeepMessages(true);
        }else if(!errorNombre.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorNombre, null);
            faceContext.addMessage(null, message);
            faceContext.getExternalContext().getFlash().setKeepMessages(true);
        }else if(!errorCorreo.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorCorreo, null);
            faceContext.addMessage(null, message);
            faceContext.getExternalContext().getFlash().setKeepMessages(true);
        }else{
            p = new Profesor();
            p.setSContrasenha(contrasenha);
            p.setSNombre(nombre);
            p.setSCorreo(correo);
            try{
                daoP.insertar(p);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Cuenta creda correctamente.", null);
                faceContext.addMessage(null, message);
                faceContext.getExternalContext().getFlash().setKeepMessages(true);
                return beanIndex.INDEX;
            }catch(Exception e){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getLocalizedMessage(), null);
                faceContext.addMessage(null, message);
                faceContext.getExternalContext().getFlash().setKeepMessages(true);
            }
        }
        return beanIndex.REGISTRO_PROFESOR;
    }
    
    public String registrarAlumno(){
        Alumno a;
        String errorCont,errorNombre,errorCorreo;
        errorCont = validarContrasenha(contrasenha, contrasenha2);
        errorNombre = validarNombre(nombre);
        errorCorreo = validarCorreo(correo, true);
        if(!errorCont.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorCont, null);
            faceContext.addMessage(null, message);
            faceContext.getExternalContext().getFlash().setKeepMessages(true);
        }else if(!errorNombre.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorNombre, null);
            faceContext.addMessage(null, message);
            faceContext.getExternalContext().getFlash().setKeepMessages(true);
        }else if(!errorCorreo.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorCorreo, null);
            faceContext.addMessage(null, message);
            faceContext.getExternalContext().getFlash().setKeepMessages(true);
        }else{
            a = new Alumno();
            a.setSContrasenha(contrasenha);
            a.setSNombre(nombre);
            a.setSCorreo(correo);
            try{
                daoA.insertar(a);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Cuenta creda correctamente.", null);
                faceContext.addMessage(null, message);
                faceContext.getExternalContext().getFlash().setKeepMessages(true);
                return beanIndex.INDEX;
            }catch(Exception e){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getLocalizedMessage(), null);
                faceContext.addMessage(null, message);
                faceContext.getExternalContext().getFlash().setKeepMessages(true);
            }
        }
        return beanIndex.REGISTRO_ALUMNO;
    }
    
    public static String validarContrasenha(String c1, String c2){
        String aux;
        if(c1 == null || c2 == null){
            return "Contraseña vacia.";
        }else if(!c1.equals(c2)){
            return "Las contraseñas ingresadas son distintas.";
        }else if(c1.length() < 6 || c1.length() > 20){
            return "Las contraseña debe tener entre 6 y 20 caracteres.";
        }
        aux = borrar(c1, LETRAS);
        aux = borrar(aux, NUMEROS);
        if(!aux.equals("")){
            return "La contraseña solo debe contener caracteres alfanumericos.";
        }
        return "";
    }
    
    public static String validarNombre(String n){
        String aux;
        if(n == null){
            return "Nombre vacio.";
        }else if(n.length() < 3 || n.length() > 20){
            return "El nombre debe tener entre 3 y 20 caracteres.";
        }
        aux = borrar(n, LETRAS);
        if(!aux.equals("")){
            return "El nombre solo puede contener letras.";
        }
        return "";
    }
    
    public static String validarCorreo(String c, boolean tabla){
        AlumnoDao daoA;
        ProfesorDao daoP;
        String aux;
        if(c == null){
            return "Correo vacio.";
        }
        if(!c.endsWith(DOMINIO)){
            return "El correo debe de ser del dominio de @ciencias.unam.mx";
        }
        aux = c.substring(0,c.length()-DOMINIO.length());
        aux = borrar(aux, LETRAS);
        aux = borrar(aux, NUMEROS);
        if(!aux.equals("")){
            return "El correo contiene caracteres invalidos.";
        }
        if(tabla){
            daoA = new AlumnoDao();
            if(daoA.existeCorreo(c)){
                return "El correo ya existe en la base de datos.";
            }
        }else{
            daoP = new ProfesorDao();
            if(daoP.existeCorreo(c)){
                return "El correo ya existe en la base de datos.";
            }
        }
        return "";
    }
    
    public static String validarCorreoMod(String c, boolean tabla){
        ProfesorDao daoP;
        String aux;
        if(c == null){
            return "Correo vacio.";
        }
        if(!c.endsWith(DOMINIO)){
            return "El correo debe de ser del dominio de @ciencias.unam.mx";
        }
        aux = c.substring(0,c.length()-DOMINIO.length());
        aux = borrar(aux, LETRAS);
        aux = borrar(aux, NUMEROS);
        if(!aux.equals("")){
            return "El correo contiene caracteres invalidos.";
        }
        return "";
    }
    
    public static String borrar(String borrada, String b){
        String aux = new String(borrada);
        String sub;
        for(int i = 0; i < b.length(); i++){
            sub = b.substring(i, i+1);
            aux = aux.replace(sub, "");
        }
        return aux;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getContrasenha2() {
        return contrasenha2;
    }

    public void setContrasenha2(String contrasenha2) {
        this.contrasenha2 = contrasenha2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}

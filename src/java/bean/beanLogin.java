
package bean;

import dao.AlumnoDao;
import dao.ProfesorDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
public class beanLogin {
    
    private final AlumnoDao daoA;
    private final ProfesorDao daoP;
    
    private String correo;
    private String contrasenha;
    
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;
    
    public beanLogin(){
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        daoA = new AlumnoDao();
        daoP = new ProfesorDao();
    }
    
    public String login(){
        Alumno a1,a2;
        Profesor p1,p2;
        a1 = new Alumno();
        p1 = new Profesor();
        a1.setSCorreo(correo);
        a1.setSContrasenha(contrasenha);
        p1.setSCorreo(correo);
        p1.setSContrasenha(contrasenha);
        try{
            a2 = daoA.verificarAlumno(a1);
            p2 = daoP.verificarProfesor(p1);
            if(p2 != null){
                httpServletRequest.getSession().setAttribute("id", p2.getIdProfesor());
                httpServletRequest.getSession().setAttribute("sesionNombre", p2.getSNombre());
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Otorgado.", null);
                faceContext.addMessage(null, message);
                return beanIndex.INICIO_PROFESOR;
            }else if(a2 != null){
                httpServletRequest.getSession().setAttribute("id", a2.getIdAlumno());
                httpServletRequest.getSession().setAttribute("sesionNombre", a2.getSNombre());
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Acceso Otorgado.", null);
                faceContext.addMessage(null, message);
                return beanIndex.INICIO_ALUMNO;
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrecto.", null);
                faceContext.addMessage(null, message);
            }
        }catch(Exception e){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getLocalizedMessage(), null);
            faceContext.addMessage(null, message);
        }
        return beanIndex.LOGIN;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }
}

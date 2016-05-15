
package bean;

import dao.ProfesorDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Profesor;

/**
 *
 * @author esmeralda
 */
@ManagedBean
@RequestScoped
public class beanModificarPerfil {
    
    private final Profesor profesor;
    private final int id;
    private String nombre;
    private String correo;
    private String contrasenha1;
    private String contrasenha2;
    private final ProfesorDao dao;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;
    
    public beanModificarPerfil(){
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        nombre = httpServletRequest.getSession().getAttribute("sesionNombre").toString();
        dao = new ProfesorDao();
        id = (int)httpServletRequest.getSession().getAttribute("id");
        profesor = dao.obtenerPorID(id);     
        correo = profesor.getSCorreo();
    }
    
    public String modificarPerfil(){
        String errorNombre,errorCorreo,errorContr;
        try{
            errorNombre = beanRegistro.validarNombre(nombre);
            errorCorreo = beanRegistro.validarCorreo(correo, false);
            errorContr = beanRegistro.validarContrasenha(contrasenha1, contrasenha2);
            if(nombre != null){
                if(!nombre.equals("")){
                    if(errorNombre.equals("")){
                        profesor.setSNombre(nombre);
                        httpServletRequest.getSession().setAttribute("sessionSNombre", nombre);
                    }
                }
            }
            if(correo != null){
                if(!correo.equals("")){
                    if(errorCorreo.equals("")){
                        profesor.setSCorreo(correo);
                    }
                }

            }
            if(contrasenha1 != null && contrasenha2 != null){
                if(!contrasenha1.equals("") && contrasenha2.equals("")){
                    if(errorContr.equals("")){
                        profesor.setSContrasenha(contrasenha1);
                    }
                }
            }
            dao.actualizar(profesor);
        }catch(Exception e){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getLocalizedMessage(), null);
            faceContext.addMessage(null, message);
        }
        message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Informacion modificada correctamente", null);
        faceContext.addMessage(null, message);
        return beanIndex.MODIFICAR_PERFIL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenha1() {
        return contrasenha1;
    }

    public void setContrasenha1(String contrasenha1) {
        this.contrasenha1 = contrasenha1;
    }

    public String getContrasenha2() {
        return contrasenha2;
    }

    public void setContrasenha2(String contrasenha2) {
        this.contrasenha2 = contrasenha2;
    }
}

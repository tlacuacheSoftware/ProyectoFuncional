/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author raul__000
 */
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Managed Bean para manejar el cierre de sesión de la aplicación.
 */
@ManagedBean // LEER LA DOCUMENTACIÖN DE ESTA ANOTACIÓN: Permite dar de alta al bean en la aplicación
@RequestScoped // Sólo está disponible a partir de peticiones al bean
public class beanLogout {

    private String usuario; // Representa el nombre de usuario.
    private final HttpServletRequest httpServletRequest; // Obtiene información de todas las peticiones de usuario.
    private final FacesContext faceContext; // Obtiene información de la aplicación
    private FacesMessage message; // Permite el envio de mensajes entre el bean y la vista.

    /**
     * Constructor para inicializar los valores de faceContext y
     * httpServletRequest, además de la sesión de usuario.
     */
    public beanLogout() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        if (httpServletRequest.getSession().getAttribute("sessionSNombre") != null) {
            usuario = httpServletRequest.getSession().getAttribute("sessionSNombre").toString();
        }
    }

    /**
     * Método encargado de cerrar la sesión de la aplicación.
     *
     * @return El nombre de la vista que va a responder.
     */
    public String cerrarSession() {
        httpServletRequest.getSession().removeAttribute("sessionUsuario");
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Session cerrada correctamente", null);
        faceContext.addMessage(null, message);
        return "Index";
    }

    /**
     * Regresa el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param usuario El nombre de usuario a establecer.
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
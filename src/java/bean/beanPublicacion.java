
package bean;

import dao.ActividadDao;
import dao.AreaDao;
import dao.ProfesorDao;
import dao.SolicitudDao;
import dao.TipoDao;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Actividad;
import modelo.Area;
import modelo.Profesor;
import modelo.Solicitud;
import modelo.Tipo;

/**
 *
 * @author esmeralda
 */
@ManagedBean
@RequestScoped
public class beanPublicacion {
    
    private final int id;
    private int idActividad;
    private int idArea;
    private int idTipo;
    private int cupoMaximo;
    private String descripcion;
    private final ActividadDao dao;
    private final SolicitudDao solicitud;
    private final TipoDao daoT;
    private final AreaDao daoA;
    private final ProfesorDao daoP;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;
    
    public beanPublicacion() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        dao = new ActividadDao();
        solicitud = new SolicitudDao();
        daoA = new AreaDao();
        daoT = new TipoDao();
        daoP = new ProfesorDao();
        id = (int)httpServletRequest.getSession().getAttribute("id");
    }
    
    public String guardarPublicacion(){
        String error;
        Actividad actividad;
        Area area;
        Tipo tipo;
        Profesor profesor;
        error = checkCampos(true);
        try{
            if(error.equals("")){
                area = daoA.obtenerPorID(idArea);
                tipo = daoT.obtenerPorID(idTipo);
                profesor = daoP.obtenerPorID(id);
                actividad = new Actividad();
                actividad.setICupomaximo(cupoMaximo);
                actividad.setSDescripciom(descripcion);
                actividad.setArea(area);
                actividad.setProfesor(profesor);
                actividad.setTipo(tipo);
                dao.insertar(actividad);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Actividad publicada exitosamente.", null);
                faceContext.addMessage(null, message);
                return beanIndex.NUEVA_PUBLICACION;
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,error, null);
                faceContext.addMessage(null, message);
                return beanIndex.NUEVA_PUBLICACION;
            }
        }catch(Exception e){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getLocalizedMessage(), null);
            faceContext.addMessage(null, message);
            return beanIndex.NUEVA_PUBLICACION;
        }
    }
    
    public String actualizarPublicacion(){
        String error;
        Actividad actividad;
        Area area;
        Tipo tipo;
        Profesor profesor;
        error = checkCampos(false);
        try{
            if(error.equals("")){
                area = daoA.obtenerPorID(idArea);
                tipo = daoT.obtenerPorID(idTipo);
                profesor = daoP.obtenerPorID(id);
                actividad = new Actividad();
                actividad.setICupomaximo(cupoMaximo);
                actividad.setSDescripciom(descripcion);
                actividad.setArea(area);
                actividad.setProfesor(profesor);
                actividad.setTipo(tipo);
                dao.actualizar(actividad);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Actividad publicada exitosamente.", null);
                faceContext.addMessage(null, message);
                return beanIndex.NUEVA_PUBLICACION;
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,error, null);
                faceContext.addMessage(null, message);
                return beanIndex.NUEVA_PUBLICACION;
            }
        }catch(Exception e){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getLocalizedMessage(), null);
            faceContext.addMessage(null, message);
            return beanIndex.NUEVA_PUBLICACION;
        }
    }
    
    public String borrarPublicacion(){
        Actividad actividad;
        try{
            actividad = dao.obtenerPorID(idActividad);
            dao.borrar(actividad);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Actividad borrada exitosamente.", null);
            faceContext.addMessage(null, message);
            return beanIndex.NUEVA_PUBLICACION;
        }catch(Exception e){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getLocalizedMessage(), null);
            faceContext.addMessage(null, message);
            return beanIndex.NUEVA_PUBLICACION;
        }
    }
    
    public List<Actividad> mostrarPublicaciones(){
        List<Actividad> resultado;
        resultado = dao.obtenerPorProfesor(id);
        return resultado;
    }
    
    public List<Actividad> mostrarPublicacionesAlumno(){
        List<Actividad> resultado;
        resultado = dao.obtenerTodas();
        return resultado;
    }

    private String checkCampos(boolean flag){
        try{
            if(cupoMaximo < 1){
                return "Cupo maximo no valido.";
            }
            if(descripcion.isEmpty()){
                if(flag){
                    return "Descripcion vacia.";
                }
            }
            if(idArea < 1){
                return "Area no valida.";
            }
            if(idTipo < 1){
                return "Tipo no valido.";
            }
        }catch(Exception e){
            if(flag){
                return "Campo vacio.";
            }
        }
        return "";
    }
    
    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int disponibles(int id){
        List<Solicitud> resultado;
        int respuesta;
        resultado = solicitud.obtenerPorActividad(id);
        if(resultado == null){
            respuesta = 0;
        }else{
            
            respuesta = resultado.size();
        }
        
        return respuesta;
    }
    
}

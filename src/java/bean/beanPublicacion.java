
package bean;

import dao.ActividadDao;
import dao.AreaDao;
import dao.ProfesorDao;
import dao.SolicitudDao;
import dao.TipoDao;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import modelo.Actividad;
import modelo.Area;
import modelo.Profesor;
import modelo.Solicitud;
import modelo.Tipo;
import sun.font.TrueTypeFont;

/**
 *
 * @author esmeralda
 */
@ManagedBean
@RequestScoped
public class beanPublicacion {
    
    private final String CUPO_INVALIDO = "No se ha definido el cupo.";
    
    private final int id;
    private Actividad actividad;
    //@ManagedProperty(value = "#{area}")
    private Area area;
    //@ManagedProperty(value = "#{tipo}")
    private Tipo tipo;
    private List<Area> areas;
    private List<Tipo> tipos;
    private int cupoMaximo;
    private String cupo;
    private String mensajeCupo;
    private String descripcion;
    private final ActividadDao dao;
    private final SolicitudDao daoS;
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
        daoA = new AreaDao();
        daoT = new TipoDao();
        daoP = new ProfesorDao();
        daoS = new SolicitudDao();
        id = (int)httpServletRequest.getSession().getAttribute("id");
        cupo = CUPO_INVALIDO;
        areas = mostrarAreas();
        tipos = mostrarTipos();
        definirActividad();
    }
    
    private void definirActividad(){
        int idA;
        try{
            if(actividad == null){
                idA = (int)httpServletRequest.getSession().getAttribute("sesionActividad");
                actividad = dao.obtenerPorID(idA);
                tipo = actividad.getTipo();
                area = actividad.getArea();
                descripcion = actividad.getSDescripciom();
                cupoMaximo = actividad.getICupomaximo();
                cupo = "" + cupoMaximo;
            }
        }catch(Exception e){
        
        }
    }
    
    public String guardarPublicacion(){
        String error;
        Profesor profesor;
        error = checkCampos(true);
        try{
            if(error.equals("")){

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
                faceContext.getExternalContext().getFlash().setKeepMessages(true);
                return beanIndex.NUEVA_PUBLICACION;
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,error, null);
                faceContext.addMessage(null, message);
                faceContext.getExternalContext().getFlash().setKeepMessages(true);
                return beanIndex.NUEVA_PUBLICACION;
            }
        }catch(Exception e){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getLocalizedMessage(), null);
            faceContext.addMessage(null, message);
            faceContext.getExternalContext().getFlash().setKeepMessages(true);
            return beanIndex.NUEVA_PUBLICACION;
        }
    }
    
    public String actualizarPublicacion(){
        String error;
        Profesor profesor;
        error = checkCampos(false);
        try{
            if(error.equals("")){
                profesor = daoP.obtenerPorID(id);
                actividad.setICupomaximo(cupoMaximo);
                actividad.setSDescripciom(descripcion);
                if(area != null){
                    actividad.setArea(area);
                }
                if(tipo != null){
                    actividad.setTipo(tipo);
                }
                actividad.setProfesor(profesor);
                dao.actualizar(actividad);
                message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Actividad actualizada exitosamente.", null);
                faceContext.addMessage(null, message);
                faceContext.getExternalContext().getFlash().setKeepMessages(true);
                return beanIndex.ACTUALIZAR_PUBLICACION;
            }else{
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,error, null);
                faceContext.addMessage(null, message);
                faceContext.getExternalContext().getFlash().setKeepMessages(true);
                return beanIndex.ACTUALIZAR_PUBLICACION;
            }
        }catch(Exception e){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getLocalizedMessage(), null);
            faceContext.addMessage(null, message);
            faceContext.getExternalContext().getFlash().setKeepMessages(true);
            return beanIndex.ACTUALIZAR_PUBLICACION;
        }
    }
    
    public String borrarPublicacion(){
        try{
            dao.borrar(actividad);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Actividad borrada exitosamente.", null);
            faceContext.addMessage(null, message);
            faceContext.getExternalContext().getFlash().setKeepMessages(true);
            return beanIndex.BORRAR_PUBLICACION;
        }catch(Exception e){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getLocalizedMessage(), null);
            faceContext.addMessage(null, message);
            faceContext.getExternalContext().getFlash().setKeepMessages(true);
            return beanIndex.BORRAR_PUBLICACION;
        }
    }
    
    public List<Actividad> mostrarPublicacionesProfesor(){
        List<Actividad> resultado;
        try{
            resultado = dao.obtenerPorProfesor(id);
        }catch(Exception e){
            resultado = new LinkedList<>();
        }
        return resultado;
        
    }
    
    public List<Actividad> mostrarPublicacionesAlumno(){
        List<Actividad> resultado;
        try{
            resultado = dao.obtenerLista();
        }catch(Exception e){
            resultado = new LinkedList<>();
        }
        return resultado;
    }

    private List<Area> mostrarAreas(){
        List<Area> list;
        try{
            list = daoA.obtenerLista();
        }catch(Exception e){
            list = new LinkedList<>();
        }
        return list;
    }
    
    private List<Tipo> mostrarTipos(){
        List<Tipo> list;
        try{
            list = daoT.obtenerLista();
        }catch(Exception e){
            list = new LinkedList<>();
        }
        return list;
    }
    
    public void revisarCupo(){
        
    }
    
    private String checkCampos(boolean flag){
        boolean c = false;
        try{
            cupoMaximo =  Integer.parseInt(cupo);
            c = true;
            if(cupoMaximo < 1){
                return "Cupo maximo no valido.";
            }
            if(descripcion.isEmpty()){
                return "Descripcion vacia.";
            }
            if(area == null){
                if(flag){
                    return "Area no valida.";
                }
            }
            if(tipo == null){
                if(flag){
                    return "Tipo no valido.";
                }
            }
        }catch(Exception e){
            if(flag){
                return "Campo vacio.";
            }else if(c){
                return "El cupo debe ser un numero entre 2 y 10. (Inclusivo)";
            }
        }
        return "";
    }
    
    public String definirActividad(Actividad actividad,boolean actualizar){
        if(actividad == null){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Actividad invalida.", null);
            faceContext.addMessage(null, message);
            faceContext.getExternalContext().getFlash().setKeepMessages(true);
            return beanIndex.MIS_ACTIVIDADES;
        }else{
            httpServletRequest.getSession().setAttribute("sesionActividad", actividad.getIdActividad());
            if(actualizar){
                return beanIndex.ACTUALIZAR_PUBLICACION;
            }else{
                return beanIndex.BORRAR_PUBLICACION;
            }
        }
    }
    
    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public String getTipoSeleccionado(){
        definirActividad();
        if(tipo == null){
            return "No se ha seleccionado tipo.";
        }else{
            return tipo.toString();
        }
    }
    
    public String getAreaSeleccionada(){
        definirActividad();
        if(area == null){
            return "No se ha seleccionado area.";
        }else{
            return area.toString();
        }
    }
    
    public void listenerArea(ValueChangeEvent e){
        Area aux;
        try{
            String a = e.getNewValue().toString();
            for(int i = 0; i < areas.size(); i++){
                aux = areas.get(id);
                if(aux.getSArea().equals(a)){
                    area = aux;
                }
            }
        }catch(Exception ex){
        
        }
    }
    
    public void listenerTipo(ValueChangeEvent e){
        Tipo aux;
        try{
            String a = e.getNewValue().toString();
            for(int i = 0; i < tipos.size(); i++){
                aux = tipos.get(id);
                if(aux.getSTipo().equals(a)){
                    tipo = aux;
                }
            }
        }catch(Exception ex){
        
        }
    }
    
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        if(area != null){
            this.area = area;
        }
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        if(tipo != null){
            this.tipo = tipo;
        }
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

    public String getCupo() {
        return cupo;
    }

    public void setCupo(String cupo) {
        this.cupo = cupo;
    }

    public String getMensajeCupo() {
        return mensajeCupo;
    }

    public void setMensajeCupo(String mensajeCupo) {
        this.mensajeCupo = mensajeCupo;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }
    
    public int disponibles(int id){
        List<Solicitud> resultado;
        int respuesta;
        resultado = daoS.obtenerPorActividad(id);
        if(resultado == null){
            respuesta = 0;
        }else{
            
            respuesta = resultado.size();
        }
        
        return respuesta;
    }
}

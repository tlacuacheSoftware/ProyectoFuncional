/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ActividadDao;
import dao.AlumnoDao;
import dao.ProfesorDao;
import dao.SolicitudDao;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Actividad;
import modelo.Alumno;
import modelo.Profesor;
import modelo.Solicitud;

/**
 *
 * @author Rodrigo_Rivera
 */
@ManagedBean
@RequestScoped
public class beanSolicitar {

    private final int id;
    private final SolicitudDao daoSolicitud;
    private final ActividadDao daoActividad;
    private final AlumnoDao daoAlumno;
    private final ProfesorDao daoProfesor;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;

    public beanSolicitar() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        daoSolicitud = new SolicitudDao();
        daoActividad = new ActividadDao();
        daoAlumno = new AlumnoDao();
        daoProfesor = new ProfesorDao();
        id = (int) httpServletRequest.getSession().getAttribute("id");
    }

    public String guardarSolicitud(int id_Actividad, int id_Profesor, int id_Alumno) {

        Actividad actividad;
        Profesor profesor;
        Alumno alumno;
        Solicitud solicitud;

        // obtencion del ojeto mediante el id
        actividad = daoActividad.obtenerPorID(id_Actividad);
        profesor = daoProfesor.obtenerPorID(id_Profesor);
        alumno = daoAlumno.obtenerPorID(id_Alumno);

        if (buscar(id_Alumno)) {// si ya esta inscrito

            //creacion del objeto
            solicitud = new Solicitud();
            // llenar los parametros
            solicitud.setActividad(actividad);
            solicitud.setProfesor(profesor);
            solicitud.setAlumno(alumno);
            //insetar
            daoSolicitud.insertar(solicitud);

            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La solicitud fue aprobada exitosamente.", null);
            faceContext.addMessage(null, message);
            return beanIndex.INICIO_ALUMNO; // me regresa al home 
        }else{
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solo te puedes incribir a una Modalidad.", null);
            
            faceContext.addMessage(null, message);
            return beanIndex.VER_ACTIVIDADES;
        }
        
    }

    /**
     *
     * @param id_Alumno
     * @return true si si ya esta inscrito
     */
    public boolean buscar(int id_Alumno) {
        SolicitudDao daoSolicitudDao;

        daoSolicitudDao = new SolicitudDao();
        // guardamos la la lista de los alumnos que estas en ese curso
        List<Solicitud> lis = daoSolicitudDao.obtenerPorAlumno(id_Alumno);
        return lis == null;
    }
    
    public Solicitud buscarIdPorIdAlumno(int id_Alumno){
        Solicitud resultado;
        resultado = daoSolicitud.obtenerPorAlumno(id_Alumno).get(0);
        return resultado;
    }
    
    public List<Solicitud> mostrarSolicitud(){
        List<Solicitud> resultado;
        resultado = daoSolicitud.obtenerPorAlumno(id);
        return resultado;
    }
    
    public String eliminarSolicitud(int id){
        daoSolicitud.borrar(daoSolicitud.obtenerPorID(id));
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud eliminada correctamente", null);
        faceContext.addMessage(null, message);
        return beanIndex.MIS_ACTIVIDADES;
    }
    
    public String eliminarSolicitudA(int id){
        daoSolicitud.borrar(daoSolicitud.obtenerPorID(id));
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud eliminada correctamente", null);
        faceContext.addMessage(null, message);
        return beanIndex.INICIO_ALUMNO;
    }
    
    public List<Solicitud> mostrarSolicitudP(int idPublicacion){
        List<Solicitud> solicitudes;
        solicitudes = daoSolicitud.obtenerPorActividad(idPublicacion);
        return solicitudes;
        
    }

}
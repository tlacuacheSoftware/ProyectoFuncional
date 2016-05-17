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
        id = (int)httpServletRequest.getSession().getAttribute("id");
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
        
        //creacion del objeto
        solicitud = new Solicitud();
        // llenar los parametros
        solicitud.setActividad(actividad);
        solicitud.setProfesor(profesor);
        solicitud.setAlumno(alumno);
        //insetar
        daoSolicitud.insertar(solicitud);
        
        message = new FacesMessage(FacesMessage.SEVERITY_INFO,"La solicitud fue aprobada exitosamente.", null);
                faceContext.addMessage(null, message);
                return beanIndex.INICIO_ALUMNO; // me regresa al home 
    }

}

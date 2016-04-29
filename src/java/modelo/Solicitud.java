package modelo;
// Generated 23/04/2016 11:31:45 PM by Hibernate Tools 4.3.1



/**
 * Solicitud generated by hbm2java
 */
public class Solicitud  implements java.io.Serializable {


     private int idSolicitud;
     private Actividad actividad;
     private Alumno alumno;
     private Profesor profesor;

    public Solicitud() {
    }

	
    public Solicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    public Solicitud(int idSolicitud, Actividad actividad, Alumno alumno, Profesor profesor) {
       this.idSolicitud = idSolicitud;
       this.actividad = actividad;
       this.alumno = alumno;
       this.profesor = profesor;
    }
   
    public int getIdSolicitud() {
        return this.idSolicitud;
    }
    
    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }
    public Actividad getActividad() {
        return this.actividad;
    }
    
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
    public Alumno getAlumno() {
        return this.alumno;
    }
    
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    public Profesor getProfesor() {
        return this.profesor;
    }
    
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }




}



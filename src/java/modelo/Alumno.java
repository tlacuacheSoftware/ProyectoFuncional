package modelo;
// Generated 23/04/2016 11:31:45 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Alumno generated by hbm2java
 */
public class Alumno  implements java.io.Serializable {


     private int idAlumno;
     private String SNombre;
     private String SContrasenha;
     private String SCorreo;
     private Set<Solicitud> solicituds = new HashSet<Solicitud>(0);

    public Alumno() {
    }

	
    public Alumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    public Alumno(int idAlumno, String SNombre, String SContrasenha, String SCorreo, Set<Solicitud> solicituds) {
       this.idAlumno = idAlumno;
       this.SNombre = SNombre;
       this.SContrasenha = SContrasenha;
       this.SCorreo = SCorreo;
       this.solicituds = solicituds;
    }
   
    public int getIdAlumno() {
        return this.idAlumno;
    }
    
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }
    public String getSNombre() {
        return this.SNombre;
    }
    
    public void setSNombre(String SNombre) {
        this.SNombre = SNombre;
    }
    public String getSContrasenha() {
        return this.SContrasenha;
    }
    
    public void setSContrasenha(String SContrasenha) {
        this.SContrasenha = SContrasenha;
    }
    public String getSCorreo() {
        return this.SCorreo;
    }
    
    public void setSCorreo(String SCorreo) {
        this.SCorreo = SCorreo;
    }
    public Set<Solicitud> getSolicituds() {
        return this.solicituds;
    }
    
    public void setSolicituds(Set<Solicitud> solicituds) {
        this.solicituds = solicituds;
    }




}



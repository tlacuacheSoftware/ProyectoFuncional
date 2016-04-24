package modelo;
// Generated 23/04/2016 12:26:03 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Actividad generated by hbm2java
 */
public class Actividad  implements java.io.Serializable {


     private int idActividad;
     private Area area;
     private Profesor profesor;
     private Tipo tipo;
     private Integer ICupomaximo;
     private String ADescripciom;
     private Set<Solicitud> solicituds = new HashSet<Solicitud>(0);

    public Actividad() {
    }

	
    public Actividad(int idActividad) {
        this.idActividad = idActividad;
    }
    public Actividad(int idActividad, Area area, Profesor profesor, Tipo tipo, Integer ICupomaximo, String ADescripciom, Set<Solicitud> solicituds) {
       this.idActividad = idActividad;
       this.area = area;
       this.profesor = profesor;
       this.tipo = tipo;
       this.ICupomaximo = ICupomaximo;
       this.ADescripciom = ADescripciom;
       this.solicituds = solicituds;
    }
   
    public int getIdActividad() {
        return this.idActividad;
    }
    
    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }
    public Area getArea() {
        return this.area;
    }
    
    public void setArea(Area area) {
        this.area = area;
    }
    public Profesor getProfesor() {
        return this.profesor;
    }
    
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    public Tipo getTipo() {
        return this.tipo;
    }
    
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    public Integer getICupomaximo() {
        return this.ICupomaximo;
    }
    
    public void setICupomaximo(Integer ICupomaximo) {
        this.ICupomaximo = ICupomaximo;
    }
    public String getADescripciom() {
        return this.ADescripciom;
    }
    
    public void setADescripciom(String ADescripciom) {
        this.ADescripciom = ADescripciom;
    }
    public Set<Solicitud> getSolicituds() {
        return this.solicituds;
    }
    
    public void setSolicituds(Set<Solicitud> solicituds) {
        this.solicituds = solicituds;
    }




}


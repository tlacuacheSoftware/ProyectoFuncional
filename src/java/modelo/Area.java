package modelo;
// Generated 23/04/2016 11:31:45 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Area generated by hbm2java
 */
public class Area  implements java.io.Serializable {


     private int idArea;
     private String SArea;
     private Set<Actividad> actividads = new HashSet<Actividad>(0);

    public Area() {
    }

	
    public Area(int idArea) {
        this.idArea = idArea;
    }
    public Area(int idArea, String SArea, Set<Actividad> actividads) {
       this.idArea = idArea;
       this.SArea = SArea;
       this.actividads = actividads;
    }
   
    public int getIdArea() {
        return this.idArea;
    }
    
    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
    public String getSArea() {
        return this.SArea;
    }
    
    public void setSArea(String SArea) {
        this.SArea = SArea;
    }
    public Set<Actividad> getActividads() {
        return this.actividads;
    }
    
    public void setActividads(Set<Actividad> actividads) {
        this.actividads = actividads;
    }




}



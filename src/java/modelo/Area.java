package modelo;
// Generated May 8, 2016 10:24:12 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Area generated by hbm2java
 */
public class Area  implements java.io.Serializable {

     private int idArea;
     private String SArea;
     private Set actividads = new HashSet(0);

    public Area() {
    }

	
    public Area(int idArea) {
        this.idArea = idArea;
    }
    public Area(int idArea, String SArea, Set actividads) {
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
    public Set getActividads() {
        return this.actividads;
    }
    
    public void setActividads(Set actividads) {
        this.actividads = actividads;
    }

     @Override
    public String toString (){
        return SArea;
    }

}
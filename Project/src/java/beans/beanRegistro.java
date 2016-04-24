/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import dao.ProfesorDao;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.faces.context.FacesContext;
import modelo.sql.Actividad;
import modelo.sql.Profesor;
import modelo.sql.Solicitud;
/**
 *
 * @author raul__000
 */
@ManagedBean
@SessionScoped
public class beanRegistro implements Serializable{

    public String getSRfc() {
        return SRfc;
    }

    public void setSRfc(String SRfc) {
        this.SRfc = SRfc;
    }

    public String getSNombre() {
        return SNombre;
    }

    public void setSNombre(String SNombre) {
        this.SNombre = SNombre;
    }

    public String getSAppaterno() {
        return SAppaterno;
    }

    public void setSAppaterno(String SAppaterno) {
        this.SAppaterno = SAppaterno;
    }

    public String getSApmaterno() {
        return SApmaterno;
    }

    public void setSApmaterno(String SApmaterno) {
        this.SApmaterno = SApmaterno;
    }

    public String getSContrasenha() {
        return SContrasenha;
    }

    public void setSContrasenha(String SContrasenha) {
        this.SContrasenha = SContrasenha;
    }

    public String getSCorreo() {
        return SCorreo;
    }

    public void setSCorreo(String SCorreo) {
        this.SCorreo = SCorreo;
    }

    public Set<Solicitud> getSolicituds() {
        return solicituds;
    }

    public void setSolicituds(Set<Solicitud> solicituds) {
        this.solicituds = solicituds;
    }

    public Set<Actividad> getActividads() {
        return actividads;
    }

    public void setActividads(Set<Actividad> actividads) {
        this.actividads = actividads;
    }
    private Profesor profesor;

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    private String SRfc;
     private String SNombre;
     private String SAppaterno;
     private String SApmaterno;
     private String SContrasenha;
     private String SCorreo;
     private Set<Solicitud> solicituds = new HashSet<Solicitud>(0);
     private Set<Actividad> actividads = new HashSet<Actividad>(0);
    public String registrar(){
        this.profesor=new Profesor();
        profesor.setSNombre(SNombre);
        profesor.setSApmaterno(SApmaterno);
        profesor.setSAppaterno(SAppaterno);
        profesor.setSContrasenha(SContrasenha);
        profesor.getSRfc();
        profesor.setSCorreo(SCorreo);
        ProfesorDao prof=new ProfesorDao();
        prof.insert(this.profesor);
        return "Exito";
    }
}

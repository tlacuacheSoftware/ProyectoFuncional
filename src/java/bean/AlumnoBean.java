/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import Dao.AlumnoDao;

/**
 *
 * @author luis
 */
@ManagedBean
@ViewScoped
public class AlumnoBean {
     private int idAlumno;
     private String ANombre;
     private String AContrasenha;
     private String ACorreo;
     private AlumnoDao c;

    public AlumnoBean() {
        c = new AlumnoDao();
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getANombre() {
        return ANombre;
    }

    public void setANombre(String ANombre) {
        this.ANombre = ANombre;
    }

    public String getAContrasenha() {
        return AContrasenha;
    }

    public void setAContrasenha(String AContrasenha) {
        this.AContrasenha = AContrasenha;
    }

    public String getACorreo() {
        return ACorreo;
    }

    public void setACorreo(String ACorreo) {
        this.ACorreo = ACorreo;
    }

    public String guardarAlumno(){
        c.indroducirAlumno(ANombre, AContrasenha, ACorreo);
        return "index";
    }

}

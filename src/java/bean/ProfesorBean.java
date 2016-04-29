/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import Dao.ProfesorDao;

/**
 *
 * @author luis
 */
@ManagedBean
@ViewScoped
public class ProfesorBean {

    private int idProfesor;
     private String ANombre;
     private String AContrasenha;
     private String ACorreo;
     private ProfesorDao c;

    public ProfesorBean() {
        c = new ProfesorDao();
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdAlumno(int idProfesor) {
        this.idProfesor = idProfesor;
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

    public String guardarProfesor(){
        c.indroducirProfesor(ANombre, AContrasenha, ACorreo);
        return "Index";
    }
    
}

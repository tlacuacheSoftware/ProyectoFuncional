/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import Dao.ProfesorDao;
import modelo.Profesor;

/**
 *
 * @author luis
 */
@ManagedBean
@ViewScoped
public class modificarProfesor {
    
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    private FacesMessage message;
    
     private int idProfesor;
     private String PNombre;
     private String PContrasenha;
     private String PCorreo;
     private final ProfesorDao dao;
     private Profesor profesor;
     
    public modificarProfesor() {
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        dao = new ProfesorDao();
        defineProfesor();
    }

    private void defineProfesor(){
        String id;
        id = httpServletRequest.getParameter("idProfesor");
        if(id == null){
            idProfesor = 1;
        }else{
            idProfesor = Integer.parseInt(id);
        }
        profesor = dao.getByID(idProfesor);
        httpServletRequest.setAttribute("idProfesor", idProfesor);
        PNombre = profesor.getSNombre();
        PCorreo = profesor.getSCorreo();
        PContrasenha = profesor.getSContrasenha();
    }
    
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    
    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getPNombre() {
        return PNombre;
    }

    public void setPNombre(String PNombre) {
        this.PNombre = PNombre;
    }

    public String getPContrasenha() {
        return PContrasenha;
    }

    public void setPContrasenha(String PContrasenha) {
        this.PContrasenha = PContrasenha;
    }

    public String getPCorreo() {
        return PCorreo;
    }

    public void setPCorreo(String PCorreo) {
        this.PCorreo = PCorreo;
    }

    public String modificarProfesor(){
        Profesor p;
        p = new Profesor();
        p.setIdProfesor(profesor.getIdProfesor());
        
        try{
            checkNombre();
            checkCorreo();
            checkContrasenha();
            p.setSNombre(PNombre);
            p.setSCorreo(PCorreo);
            p.setSContrasenha(PContrasenha);
            dao.update(p);
        }catch(Exception e){    
            return "modificar";
        }
        
        return "index";
    }
    
    private void checkNombre(){
        if(PNombre == null || PNombre.equals("")){
            throw new NullPointerException("El nombre esta vacio.");
        }
    }
    
    private void checkCorreo(){
        if(PCorreo == null || PCorreo.equals("")){
            throw new NullPointerException("El correo esta vacio.");
        }
    }

    private void checkContrasenha(){
        if(PContrasenha == null || PContrasenha.equals("")){
            throw new NullPointerException("La contraseña esta vacia.");
        }
    }
}

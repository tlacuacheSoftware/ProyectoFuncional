/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;

import Dao.AlumnoDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author luis
 */
@ManagedBean
@RequestScoped
public class AlumnoBean {
     private int idAlumno;
     private String ANombre;
     private String AContrasenha;
     private String ACorreo;
     private AlumnoDao c;
     private final FacesContext faceContext;
     private FacesMessage message;
     private final HttpServletRequest httpServletRequest;
    public AlumnoBean() {
        c = new AlumnoDao();
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
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
        String cadena1 = checaNombre();
        String cadena2 = checaCorreo();
        String cadena3 = checaContraseña();
        if("Index".equals(cadena1) && "Index".equals(cadena2) && "Index".equals(cadena3)){
            c.introducirAlumno(ANombre, AContrasenha, ACorreo);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Cuenta creda correctamente", null);
            faceContext.addMessage(null, message);
            return "Index";
        }else{
            return "RP";
        }
    }
    
    private String checaNombre(){
        if(ANombre == null || ANombre.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nombre no válido", null);
            faceContext.addMessage(null, message);
            return "RP";
        }else{
            if(!revisaNombre()){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El nombre sólo puede contener caracteres", null);
                faceContext.addMessage(null, message);
                return "RP";
            }else{
                if(ANombre.length()<4 || ANombre.length()>20){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El nombre debe tener entre 4 y 20 caracteres", null);
                    faceContext.addMessage(null, message);
                    return "RP";
                }else{
                    return "Index";
                }
            }
        }
    }
    
    private boolean revisaNombre(){
        boolean resultado = true;
        String alfa = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        for(int i = 0; i < ANombre.length(); i++){
            String temp = ""+ANombre.charAt(i);
            if(!alfa.contains(temp)){
                resultado = false;
            }
        }
        return resultado;
    }
    
    private String checaCorreo(){
        if(ACorreo == null || ACorreo.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Correo no válido", null);
            faceContext.addMessage(null, message);
            return "RP";
            }else{
                if(!revisaCorreo()){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"El correo debe de ser del dominio de Ciencias", null);
                    faceContext.addMessage(null, message);
                    return "RP";
                }else{
                    if(correoExiste()){
                        return "RP";
                    }else{
                        return "Index";
                    }
                }
        }
    }
    
    private boolean revisaCorreo(){
        boolean resultado = true;
        String prueba = "@ciencias.unam.mx";
        if(!ACorreo.contains(prueba)){
            resultado = false;
        }
        return resultado;
    }
    
    private boolean correoExiste(){
        boolean resultado = false;
        AlumnoDao prof = new AlumnoDao();
        if(prof.Existe(ACorreo)){
            resultado = true;
        }
        
        return resultado;
    }
    
    private String checaContraseña(){
        if(AContrasenha == null || AContrasenha.equals("")){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Contraseña no válida", null);
            faceContext.addMessage(null, message);
        return "RP";
        }else{
            if(AContrasenha.length() < 6 || AContrasenha.length() > 20){
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La contraseña debe tener al menos 6 caracteres y máximo 20", null);
                faceContext.addMessage(null, message);
                return "RP"   ;
            }else{
            return "Index";
            }
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;

import dao.AlumnoDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import modelo.Alumno;

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
     private String AContrasenha2;
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

    public String getAContrasenha2() {
        return AContrasenha2;
    }

    public void setAContrasenha2(String AContrasenha2) {
        this.AContrasenha2 = AContrasenha2;
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
        Alumno a=new Alumno();
        a.setSNombre(ANombre);
        a.setSCorreo(ACorreo);
        a.setSContrasenha(AContrasenha);
        if("index".equals(cadena1) && "index".equals(cadena2) && "index".equals(cadena3)){
            c.insertar(a);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Cuenta creda correctamente", null);
            faceContext.addMessage(null, message);
            return "index";
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
                    return "index";
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
                        return "index";
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
        if(prof.existeCorreo(ACorreo)){
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
                if(!AContrasenha.equals(AContrasenha2)){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Las contraseñas no coinciden", null);
                    faceContext.addMessage(null, message);
                    return "RP";
                }else{
                    return "index";
                }
            }
        }
    }
}
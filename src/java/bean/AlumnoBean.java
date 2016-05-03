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
        AlumnoDao prof=new AlumnoDao();
        int d=0;
        String Z="AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        for(int i=0;i<Z.length();i++){
            for(int j=0;j<ANombre.length();j++){
                if(ANombre.charAt(j)==Z.charAt(i)){
                    d++;
                }
            }
        }
        if(d!=ANombre.length()){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Nombre no valido", null);
            faceContext.addMessage(null, message);
            return "RP";
        }else{
            if(ANombre==null || "".equals(ANombre)){
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Introduce un nombre", null);
            faceContext.addMessage(null, message);                
                return "RP";
            }else{
                if(AContrasenha==null || "".equals(AContrasenha)){
                    message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Introduce una contraseña", null);
                    faceContext.addMessage(null, message);
                    return "RP";
                }else{
                    if(ACorreo==null || "".equals(ACorreo)){
                        message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Introduce un correo", null);
                        faceContext.addMessage(null, message);
                        return "RP";
                    }else{
                        if(prof.Existe(ACorreo)){
                            message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Correo ya registrado", null);
                            faceContext.addMessage(null, message);
                            return "RP";
                        }else{
                            if(AContrasenha.length()<6){
                                message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La contraseña debe tener al menos 6 caaracteres", null);
                                faceContext.addMessage(null, message);
                                return "RP";
                            }
                        }
                        
    }

}
            }
        }
        c.introducirAlumno(ANombre, AContrasenha, ACorreo);
                        return "index";
    }
}
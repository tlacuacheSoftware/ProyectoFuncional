/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import Dao.LoginDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Alumno;
import modelo.Profesor;

@ManagedBean
@RequestScoped
public class beanLogin {
   private Alumno alumno=new Alumno();
   private Profesor profesor=new Profesor();
   private final HttpServletRequest httpServletRequest;
   private final FacesContext faceContext;

    public String getSCorreo() {
        return SCorreo;
    }

    public void setSCorreo(String SCorreo) {
        this.SCorreo = SCorreo;
    }

    public String getSContrasenha() {
        return SContrasenha;
    }

    public void setSContrasenha(String SContrasenha) {
        this.SContrasenha = SContrasenha;
    }
   private  String SCorreo;
   private String SContrasenha;
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
   
    public Alumno getAlumno() {
        return alumno;
    }
    
    public beanLogin(){
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

   public String login(){
        LoginDao log=new LoginDao();
        Alumno a = null;
        Profesor p=null;
        this.alumno.setSCorreo(SCorreo);
        this.alumno.setSContrasenha(SContrasenha);
        this.profesor.setSCorreo(SCorreo);
        this.profesor.setSContrasenha(SContrasenha);
        String resultado;
            a=log.VerificarA(this.alumno);
            if(a!=null){
                httpServletRequest.getSession().setAttribute("sessionSNombre", a.getSNombre());
                  resultado="Alumno";     
            }else{
                p=log.VerificarP(this.profesor);
                if(p!=null){
                httpServletRequest.getSession().setAttribute("sessionSNombre", p.getSNombre());
                httpServletRequest.getSession().setAttribute("sessionidProfesor", p.getIdProfesor());
                  resultado="Profesor";     
            }else{
                resultado="Login";
            }
    }
            return resultado;
}
}
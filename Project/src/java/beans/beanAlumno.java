/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.AlumnoDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.sql.Alumno;
/**
 *
 * @author raul__000
 */
@ManagedBean
@RequestScoped
public class beanAlumno {
    String SNombre;
   private Alumno alumno=new Alumno();
   private final HttpServletRequest httpServletRequest;
   private final FacesContext faceContext;
    public Alumno getAlumno() {
        return alumno;
    }
    
    public beanAlumno(){
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

   public String login(){
        AlumnoDao prof=new AlumnoDao();
        Alumno p = null;
        String resultado;
            p=prof.Verificar(this.alumno);
            if(p!=null){
                httpServletRequest.getSession().setAttribute("sessionSNombre", this.alumno.getSNombre());
                  resultado="Exito";     
            }else{
                resultado="Alumno";
            }
        
        return resultado;
    }
}

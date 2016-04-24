/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import dao.ProfesorDao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.sql.Profesor;

/**
 *
 * @author raul__000
 */
@ManagedBean
@RequestScoped
public class beanProfesor {
   private Profesor profesor=new Profesor();
   private final HttpServletRequest httpServletRequest;
   private final FacesContext faceContext;
   public beanProfesor(){
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
    }
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
   public String login(){
        ProfesorDao prof=new ProfesorDao();
        Profesor p = null;
        String resultado;
            p=prof.Verificar(this.profesor);
            if(p!=null){
                httpServletRequest.getSession().setAttribute("sessionSNombre", p.getSNombre());
                resultado="Exito";
            }else{
                resultado="Profesor";
            }
        
        return resultado;
    }
}

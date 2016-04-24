/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.sql.Alumno;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author raul__000
 */
public class run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
          HttpServletRequest httpServletRequest;
        FacesContext faceContext;
        faceContext = FacesContext.getCurrentInstance();
        httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        Session session;
        Transaction tx;
       AlumnoDao prof=new AlumnoDao();
        Alumno p = null;
        Alumno alumno=new Alumno(0,91,"raul","godinez","vilchis","12345","correo");
        String resultado;
            p=prof.Verificar(alumno);
            if(p!=null){
                System.out.println(httpServletRequest.getSession().getAttribute("sessionSNombre"));
                resultado="Exito";
            }else{
                resultado="Alumno";
            }
        
    }
    
}

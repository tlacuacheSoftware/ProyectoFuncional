/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author raul__000
 */
@ManagedBean
@SessionScoped
/**
 *
 * @author raul__000
 */
public class beanMovimiento implements Serializable{
    public String Alumno(){
        return "Alumno";
    }
    public String Profesor(){
        return "Profesor";
    }
    public String RegistrarProfesor(){
        return "RegistrarProfesor";
    }
    public String Salir(){
        return "Index";
    }
    public String Login(){
        return "Login";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author raul__000
 */
@ManagedBean
@RequestScoped
public class beanMovimiento {
    public String alumno(){
        return "Alumno";
    }
    public String profesor(){
        return "Profesor";
    }
    public String registrarProfesor(){
        return "RegistrarProfesor";
    }
    public String registrarAlumno(){
        return "RegistrarAlumno";
    }
    public String salir(){
        return "Index";
    }
    public String login(){
        return "Login";
    }
    public String registrar(){
        return "Registrar";
    }
    public String modificar(){
        return "Modificar";
    }
    public String exito(){
        return "Exito";
    }
}
